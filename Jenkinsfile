pipeline {
    
    agent {label 'node2'}
    
    stages {
        stage('Git Checkout'){
            steps{
                script{
                    git branch: 'SIT', url: 'https://github.com/khadar099/kubernetes-automation-java-project.git'
                    }
                }
            }
        stage('Maven build') {
            
            steps {
                
                script{
                    
                    sh 'mvn clean install'
                }
            }
        }
        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Docker image  build stage') {
            steps {
                sh 'docker image build -t shopping:v.$BUILD_NUMBER .'
            }
        }
        stage('Tag docker image') {
            steps {
                sh 'docker image tag shopping:v.$BUILD_NUMBER khadar3099/shopping:v.$BUILD_NUMBER'
                }
        }
       stage ('push docker image to  dockerhub') {
            steps {
                script {
                   withCredentials([string(credentialsId: 'dockerhub-password', variable: 'dockerhub_psd')]) {
                        sh '''
                        docker login -u khadar3099 -p ${dockerhub_psd}
                        docker image push khadar3099/shopping:v.$BUILD_NUMBER
                        docker rmi shopping:v.$BUILD_NUMBER
                        docker rmi khadar3099/shopping:v.$BUILD_NUMBER
                        ''' 
                        }
                    }
                }
            }
        stage('deploy docker image') {
            steps {
                 sh '''
                 docker ps -q -f name=shopping-container && docker stop shopping-container && docker rm shopping-container || echo "Container not found or already stopped."
                 docker run -d -p 9191:8181 --name shopping-container khadar3099/shopping:v.$BUILD_NUMBER
                 '''
            }
        }
    }
}
