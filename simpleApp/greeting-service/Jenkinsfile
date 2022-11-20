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