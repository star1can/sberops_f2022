pipeline {
//     agent {
//         node {
//             label 'Linux_Default'
//         }
//     }
    agent any

    stages {
        stage('Access right directory') {
            steps {
                script {
                    sh 'cd simpleApp/greeting-service'
                }
            }
        }
        stage('Build') {
            steps {
                withMaven(maven: 'Maven 3.5.2') {
                    sh 'mvn clean install'
                    sh 'mvn dependency:tree'
                }
            }
        }

        stage('Test') {
            steps {
                withMaven(maven: 'Maven 3.5.2') {
                    sh 'mvn clean install'
                    sh 'mvn dependency:tree'
                }
            }
        }
    }
}