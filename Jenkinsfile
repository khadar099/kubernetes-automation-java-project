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
                withSonarQubeEnv("Sonarqube") {
                   sh ' mvn clean verify sonar:sonar -Dsonar.projectKey=springboot-app'
            }
        }
    }
    stage('Quality Gate') {
        steps {
            timeout(time: 3, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
            }
        }
}
}
}
