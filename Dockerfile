FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/CRUD_Test.jar

WORKDIR /opt/app

COPY ${JAR_FILE} CRUD_Test.jar

COPY entrypoint.sh entrypoint.sh

RUN chmod 755 entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]