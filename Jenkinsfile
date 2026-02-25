pipeline {
    agent any
environment {
        SONAR_SERVER = 'Sonarqube'  // Name from Jenkins config
    }
    stages {

        stage('Load Config') {
            steps {
                checkout scmGit(branches: [[name: '*/feature/changing-port-in-dockerfile']], extensions: [], userRemoteConfigs: [[credentialsId: 'gitcreds', url: 'https://github.com/khadar099/kubernetes-automation-java-project.git']])
        }
        }
        stage('build stage') {
            steps {
                sh ' mvn clean package '
    }
}
stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${SONAR_SERVER}") {
                    sh '''
                       mvn sonar:sonar \
                       -Dsonar.projectKey=springboot-app \
                       -Dsonar.projectName="springboot-app" \
                       -Dsonar.host.url=http://13.235.50.77:9000 \
                       -Dsonar.login=squ_960238f3f87faca7f0948f6c20ec0a4784efd5ad
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}

        
