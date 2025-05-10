pipeline {
    agent any

    environment {
        SONARQUBE_ENV = 'SonarQube' // replace with your actual SonarQube server name
    }

    stages {
        stage('Git Checkout') {
            steps {
                script {
                    git branch: 'UAT', url: 'https://github.com/khadar099/kubernetes-automation-java-project.git'
                }
            }
        }

        stage('Maven build') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }

        stage("build & SonarQube analysis") {
          node {
              withSonarQubeEnv('SonarQube') {
                 sh 'mvn clean package sonar:sonar'
              }    
          }
      }
      
        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
