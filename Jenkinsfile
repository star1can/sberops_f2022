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
                    sh 'mvn -f ("./simpleApp/greeting-service/pom.xml") clean install'
                    sh 'mvn -f ("./simpleApp/greeting-service/pom.xml") dependency:tree'
                }
            }
        }
    }
}