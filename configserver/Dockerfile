FROM maven:3-openjdk-17

LABEL maintainer="wangyaning<357208746@qq.com>"

ENV TZ=Asia/Shanghai
ENV MYPATH configserver

WORKDIR /$MYPATH

COPY . .

COPY settings.xml /root/.m2/settings.xml

RUN mvn clean package -Dmaven.test.skip=true

EXPOSE 8888

ENTRYPOINT ["java","-jar","/configserver/target/configserver-0.0.1-SNAPSHOT.jar"]