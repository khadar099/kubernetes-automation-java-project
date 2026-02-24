pipeline {
    agent any

    stages {

        stage('Load Config') {
            steps {
                script {
                    def props = readProperties file: 'config.properties'
                    
                    props.each { key, value ->
                        env."${key}" = value   // ✅ sandbox-safe way
                    }
                }
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${SONAR_SERVER}") {
                   sh ' mvn clean verify sonar:sonar -Dsonar.projectKey=springboot-app'
            }
        }
    }
}
}
