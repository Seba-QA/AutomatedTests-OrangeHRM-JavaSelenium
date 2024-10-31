pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Seba-QA/AutomatedTests-OrangeHRM-JavaSelenium'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install' // Suponiendo que usas Maven
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test' // Ejecutar pruebas
            }
        }
        stage('Report') {
            steps {
                junit '**/target/surefire-reports/*.xml' // Ajusta la ruta según tu configuración
            }
        }
    }
}