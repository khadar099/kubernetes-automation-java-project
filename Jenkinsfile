pipeline {
    
    agent any 
    
    stages {
        stage('Git Checkout'){
            steps{
                script{
                    git branch: 'newbranch', url: 'https://github.com/khadar099/kubernetes-automation-java-project.git'
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
                sh 'docker image build -t $JOB_NAME:v.$BUILD_ID .'
            }
        }
        stage('Push docker image to  docker hub') {
            steps {
                sh 'docker image tag $JOB_NAME:v.$BUILD_ID khadar3099/$JOB_NAME:v.$BUILD_ID'
                }
        }
       stage ('push docker image to  dockerhub') {
            steps {
                script {
                   withCredentials([string(credentialsId: 'dockerhub-password', variable: 'dockerhub_psd')]) {
                        sh 'docker login -u khadar3099 -p ${dockerhub_psd}'
                        sh 'docker image push khadar3099/$JOB_NAME:v.$BUILD_ID'
                        //sh "docker rmi khadar3099/$JOB_NAME:v.$BUILD_ID"
                        //sh 'docker run -p 9191:9090 khadar3099/k8s-demo:v.7'
                        }
                    }
                }
            }
    }
}
