pipeline {
    agent any
    stages{
        stage('get code'){
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/khadar099/kubernetes-automation-java-project.git']]])
            }
        }
        stage (' build stage') {
            step {
                sh ' mvn clean install '
            }
        }
    }
}
        

