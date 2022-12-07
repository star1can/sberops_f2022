environment {
    TESTS_PASSED = "false"
}

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                withMaven(maven: 'Maven 3.5.2') {
                    sh 'mvn package -Dmaven.test.skip'
                }
            }
        }

        stage('Test') {
            steps {
                withMaven(maven: 'Maven 3.5.2') {
                    sh 'mvn test'
                }
            }

            post {
                success {
                    TESTS_PASSED = "true"
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

        stage('SQ') {
            steps {
                if(TESTS_PASSED == "true") {
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
        }

        stage('Docker build') {
            steps {
                if(TESTS_PASSED == "true") {
                    sh 'docker build -t tuzzik/greeting-service:latest .'
                }
            }
        }

        stage('Docker push') {
            steps {
                if(TESTS_PASSED == "true") {
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
        }

        stage('Ansible') {
            steps {
                if(TESTS_PASSED == "true") {
                    timeout(time: 30, unit:'SECONDS') {
                        sh 'deploy/docker/start.sh'
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs deleteDirs:true
        }
    }
}