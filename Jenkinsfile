def CONTAINER_NAME = "crud_test"
def ENV_NAME = getEnvName(env.BRANCH_NAME)
def CONTAINER_TAG =  getTag(env.BUILD_NUMBER, env.BRANCH_NAME)
def HTTP_PORT = getHTTPPort(env.BRANCH_NAME)
dev EMAIL_RECIPENTS = "hadday8@gmail.com"


node {

    try{

        stage('Initialize') {
            def dockerHome = tool 'DockerLatest'
            def mavenHome = tool 'MavenLatest'
            env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
        }

        stage('Checkout') {
            checkout scm
        }

        stage('Build with test') {
            sh "mvn clean install"
        }

    } finally {
        deleteDir()
    }

}