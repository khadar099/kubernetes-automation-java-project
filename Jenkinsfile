pipeline {
    agent any 
stages {
    stage ('build stage') {
        steps {
            sh 'mvn clean install'
        }
    }
    stage ('build docker image') {
        steps {
            sh ' docker build -t shopping .'
            sh ' docker tag shopping khadar3099/shopping '
            }
         }
    }
}
