pipeline {
//     agent {
//         node {
//             label 'Linux_Default'
//         }
//     }
    agent any

    stages {
//         stage('Build') {
//             steps {
//                 withMaven(maven: 'Maven 3.5.2') {
//                     sh 'mvn clean install'
//                 }
//             }
//         }
//
//         stage('SQ') {
//             steps {
//                 withSonarQubeEnv(credentialsId: 'sq_secret', installationName: 'SonarQube') {
//                     withMaven(maven: 'Maven 3.5.2') {
//                         script {
//                             sh """
//                             mvn sonar:sonar
//                             """
//                         }
//                     }
//                 }
//             }
//         }
//
//         stage('Test') {
//             steps {
//                 withMaven(maven: 'Maven 3.5.2') {
//                     sh 'mvn test'
//                 }
//             }
//         }

        stage('Ansible') {
            steps {
                sh 'deploy/docker/start.sh'
            }
        }
    }

//     post {
//         always {
//             script {
//                  allure([
//                     includeProperties: false,
//                     jdk: '',
//                     properties: [],
//                     reportBuildPolicy: 'ALWAYS',
//                     results: [[path: 'target/allure-results']]
//                  ])
//             }
//         }
//     }
}