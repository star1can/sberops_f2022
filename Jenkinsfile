pipeline {
//     agent {
//         node {
//             label 'Linux_Default'
//         }
//     }
    agent any

    stages {
        stage('Build') {
            steps {
                withMaven(maven: 'Maven 3.5.2') {
                    sh 'mvn clean package'
                }
            }
        }

        stage('SQ') {
            steps {
                withSonarQubeEnv(credentialsId: 'sq_secret', installationName: 'SonarQube') {
                    withMaven(maven: 'Maven 3.5.2') {
                        script {
                            sh """
                            mvn sonar:sonar \
                            -Dsonar.sources=src/main \
                            -Dsonar.language=java \
                            -Dsonar.tests=src/test \
                            -Dsonar.junit.reportsPath=target/surefire-reports \
                            -Dsonar.surefire.reportsPath=target/surefire-reports \
                            -Dsonar.jacoco.reportPath=target/jacoco.exec \
                            -Dsonar.java.coveragePlugin=jacoco \
                            -Dsonar.verbose=true
                            """
                        }
                    }
                }
            }
        }

        stage('Test') {
            steps {
                withMaven(maven: 'Maven 3.5.2') {
                    sh 'mvn test'
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