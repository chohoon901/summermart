FROM openjdk:11

ADD summermart-1.1.jar summermart.jar

ENTRYPOINT ["java", "-jar", "/summermart.jar"]