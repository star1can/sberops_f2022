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
                script {
                    sh 'mvn clean install'
                    sh 'mvn dependency:tree'
                }
            }
        }
    }
}