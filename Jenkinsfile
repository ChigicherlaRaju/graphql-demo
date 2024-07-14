/* groovylint-disable CompileStatic, GStringExpressionWithinString, NestedBlockDepth */
pipeline {
    agent any

    parameters {
        booleanParam(name: 'RUN_TESTS', defaultValue: false, description: 'Enable to run tests and Sonar analysis')
    }

    environment {
        APP_NAME = 'graphql-demo'
        DOCKER_IMAGE = "chigicherlaraju/${APP_NAME}:latest"
        SONAR_PROJECT_TOKEN = 'your_sonar_project_token'
        SONAR_PROJECT_KEY = 'your_sonar_project_key'
        SONAR_HOST_URL = 'http://localhost:9000'
        REPO_URL = 'https://github.com/ChigicherlaRaju/graphql-demo.git'
    }

    stages {
        stage('Verify Java, Maven & Git versions') {
            steps {
                script {
                    sh 'java -version'
                    sh 'mvn -version'
                    sh 'git --version'
                    echo "Branch name is: ${env.BRANCH_NAME}"
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
                        sh 'mvn --e -X clean verify sonar:sonar \
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
            steps {
                script {
                    echo "Building Docker image ${DOCKER_IMAGE_NAME}..."
                    sh 'docker build -t ${DOCKER_IMAGE}:latest .'
                }
            }
        }

        stage('Push Docker image') {
            steps {
                script {
                    echo 'Pushing Docker image ${DOCKER_IMAGE_NAME}...'
                    withDockerRegistry([credentialsId: 'DOCKER_CREDENTIALS', url: 'https://index.docker.io/v2/']) {
                        sh "docker push ${DOCKER_IMAGE}:latest"
                    }
                }
            }
        }
    }
}
