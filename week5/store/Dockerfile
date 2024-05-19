FROM bellsoft/liberica-openjdk-alpine-musl:17
WORKDIR /app
COPY target/store-0.0.1-SNAPSHOT.jar /app/store-0.0.1-SNAPSHOT.jar
EXPOSE 8081
CMD ["java", "-jar", "store-0.0.1-SNAPSHOT.jar"]
