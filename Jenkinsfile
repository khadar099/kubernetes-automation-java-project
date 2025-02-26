pipeline {
    agent any 
stages {
    stage ('build stage') {
        steps {
            sh 'mvn clean install'
        }
    }
    stage ('build docker image and tag the image') {
        steps {
            sh '''
            docker build -t shopping-app:v.${BUILD_NUMBER} .
            docker tag shopping-app:v.${BUILD_NUMBER} khadar3099/shopping-app:v.${BUILD_NUMBER}
            '''
        }
    }
    stage ('push docker image to docker hub') {
        steps {
            withCredentials([string(credentialsId: 'dockerhubpswd', variable: 'dockerpswd')]) {
                sh 'docker login -u khadar3099 -p ${dockerpswd}'
                sh 'docker push khadar3099/shopping-app:v.${BUILD_NUMBER}'
            }

        }
    }
    stage ('deploy docker image or run container in ec2 instance') {
        steps {
            sh 'docker run -d -p 8181:8181 --name shopping_container  khadar3099/shopping_website:v.${BUILD_NUMBER}'
        }
    }
}   
}
