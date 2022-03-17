FROM openjdk:11
ADD /build/libs/manifest-service-0.0.1-SNAPSHOT.jar manifest-backend.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar" , "manifest-backend.jar"]