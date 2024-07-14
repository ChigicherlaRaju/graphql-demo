/* groovylint-disable CompileStatic, GStringExpressionWithinString, NestedBlockDepth */
pipeline {
    agent any

    parameters {
        booleanParam(name: 'RUN_TESTS', defaultValue: false, description: 'Enable to run tests and Sonar analysis')
        booleanParam(name: 'RUN_DOCKER', defaultValue: false, description: 'Enable to create/publish the Docker image')
    }

    environment {
        APP_NAME = 'graphql-demo'
        DOCKER_IMAGE = "chigicherlaraju/${APP_NAME}"
        SONAR_PROJECT_TOKEN = 'your_sonar_project_token'
        SONAR_PROJECT_KEY = 'your_sonar_project_key'
        SONAR_HOST_URL = 'http://localhost:9000'
        REPO_URL = 'https://github.com/ChigicherlaRaju/graphql-demo.git'
    }

    stages {
        stage('Verify Basic Details') {
            steps {
                script {
                    echo "JAVA_HOME: ${env.JAVA_HOME}"
                    echo "MAVEN_HOME: ${env.MAVEN_HOME}"
                    echo "Branch name is: ${env.BRANCH_NAME}"
                    sh 'git --version'
                    sh 'java -version'
                    sh 'mvn --version'
                }
            }
        }

        stage('Checkout Code') {
            steps {
                script {
                    echo "Checking out code from ${env.REPO_URL}"
                    git branch: "${env.BRANCH_NAME}", url: "${env.REPO_URL}", credentialsId: 'GITHUB_CREDENTIALS'
                }
            }
        }

        stage('Run Test Cases and Publish Sonar Report') {
            when {
                expression { return params.RUN_TESTS }
            }
            steps {
                script {
                    echo 'echo "Executing test cases & Publishing Sonar report...'
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn --e -X -U clean verify sonar:sonar \
                            -Dsonar.projectKey=${env.SONAR_PROJECT_KEY} \
                            -Dsonar.host.url=${env.SONAR_HOST_URL} \
                            -Dsonar.login=${env.SONAR_PROJECT_TOKEN}'
                    }
                }
            }
        }

        stage('Build the application jar file') {
            steps {
                script {
                    echo 'Building application jar...'
                    sh 'mvn --e -X -U clean install -DskipTests'
                }
            }
        }

        stage('Build Docker image') {
            when {
                expression { return params.RUN_DOCKER }
            }
            steps {
                script {
                    echo "Building Docker image ${DOCKER_IMAGE}..."
                    sh 'docker build -t ${DOCKER_IMAGE}:latest .'
                }
            }
        }

        stage('Push Docker image') {
            when {
                expression { return params.RUN_DOCKER }
            }
            steps {
                script {
                    echo 'Pushing Docker image ${DOCKER_IMAGE}...'
                    withDockerRegistry([credentialsId: 'DOCKER_CREDENTIALS', url: 'https://index.docker.io/v1/']) {
                        sh 'docker push ${DOCKER_IMAGE}:latest'
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
