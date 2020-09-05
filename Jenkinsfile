pipeline {
    agent { label 'qtest'}

    stages {
        // The first two stages below are explicitly mentioned so they are reported in Jenkins properly.
        stage('Build app') {
            steps {
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
                    junit 'app/build/outputs/androidTest-results/connected/*.xml'
                    submitJUnitTestResultsToqTest([apiKey: '1312d354-eb15-4817-9f89-a7b9bb717685', containerID: 527351, containerType: 'release', createTestCaseForEachJUnitTestClass: false, createTestCaseForEachJUnitTestMethod: true, overwriteExistingTestSteps: true, parseTestResultsFromTestingTools: false, projectID: 101677, qtestURL: 'https://androidtest.qtestnet.com/', submitToAReleaseAsSettingFromQtest: true, submitToExistingContainer: false, utilizeTestResultsFromCITool: true])
                    slackSend channel: '#jenkins-test', 
                              message: 'Hello, world'
                }
            }
        }
    }
}
