pipeline {
    agent any
stages {
    stage('checkout stage') {
        steps {
            checkout scmGit(branches: [[name: '*/feature/changing-port-in-dockerfile']], extensions: [], userRemoteConfigs: [[credentialsId: 'githubcreds', url: 'https://github.com/khadar099/kubernetes-automation-java-project.git']])
        }
    }
    stage('maven build') {
        steps {
            sh 'mvn clean package'
        }
    }
    stage('docker build stage') {
        steps {
            sh'docker build -t shoppingimage:1.2 .'
        }
    }
    stage('docker tag and push stage') {
        steps {
            sh 'docker tag shoppingimage:1.2 khadar3099/shoppingimage:1.2'
            withCredentials([string(credentialsId: 'dockerpassword', variable: 'dockerhubpasword')]) {
            sh 'docker login -u khadar3099 --password ${dockerhubpasword}'
            sh 'docker push khadar3099/shoppingimage:1.2'
        }
    }
    }
    stage('deploy on ec2 instance') {
        steps {
            sh 'docker run -d -p 8181:8181 --name shopping-container khadar3099/shoppingimage:1.2'
        }
    }
}
}
