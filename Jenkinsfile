pipeline {
    agent any

    stages {
        stage('Build Application') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('sonarqube analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh ' mvn sonar:sonar '
                    }
                }
        }
        stage('Quality Gate') {
            stesps {
                timeout(time: 5, unit: 'MINUTES') {
                      waitForQualityGate abortPipeline:true
                }
            }
    }
}
}
