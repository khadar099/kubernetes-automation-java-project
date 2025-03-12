pipeline {
    agent any
    stages{
        stage('build'){
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/khadar099/kubernetes-automation-java-project.git']]])
                sh 'mvn clean install '
            }
        }
        stage('static code analysis'){
            steps{
                script{
                    sh 'mvn clean verify sonar:sonar \
                      -Dsonar.projectKey=shopping-app \
                      -Dsonar.host.url=http://3.108.42.106:9000 \
                      -Dsonar.login=squ_e85ead67c88147526f6d4e785313f73f2c5702b6 '
                }
            }
        
                }
            }
        }

