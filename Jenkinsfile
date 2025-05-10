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
      
      stage("Quality Gate"){
          timeout(time: 1, unit: 'HOURS') {
              def qg = waitForQualityGate()
              if (qg.status != 'OK') {
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"
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
