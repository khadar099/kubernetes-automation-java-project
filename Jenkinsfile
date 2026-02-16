pipeline {
    agent any

    stages {

        stage('Load Config') {
            steps {
                script {
                    def props = readProperties file: 'config.properties'
                    props.each { key, value ->
                        env[key] = value
                    }
                }
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def tag = "v.${env.BUILD_NUMBER}"
                    def imageName = "${env.DOCKER_REPO}:${tag}"
                    def repoName = "${env.DOCKER_USERNAME}/${env.DOCKER_REPO}:${tag}"

                    sh """
                        docker build -t ${imageName} .
                        docker tag ${imageName} ${repoName}
                    """
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    def tag = "v.${env.BUILD_NUMBER}"
                    def repoName = "${env.DOCKER_USERNAME}/${env.DOCKER_REPO}:${tag}"

                    withCredentials([string(credentialsId: 'dockerhubpswd', variable: 'dockerpswd')]) {
                        sh """
                            echo ${dockerpswd} | docker login -u ${env.DOCKER_USERNAME} --password-stdin
                            docker push ${repoName}
                        """
                    }
                }
            }
        }

        stage('Deploy Container') {
            steps {
                script {
                    def tag = "v.${env.BUILD_NUMBER}"
                    def imageName = "${env.DOCKER_USERNAME}/${env.DOCKER_REPO}:${tag}"

                    sh """
                        docker ps -q -f name=${env.CONTAINER_NAME} | grep -q . && docker stop ${env.CONTAINER_NAME} && docker rm ${env.CONTAINER_NAME} || echo "Container not running"
                        docker run -d -p ${env.HOST_PORT}:${env.CONTAINER_PORT} --name ${env.CONTAINER_NAME} ${imageName}
                    """
                }
            }
        }
    }
}
