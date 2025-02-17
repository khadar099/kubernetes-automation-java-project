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
                sh '''
                ssh -tt -o StrictHostKeyChecking=no ec2-user@44.204.75.60
                sh mkdir basha
                sh '''
            }
        }
    }
}
