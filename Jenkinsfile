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

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${env.SONARQUBE_ENV}") {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=shopping -Dsonar.projectName=ShoppingApp'
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
