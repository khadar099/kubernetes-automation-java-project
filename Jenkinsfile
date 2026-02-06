pipeline {
    
    agent any 
    
    stages {
        stage('Git Checkout'){
            steps{
                script{
                    git branch: 'feature/changing-port-in-dockerfile', url: 'https://github.com/khadar099/kubernetes-automation-java-project.git'
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
                       sh '''
                        docker login -u khadar3099 -p khadar@890
                        docker image push khadar3099/shopping:v.$BUILD_NUMBER
                        docker rmi shopping:v.$BUILD_NUMBER
                        docker rmi khadar3099/shopping:v.$BUILD_NUMBER
                        ''' 
                        }
                    }
                }
            }
    }
