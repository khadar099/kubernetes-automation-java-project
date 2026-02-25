pipeline {
    agent any

    stages {

        stage('Load Config') {
            steps {
                checkout scmGit(branches: [[name: '*/feature/changing-port-in-dockerfile']], extensions: [], userRemoteConfigs: [[credentialsId: 'gitcreds', url: 'https://github.com/khadar099/kubernetes-automation-java-project.git']])
        }
        }
        stage('build stage') {
            steps {
                sh ' mvn clean package '
    }
}
    }
}

        
