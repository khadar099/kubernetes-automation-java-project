pipeline {
    agent any
    environment {
        DOCKER_USERNAME = 'khadar3099'               // Docker username
        DOCKER_REPO = 'shopping-app'                 // Docker repository name
        CONTAINER_NAME = 'shopping_container'         // Container name
        HOST_PORT = '8181'                           // Host port to expose
        CONTAINER_PORT = '8181'                      // Container port inside the container
        BUILD_TAG = "${env.BUILD_NUMBER}"            // Docker image tag (build number)
    }
    stages {
        stage('checkout stage') {
            steps {
                checkout scmGit(branches: [[name: '*/develop']], extensions: [], userRemoteConfigs: [[credentialsId: 'gitcreds', url: 'https://github.com/khadar099/kubernetes-automation-java-project.git']])
                }
            }
        stage ('build stage') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage ('build docker image and tag the image') {
            steps {
                script {
                    def imageName = "${DOCKER_REPO}:v.${env.BUILD_TAG}"
                    def repoName = "${DOCKER_USERNAME}/${DOCKER_REPO}:v.${env.BUILD_TAG}"
                    sh """
                        docker build -t ${imageName} .
                        docker tag ${imageName} ${repoName}
                    """
                }
            }
        }
        stage ('push docker image to docker hub') {
            steps {
                withCredentials([string(credentialsId: 'dockerhub-password', variable: 'dockerhub_psd')]) {
                    script {
                        def repoName = "${DOCKER_USERNAME}/${DOCKER_REPO}:v.${env.BUILD_TAG}"
                        sh """
                            docker login -u ${DOCKER_USERNAME} -p ${dockerhub_psd}
                            docker push ${repoName}
                            docker rmi ${repoName}
                        """
                    }
                }
            }
        }
        stage ('deploy docker image or run container in ec2 instance') {
            steps {
                script {
                    def imageName = "${DOCKER_USERNAME}/${DOCKER_REPO}:v.${env.BUILD_TAG}"
                    def containerName = "${CONTAINER_NAME}"
                    sh """
                        docker ps -q -f name=${containerName} && docker stop ${containerName} && docker rm ${containerName} || echo "Container not found or already stopped."
                        docker run -d -p ${HOST_PORT}:${CONTAINER_PORT} --name ${containerName} ${imageName}
                    """
                }
            }
        }
    }
}
