def COLOR_MAP = [
    'SUCCESS': 'good', 
    'FAILURE': 'danger',
]

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
                //Launch and wait for emulator
                sh "${env.ANDROID_HOME}/emulator/emulator -avd Pixel_2_API_28 -memory 3072 & $ANDROID_HOME/platform-tools/adb wait-for-device"  
                //Run Espresso tests
                sh "./gradlew connectedAndroidTest"
                }
            post {
                always {
                    //Generate test report
                    junit 'app/build/outputs/androidTest-results/connected/*.xml'
                    //Send results to qTest Manager
                    submitJUnitTestResultsToqTest([apiKey: '1312d354-eb15-4817-9f89-a7b9bb717685', containerID: 527351, containerType: 'release', createTestCaseForEachJUnitTestClass: false, createTestCaseForEachJUnitTestMethod: true, overwriteExistingTestSteps: true, parseTestResultsFromTestingTools: false, projectID: 101677, qtestURL: 'https://androidtest.qtestnet.com/', submitToAReleaseAsSettingFromQtest: true, submitToExistingContainer: false, utilizeTestResultsFromCITool: true])
                    //Send message with build result to slack channel
                    slackSend channel: '#jenkins-test', 
                              color: COLOR_MAP[currentBuild.currentResult],
                              message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME} build ${env.BUILD_NUMBER} \n More info at: ${env.BUILD_URL}"
                }
            }
        }
    }
}
