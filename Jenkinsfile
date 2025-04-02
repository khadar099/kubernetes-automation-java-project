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
    }
}
