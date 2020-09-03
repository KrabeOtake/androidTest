pipeline {
    agent { label 'qtest'}

    stages {
        // The first two stages below are explicitly mentioned so they are reported in Jenkins properly.
        stage('Build app') {
            steps {
                dir("ReferenceAndroid") {
                    cd ..
                    echo "Hello World"
                    sh "./gradlew assembleDebug"
                }
            }
        }

        stage('Build test app') {
            steps {
                sh "./gradlew testDebug"
            }
        }
//test 
    }
}
