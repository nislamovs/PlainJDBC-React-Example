FROM openjdk:8-jdk-alpine

MAINTAINER nizamiislamovs@gmail.com

#Debugging
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n
EXPOSE 8000

#Necessary specially for jasper reports
RUN apk add ttf-dejavu

VOLUME /tmp
EXPOSE 8080
#./jdbcexample/
ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.jdbcexample.JdbcexampleApplication"]
