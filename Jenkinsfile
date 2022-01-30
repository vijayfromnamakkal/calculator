pipeline {
     agent any
     stages {
          stage('Compile') {
               steps {
                    sh './gradlew compileJava'
               }
          }
          stage('Unit test') {
               steps {
                    sh './gradlew test'
               }
          }
	  stage('Static code analysis') {
            steps {
              sh './gradlew checkstyleMain'
	      publishHTML (target: [
		     reportDir: 'build/reports/checkstyle/',
		     reportFiles: 'main.html',
		     reportName: 'Checkstyle Report'
	      ])
            }
         }
          stage('Code coverage') {
               steps {
                    sh './gradlew jacocoTestReport'
		    publishHTML (target: [
               		reportDir: 'build/reports/jacoco/test/html',
               		reportFiles: 'index.html',
			reportName: 'JaCoCo Report'
          	    ])
                    sh './gradlew jacocoTestCoverageVerification'
                    }
	       }
	   stage('Package') {
              steps {
                sh './gradlew build'
              }
           }
	     
	   stage('Docker build') {
             steps {
               sh 'docker build -t ksvijaynkl/calculator .'
             }
          }
	     
	     stage('Docker push') {
		 steps {
	           script {
		      def userInput = input(
                            id: 'userInput', message: 'Enter password',
                            parameters: [
                                    string(defaultValue: 'None',
                                            description: 'Path of config file',
                                            name: 'Config')
                       ])
		   }
		 sh "docker login --username ksvijaynkl --password ${userInput.Config}"
                 sh 'docker push ksvijaynkl/calculator'
               }
             }
	     
	     stage('Deploy to staging') {
               steps {
                 sh 'docker run -d --rm -p 8765:8080 --name calculator ksvijaynkl/calculator'
               }
            }
	     
	     stage('Acceptance test') {
               steps {
                sleep 60
                sh 'chmod +x acceptance_test.sh && ./acceptance_test.sh'
               }
             }
   
     }
   }
