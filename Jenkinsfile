pipeline {
    agent any 
stages {
    stage ('build stage') {
        steps {
            sh 'mvn clean install'
        }
    }
    stage ('build docker image , tag and push it to dockerhub') {
        steps {
            sh '''
            docker build -t shopping .
            docker tag shopping khadar3099/shopping
            docker push  khadar3099/shopping
            docker run -d -p 8181:8181 --name shopping_container  khadar3099/shopping
            '''
            }
         }
    }   
}
