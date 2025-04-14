pipeline {
    agent any

    parameters {
        choice(name: 'ENVIRONMENT', choices: ['dev', 'SIT', 'UAT', 'Prod'], description: 'Select the environment to build from the corresponding branch')
    }

    environment {
        GIT_URL = 'https://github.com/khadar099/kubernetes-automation-java-project.git'
    }

    stages {
        stage('Git Checkout') {
            steps {
                script {
                    // Map environment to git branch
                    def branchMap = [
                        dev : 'dev',
                        SIT : 'sit',
                        UAT : 'uat',
                        Prod: 'main' // assuming 'main' is your prod branch
                    ]
                    def selectedBranch = branchMap[params.ENVIRONMENT]
                    echo "Checking out branch: ${selectedBranch}"
                    git branch: selectedBranch, url: env.GIT_URL
                }
            }
        }

        stage('Maven build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Docker image build') {
            steps {
                sh "docker image build -t shopping:v.${BUILD_NUMBER} ."
            }
        }

        stage('Tag Docker image') {
            steps {
                sh "docker image tag shopping:v.${BUILD_NUMBER} khadar3099/shopping:v.${BUILD_NUMBER}"
            }
        }

        stage('Push Docker image to Docker Hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-password', variable: 'dockerhub_psd')]) {
                        sh '''
                        docker login -u khadar3099 -p ${dockerhub_psd}
                        docker image push khadar3099/shopping:v.${BUILD_NUMBER}
                        docker rmi shopping:v.${BUILD_NUMBER}
                        docker rmi khadar3099/shopping:v.${BUILD_NUMBER}
                        '''
                    }
                }
            }
        }

        stage('Deploy Docker image') {
            steps {
                sh '''
                docker ps -q -f name=shopping-container && docker stop shopping-container && docker rm shopping-container || echo "Container not found or already stopped."
                docker run -d -p 9191:8181 --name shopping-container khadar3099/shopping:v.${BUILD_NUMBER}
                '''
            }
        }
    }
}
