pipeline {
    agent any

stages {
    stage ('checkout') {
        steps {
            checkout scmGit(branches: [[name: '*/feature/changeimage-and-header']], extensions: [], userRemoteConfigs: [[credentialsId: 'GitCreds', url: 'https://github.com/khadar099/kubernetes-automation-java-project.git']])
        }
    }
    stage ('build') {
        steps {
            sh ' mvn clean install'
        }
    }
    stage ('docker build') {
        steps {
            sh '''
            docker image build -t shopping_website:v.$BUILD_ID .
            docker image tag shopping_website:v.$BUILD_ID khadar3099/shopping_website:v.$BUILD_ID
            '''
            }
        }
    stage ('push docker image to  dockerhub') {
        steps {
            script {
                withCredentials([string(credentialsId: 'Dockerhubpswd', variable: 'dockerhubpasswrd')]) {
                        sh '''
                        docker login -u khadar3099 -p ${dockerhubpasswrd}
                        docker image push khadar3099/shopping_website:v.$BUILD_ID
                        docker run -d -p 8082:8181 khadar3099/shopping_website:v.$BUILD_ID
                        '''
                        }
                    }
                }
            }
        }
    }
