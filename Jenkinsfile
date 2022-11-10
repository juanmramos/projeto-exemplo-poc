node {
    def projectName = "${env.JOB_BASE_NAME}"
    def WORKSPACE = "/var/jenkins_home/workspace/crs-api"
    def dockerImageTag = "${projectName}${env.BUILD_NUMBER}"
try{
    //notifyBuild('STARTED')
    stage('Clone Repo') {
        // for display purposes
        // Get some code from a GitLab repository
        git url: 'https://dev.novaxs.com.br/crs/core.git',
            credentialsId: '201b1853-2ce4-47b2-bf60-a4ec8073fd3e',
            branch: 'main'
     }
     stage("Compilation") {
        parallel 'Compilation': {
            sh "./mvnw -v"
            sh "./mvnw clean install -DskipTests"
        }
    }
    stage('Build docker') {
         dockerImage = docker.build("${projectName}:${env.BUILD_NUMBER}")
    }
    stage('Deploy docker'){
          echo "Docker Image Tag Name: ${dockerImageTag}"
          sh "docker stop ${projectName} || true && docker rm ${projectName} || true"
          sh "echo ${PWD}"
          sh "echo ${HOME}"
          sh "docker run --name ${projectName} -v /var/jenkins_home/log:/log -d -p 8082:8080 ${projectName}:${env.BUILD_NUMBER}"
    }
}catch(e){
    currentBuild.result = "FAILED"
    throw e
}finally{
    //notifyBuild(currentBuild.result)
 }
}


def notifyBuild(String buildStatus = 'STARTED'){
  
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'
  
  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def now = new Date()
  
  // message
  def subject = "${buildStatus}, Job: ${env.JOB_NAME} FRONTEND - Deployment Sequence: [${env.BUILD_NUMBER}] "
  def summary = "${subject} - Check On: (${env.BUILD_URL}) - Time: ${now}"
  def subject_email = "Spring boot Deployment"
  def details = """<p>${buildStatus} JOB </p>
    <p>Job: ${env.JOB_NAME} - Deployment Sequence: [${env.BUILD_NUMBER}] - Time: ${now}</p>
    <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME}</a>"</p>"""
  
  // Email notification
  emailext (
     to: "marc@novaxs.com.br",
     subject: subject_email,
     body: details,
     recipientProviders: [[$class: 'DevelopersRecipientProvider']]
  )
    
}