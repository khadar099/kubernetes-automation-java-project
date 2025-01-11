FROM openjdk:8
EXPOSE 9090
args
args
ADD target/devops-integration.jar devops-integration.jar
ENTRYPOINT ["java","-jar","/devops-integration.jar"]
