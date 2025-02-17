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
                sh ' ssh -o StrictHostKeyChecking=no -T ubuntu@44.204.75.60 << EOF'
                sh ' mkdir basha'
            }
        }
