FROM openjdk:8
EXPOSE 9090
COPY /target/demo.txt /aap/demo.txt
ADD target/devops-integration.jar devops-integration.jar
ENTRYPOINT ["java","-jar","/devops-integration.jar"]
