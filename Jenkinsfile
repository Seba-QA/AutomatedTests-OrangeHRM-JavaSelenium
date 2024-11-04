pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Maven' // Nombre configurado en Jenkins para Maven
        JAVA_HOME = tool 'Java'   // Nombre configurado en Jenkins para Java
    }
    stages {
        stage('Checkout') {
            steps {
                // Clona el repositorio de Git
                git url: 'https://github.com/Seba-QA/AutomatedTests-OrangeHRM-JavaSelenium.git',
                branch: 'master'
            }
        }
        stage('Build') {
            steps {
                // Ejecuta Maven para construir el proyecto
                script {
                    def mvnHome = tool name: 'Maven', type: 'maven'
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        sh 'mvn clean install'
                    }
                }
            }
        }
        stage('Run Tests') {
            steps {
                // Ejecuta pruebas y genera reportes
                sh 'mvn test'
            }
        }
        stage('Generate Cucumber Report') {
            steps {
                // Verifica y genera reportes usando el plugin de Cucumber si es necesario
                sh 'mvn verify -Dcucumber.options="--plugin json:target/cucumber-reports/cucumber.json"'
            }
        }
    }
    post {
        always {
            // Archiva los resultados de los tests
            archiveArtifacts artifacts: '**/target/*.json, **/target/*.html', allowEmptyArchive: true
        }
        failure {
            // Notifica si la compilación falla
            echo 'Build fallida'
        }
        success {
            // Notifica si la compilación es exitosa
            echo 'Build exitosa'
        }
    }
}
