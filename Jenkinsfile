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
                  ssh -o StrictHostKeyChecking=no -tt ubuntu@44.204.75.60
                  mkdir bashad
                  cd bashad
                  touch khadar
                  EOF
                  exit
               sh '''
            }
        }
    }
}
