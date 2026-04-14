# Build-vaihe
FROM maven:3.8.6-eclipse-temurin-17-focal AS build

# Kopioi Mavenin asetukset ja projektin metadata
COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package -DskipTests 

FROM eclipse-temurin:17-jre-focal

# Kopioi buildattu JAR-tiedosto
COPY --from=build /home/app/target/recipeapp-0.0.1-SNAPSHOT.jar /usr/local/lib/recipeapp.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/local/lib/recipeapp.jar"]