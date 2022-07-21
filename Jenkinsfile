pipeline {
  agent any
  stages {
    stage('Prepare') {
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
        }
        cleanup {
          echo 'after all other post conditions'
        }
      }
    }
stage('maven build') {
      steps {
        sh  'mvn package'
      }
      post {
        success {
          echo 'maven build success'
        }
        failure {
          echo 'maven build failed'
        }
      }
    }
  }
}

