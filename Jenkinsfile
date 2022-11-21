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
                withSonarQubeEnv(credentialsId: 'sberops_sq') {
                    withMaven(maven: 'Maven 3.5.2') {
                        script {
                            sh """
                            mvn sonar:sonar -Dsonar.branch.name=develop
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