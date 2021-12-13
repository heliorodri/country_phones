FROM openjdk:8

ADD ./build/libs/exercise-0.0.1-SNAPSHOT.war ./test.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./jumia/test.jar"]