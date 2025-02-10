pipeline {
    agent any

    environment {
        // Define environment variables
        GIT_REPO_URL = 'https://github.com/khadar099/kubernetes-automation-java-project.git'
        GIT_CREDENTIALS_ID = 'GitCreds'
        DOCKER_USERNAME = 'khadar3099'
        DOCKER_IMAGE_NAME = 'shopping_website'
        DOCKERHUB_CREDENTIALS_ID = 'Dockerhubpswd'
        DOCKER_TAG = "v.${BUILD_ID}" // Tag the Docker image with the Jenkins build ID
        HOST_PORT = '8082' // Port on host machine
        CONTAINER_PORT = '8181' // Port inside the container
    }

    stages {
        stage ('checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/feature/changeimage-and-header']], extensions: [], userRemoteConfigs: [[credentialsId: "${GIT_CREDENTIALS_ID}", url: "${GIT_REPO_URL}"]])
            }
        }
        
        stage ('build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage ('docker build') {
            steps {
                sh '''
                docker image build -t ${DOCKER_IMAGE_NAME}:${DOCKER_TAG} .
                docker image tag ${DOCKER_IMAGE_NAME}:${DOCKER_TAG} ${DOCKER_USERNAME}/${DOCKER_IMAGE_NAME}:${DOCKER_TAG}
                '''
            }
        }

        stage ('push docker image to dockerhub') {
            steps {
                script {
                    withCredentials([string(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}", variable: 'dockerhubpasswrd')]) {
                        sh '''
                        docker login -u ${DOCKER_USERNAME} -p ${dockerhubpasswrd}
                        docker image push ${DOCKER_USERNAME}/${DOCKER_IMAGE_NAME}:${DOCKER_TAG}
                        docker run -d -p ${HOST_PORT}:${CONTAINER_PORT} ${DOCKER_USERNAME}/${DOCKER_IMAGE_NAME}:${DOCKER_TAG}
                        '''
                    }
                }
            }
        }
    }
}
