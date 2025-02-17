pipeline {
    agent any

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
