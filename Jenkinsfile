pipeline {
    agent any

stages {
    stage ('checkout stage') {
        steps {
            checkout scmGit(branches: [[name: '*/feature/changeimage-and-header']], extensions: [], userRemoteConfigs: [[credentialsId: 'GitCreds', url: 'https://github.com/khadar099/kubernetes-automation-java-project.git']])
        }
    }
}
}
