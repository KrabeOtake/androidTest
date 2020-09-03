pipeline {
    agent { label 'qtest'}

    stages {
        // The first two stages below are explicitly mentioned so they are reported in Jenkins properly.
        stage('Build app') {
            steps {
                    sh "echo ${env.ANDROID_HOME}/tools/emulator"
                    echo "Hello World"
                    sh "./gradlew assembleDebug"
            }
        }

        stage('Build test app') {
            steps {
                sh "./gradlew testDebug"
            }
        }
        
        stage('Instumental tests') {
            steps {
                sh "./gradlew connectedAndroidTest"
            }
        }
    }
}
