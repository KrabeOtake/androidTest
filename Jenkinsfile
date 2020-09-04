pipeline {
    agent { label 'qtest'}

    stages {
        // The first two stages below are explicitly mentioned so they are reported in Jenkins properly.
        stage('Build app') {
            steps {
                
                    //sh "echo no | ${env.ANDROID_HOME}/tools/bin/avdmanager create avd --force --name test --abi google_apis/x86_64 --package 'system-images;android-28;google_apis;x86_64'"
                    //sh "${env.ANDROID_HOME}/emulator/emulator -avd Pixel_2_API_28 -memory 3072 & $ANDROID_HOME/platform-tools/adb wait-for-device"
                    //sh "${env.ANDROID_HOME}/emulator/emulator -avd Pixel_2_API_28 -wipe-data -engine auto -port 5554 -no-audio -no-boot-anim -no-window -gpu off -no-cache -memory 3072"
                    
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
                sh "${env.ANDROID_HOME}/emulator/emulator -avd Pixel_2_API_28 -memory 3072 & $ANDROID_HOME/platform-tools/adb wait-for-device"
                    
                sh "./gradlew connectedAndroidTest"
                }
            post {
                always {
                    sh "ls ${env.WORKSPACE}/app/build/reports/androidTests/connected"
                    junit '${env.WORKSPACE}/app/build/reports/androidTests/connected/index.xml'
                    submitJUnitTestResultsToqTest([apiKey: '1312d354-eb15-4817-9f89-a7b9bb717685', containerID: 527351, containerType: 'release', createTestCaseForEachJUnitTestClass: true, createTestCaseForEachJUnitTestMethod: false, overwriteExistingTestSteps: true, parseTestResultsFromTestingTools: false, projectID: 101677, qtestURL: 'https://androidtest.qtestnet.com/', submitToAReleaseAsSettingFromQtest: true, submitToExistingContainer: false, utilizeTestResultsFromCITool: true])
                }
            }
        }
    }
}
