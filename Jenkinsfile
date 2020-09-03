pipeline {
    agent { label 'qtest'}

    stages {
        // The first two stages below are explicitly mentioned so they are reported in Jenkins properly.
        stage('Build app') {
            steps {
                
                    echo no | ${env.ANDROID_HOME}/tools/bin/avdmanager create avd --force --name test --abi google_apis/x86_64 --package "system-images;android-28;google_apis;x86_64"
                    sh "${env.ANDROID_HOME}/emulator/emulator -avd test -no-audio -wipe-data -debug -all"
                    //sh "${env.ANDROID_HOME}/emulator/emulator -avd Pixel_2_API_28 -wipe-data -engine auto -port 5554 -no-audio -no-boot-anim -no-window -gpu off -no-cache -memory 3072"
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
