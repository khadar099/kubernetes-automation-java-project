pipeline {
    agent any
    environment {
        // You can customize this or pull the version from a different source (like a tag or environment variable)
        VERSION = sh(script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true).trim()
    }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Build docker image') {
            steps {
                script {
                    // You can use a version or any other dynamic variable (like build number or commit hash)
                    def version = "${VERSION}-${BUILD_NUMBER}"  // Combine Maven version with Jenkins build number
                    sh "docker build -t myrepo/testing:${version} ."
                }
            }
        }
    }
}
