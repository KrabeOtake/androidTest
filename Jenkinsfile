pipeline {
    agent { label 'qtest'}

    stages {
        // The first two stages below are explicitly mentioned so they are reported in Jenkins properly.
        stage('Build app') {
            steps {
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
                sh "$ADB start-server"

                def error
                parallel (
                    launchEmulator: {
                        sh "$ANDROID_HOME/tools/qemu/linux-x86_64/qemu-system-x86_64 -engine classic -prop persist.sys.language=en -prop persist.sys.country=US -avd test -no-snapshot-load -no-snapshot-save -no-window"
                    },
                    runAndroidTests: {
                        timeout(time: 20, unit: 'SECONDS') {
                        sh "$ADB wait-for-device"
                        }
                        try {
                            sh "./gradlew connectedAndroidTest"
                        } catch(e) {
                            error = e
                        }
                        sh script: '/var/lib/jenkins/kill-emu.sh'
                    }
                )
                if (error != null) {
                    throw error
                }
            }
        currentBuild.result = "SUCCESS"
        } catch (e) {
    currentBuild.result = "FAILED"
//    notifyFailed()
    throw e
  } finally {
    stage('Stage Clean') {
       sh script: '/var/lib/jenkins/clean.sh'
    }
  }
            }
        }
    }
}
