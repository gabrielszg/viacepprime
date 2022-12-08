FROM maven:3.3-jdk-8 as builder
COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN mvn clean install -f /usr/src/mymaven && mkdir /usr/src/wars/
RUN find /usr/src/mymaven/ -iname '*.war' -exec cp {} /usr/src/wars/ \;

FROM tomcat:8.5.84-jre8
COPY --from=builder /usr/src/wars/* /usr/local/tomcat/webapps/