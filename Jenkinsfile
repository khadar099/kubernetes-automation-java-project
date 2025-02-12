pipeline {
    agent any

    environment {
        RECIPIENTS = 'khadar3099@gmail.com'  // Set email notification recipients
    }

    stages {
        stage('Build stage') {
            steps {
                sh 'mvn clean install'
            }
        }
        
        stage('Build Docker image, tag, and push it to Docker Hub') {
            steps {
                sh '''
                docker build -t shopping .
                docker tag shopping khadar3099/shopping
                docker push khadar3099/shopping
                docker run -d -p 8181:8181 --name shopping_container khadar3099/shopping
                '''
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
            // Send success email
            emailext(
                subject: "Build Success: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: "The build was successful. Check console output for details: ${env.BUILD_URL}",
                to: "${env.RECIPIENTS}"
            )
        }

        failure {
            echo 'Build failed!'
            // Send failure email
            emailext(
                subject: "Build Failed: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: "The build has failed. Please check the console output for more details: ${env.BUILD_URL}",
                to: "${env.RECIPIENTS}"
            )
        }

        always {
            // Clean workspace after the build is finished (regardless of success or failure)
            cleanWs()
        }
    }
}
