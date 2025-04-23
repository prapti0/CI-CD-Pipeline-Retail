pipeline {
    agent any   // Make sure this is correctly placed here
    
    environment {
        DOCKER_IMAGE = "prapti989/ci-cd"
        WORK_DIR = "/var/lib/jenkins/workspace/pipeline1"
    }

    stages {
        stage('Code Checkout') {
            steps {
                // Checkout code
                git 'https://github.com/prapti0/CI-CD-Pipeline-Retail.git'
            }
        }

        stage('Code Compile') {
            steps {
                // Compile the code using Maven
                sh 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                // Run tests using Maven
                sh 'mvn test'
            }
        }

        stage('Build') {
            steps {
                // Package the application using Maven
                sh 'mvn package'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Copy the WAR file and build the Docker image
                sh 'cp ${WORK_DIR}/target/ABCtechnologies-1.0-1.0.war abc_tech.war'
                sh 'docker build -t ${DOCKER_IMAGE}:latest .'
            }
        }

        stage('Push Docker Image') {
            steps {
                // Push Docker image to DockerHub
                withDockerRegistry([credentialsId: "docker-id", url: ""]) {
                    sh 'docker push ${DOCKER_IMAGE}:latest'
                }
            }
        }

        stage('Deploy as container') {
            steps {
                // Deploy the Docker container locally
                sh 'docker run -itd -p 8081:8081 --name abcapp ${DOCKER_IMAGE}:latest'
            }
        }
         stage('Deploy to k8s cluster') {
            steps {
                withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                    // Create or update the Kubernetes deployment
                    sh 'kubectl delete deployment abcapp --ignore-not-found'
                    sh 'kubectl create deployment abcapp --image=${DOCKER_IMAGE}:latest'
                }
            }
        }
    }
}
