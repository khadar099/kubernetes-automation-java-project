pipeline {
    agent any

stages {
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
                        docker rmi shopping_website:v.$BUILD_ID || true
                        docker rmi khadar3099/shopping_website:v.$BUILD_ID || true
                        #docker ps -q -f name=shopping_website | grep -q . && docker stop shopping_website && docker rm shopping_website || echo  "container shopping_website not running"
                        #docker run -d -p 8082:8181 --name shopping_website khadar3099/shopping_website:v.$BUILD_ID
                        '''
                        }
            }
        }
    }
    stage ('deploy') {
        steps {
            sh '''
            ssh -o StrictHostKeyChecking=no -T ubuntu@44.204.75.60 << EOF
            docker ps -q -f name=shopping_website | grep -q . && docker stop shopping_website && docker rm shopping_website || echo "container shopping_website not running"
            docker run -d -p 8082:8181 --name shopping_website khadar3099/shopping_website:v.$BUILD_ID
            EOF
        '''
                    }   
                }
                }
            }
 
