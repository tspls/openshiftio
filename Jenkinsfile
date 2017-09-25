#!groovy
@Library("shared-library@development") _		

pipeline {
	agent any		
	
	//Set version of Maven and Java
	tools {
		maven "Maven 3.3.9"		
		jdk "jdk1.8.0_111"		
	}	
			
	options {
		buildDiscarder(logRotator(numToKeepStr:'5'))	
		skipDefaultCheckout()
	}
					
	stages {			
		
		//Clone the git repo and the correct branch
		stage('Clone') {				
			steps {													
				deleteDir()
				echo "Cloning branch: ${env.BRANCH_NAME}"
				gitClone env.BRANCH_NAME
			}
		}	
		
		stage('Initialize') {			
			steps {	
				script {
					echo "Initialize"
					script {
						def version = gitGetVersion env.BRANCH_NAME
						if (version == 0) {
							echo "Running pipeline with no new commits. Aborting pipeline."
							sh "exit 1"
						}
					}
				}
			}
		}
		
		
		//Unit test the code. Could be Junit or Concordion. If UNSTABLE the pipeline will exit.
		stage('Unit test') {
			steps {				
				script {
					echo "Run unit tests"	
					def version = gitGetVersion env.BRANCH_NAME
					echo "Buidling with: ${version}"					
					mavenUnitTest version, "-Pswarm"
				}
			}
			post {
				always {				
					junit 'target/surefire-reports/**/*.xml' 
				}
				unstable {		
					sendNotification currentBuild.result
					error("Unit test failed. Aborting pipeline")											
				}
			} 
		}
		
		//IT's. Component test, i.e. api/interfaces with mocks. If UNSTABLE the pipeline will exit.
		stage('Integration test') {
			when {					
				expression { env.BRANCH_NAME == "master" || env.BRANCH_NAME.startsWith("release-") }					
			}				
			steps {
				script {
					echo "Run integration test"
					def version = gitGetVersion env.BRANCH_NAME
					echo "Buidling with: ${version}"						
					sh "mvn wildfly-swarm:start verify -Dsurefire.skip=true -DbuildVersion='${version}' -Pswarm" //ugly! should be replaced by docker container
					//mavenIntegrationTest version, "-Pswarm"
				}
			}
			post {
				always {
					junit 'target/failsafe-reports/**/*.xml'
				}
				unstable {
					sendNotification currentBuild.result
					error("Integration test failed. Aborting pipeline")
				}
			}
		}

		//Build the code and deploy the artifact to Nexus if not a release branch.
		stage('Release') {
			when {					
				expression { env.BRANCH_NAME == "master" || env.BRANCH_NAME.startsWith("release-") }					
			}				
			steps {		
				script {					
					def version = gitGetVersion env.BRANCH_NAME
					currentBuild.displayName = version	
					echo "Buidling with: ${version}"					
					mavenDeploy version, "-gs /local/jenkins_tools/maven/settings_dev.xml"					
					gitTag version
				}
			}
		}				
	}
	
	
	post { 		
		success {
			echo "Arkiverar"
			archive "**/*"
		}
	}			
}