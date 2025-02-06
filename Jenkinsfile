pipeline {
    agent any 
stages {
    stage ('checkout stage ') {
        steps {
        checkout scmGit(branches: [[name: '*/develop']], extensions: [], userRemoteConfigs: [[credentialsId: 'gitcreds', url: 'https://github.com/khadar099/kubernetes-automation-java-project.git']])
        }
    }
    stage ('build stage') {
        steps {
            sh 'mvn clean install'
        }
    }
    stage ('test stage') {
        steps {
            sh 'mvn test'
        }
    }
}

}
