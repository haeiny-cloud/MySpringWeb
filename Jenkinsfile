pipeline {
    agent any
    environment {
        userId = "s05"
    }
    stages {
        stage('prepare') {
            agent any
            steps {
                checkout scm
            }
            post {
                success {
                    echo 'prepare success'
                }
                always {
                    echo 'done prepare'
                    slackSend (channel: '#devops', color: '#FFFF00',
                        message: "${env.userId} STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                }
                cleanup {
                    echo 'after all other post conditions'
                }
            }
        }
        stage('maven build') {
            steps {
                sh 'mvn package'
            }
            post {
                success {
                    echo 'maven build success'
                }
                always {
                    echo 'done maven build'
                    slackSend (channel: '#devops', color: '#FFFF00',
                        message: "${env.userId} STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                }
                failure {
                    echo 'maven build failed'
                }
            }
        }
        stage('dockerizing') {
            steps {
                sh 'docker build . -t myspringweb'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker run -d -p 40589:80 myspringweb'
            }
            post {
                success {
                    echo 'success'
                }
                failure {
                    echo 'failed'
                }
            }
        }
    }
}