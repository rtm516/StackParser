pipeline {
    agent any

    tools {
        maven 'Maven 3'
        jdk 'Java 8'
    }

    options {
        buildDiscarder(logRotator(artifactNumToKeepStr: '5'))
    }

    stages {
        stage ('Build') {
            steps {
                sh 'git submodule update --init --recursive'
                sh 'mvn clean package'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', excludes: 'target/original-*.jar', fingerprint: true
                }
            }
        }

        stage ('Deploy') {
            when {
                branch "master"
            }

            steps {
                withMaven(mavenSettingsConfig: 'repo', maven: 'Maven 3') {
                    sh 'mvn javadoc:jar source:jar deploy  -DskipTests'
                }
            }
        }
    }
}
