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
        withSonarQubeEnv('Sonarqubeserver') {
            sh 'mvn clean verify sonar:sonar'
        }
    }
}
    }
}





















stage('Quality Gate') {
    steps {
        timeout(time: 5, unit: 'MINUTES') {
            script {
                def qg = waitForQualityGate() // capture result
                echo "Quality Gate status: ${qg.status}"

                if (qg.status != 'OK') {
                    // do something if failed
                    error "Quality Gate failed: ${qg.status}"
                } else {
                    echo "Quality Gate passed ✅"
                }
            }
        }
    }
}
    }
}
