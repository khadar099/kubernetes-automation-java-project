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
    }
	}
