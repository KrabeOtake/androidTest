pipeline {
    def ANDROID_HOME='/Users/Shared/Android/sdk'
    def ADB="$ANDROID_HOME/platform-tools/adb"
    
    agent { label 'qtest'}

    stages {
        // The first two stages below are explicitly mentioned so they are reported in Jenkins properly.
        stage('Build app') {
            steps {
                    sh "$ADB wait-for-device"
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
