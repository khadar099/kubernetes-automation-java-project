FROM openjdk:8
WORKDIR /app
EXPOSE 8181
ADD target/khadar-shoppingwebsite.jar /app/khadar-shoppingwebsite.jar
ENTRYPOINT ["java","-jar","/khadar-shoppingwebsite.jar"]
