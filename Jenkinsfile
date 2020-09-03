pipeline {
    agent { label 'qtest'}

    stages {
        // The first two stages below are explicitly mentioned so they are reported in Jenkins properly.
        stage('Build app') {
            steps {
                    sh "${env.ANDROID_HOME}/emulator/emulator -avd Pixel_2_API_28 -wipe-data -engine auto -no-audio -no-window -no-cache -memory 3072"
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
