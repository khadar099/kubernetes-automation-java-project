pipeline {
    agent any
stages {
   stage('Load .env File and Set Environment Variables') {
            steps {
                script {
                    // Check if the .env file exists
                    def envFile = '.env'
                    if (fileExists(envFile)) {
                        // Load environment variables from .env file
                        sh '''#!/bin/bash
                        # Export each key-value pair in .env as an environment variable
                        set -o allexport
                        // shell command source .env to read and load the environment variables from the .env file into the Jenkins pipeline.
                        source .env 
                        set +o allexport
                        '''
                    } else {
                        error '.env file not found in the repository'
                    }
                }
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
                withCredentials([string(credentialsId: 'dockerhubpswd', variable: 'dockerpswd')]) {
                    script {
                        def repoName = "${DOCKER_USERNAME}/${DOCKER_REPO}:v.${env.BUILD_TAG}"
                        sh """
                            docker login -u ${DOCKER_USERNAME} -p ${dockerpswd}
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
