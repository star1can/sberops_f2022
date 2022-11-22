pipeline {
    agent any

    stages {
        stage('Build && Test') {
            steps {
                withMaven(maven: 'Maven 3.5.2') {
                    sh 'mvn clean install'
                }
            }
        }

        stage('SQ') {
            steps {
                withSonarQubeEnv(credentialsId: 'sq_secret_token', installationName: 'SonarQube') {
                    withMaven(maven: 'Maven 3.5.2') {
                        script {
                            sh """
                            mvn sonar:sonar
                            """
                        }
                    }
                }
            }
        }

        stage('Docker build') {
            steps {
                sh 'docker build -t tuzzik/greeting-service:latest .'
            }
        }

        stage('Docker push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                  sh """
                  docker login \
                  -u ${env.dockerHubUser} \
                  -p ${env.dockerHubPassword}
                  """
                  sh 'docker push tuzzik/greeting-service:latest'
                }
            }
        }

        stage('Ansible') {
            steps {
                timeout(time: 30, unit:'SECONDS') {
                    sh 'deploy/docker/start.sh'
                }
            }
        }
    }

    post {
        always {
            script {
                 allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                 ])
            }
        }
    }
}