FROM maven:3.9.6-eclipse-temurin-17 as builder
COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN mvn clean install -f /usr/src/mymaven && mkdir /usr/src/wars/
RUN find /usr/src/mymaven/ -iname '*.war' -exec cp {} /usr/src/wars/ \;

FROM quay.io/wildfly/wildfly
COPY --from=builder /usr/src/wars/* /opt/jboss/wildfly/standalone/deployments/