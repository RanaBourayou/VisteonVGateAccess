pipeline{
agent any 
  tools {
    maven 'M2_HOME'
  }
  stages{
    stage('checkout'){
      steps{
          checkout scm
      }
    }

    stage('build'){
      steps{
        sh 'mvn -B clean package'
      }
    }
    stage('Test'){
      steps{
        sh 'mvn test'
      }
    }
    

    
  }



  
}
