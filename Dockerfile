FROM quay.io/wildfly/wildfly:latest-jdk21
COPY target/jakartaee-conference-rest-api.war /opt/jboss/wildfly/standalone/deployments/
