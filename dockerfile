# Use a specific version of Tomcat for consistency
FROM tomcat:latest

# Copy the WAR file to the Tomcat webapps directory
#COPY ./ABCtechnologies-1.0.war /usr/local/tomcat/webapps/
COPY abc_tech.war /usr/local/tomcat/webapps/

# Change Tomcat port from 8080 to 9090
RUN sed -i 's/port="8080"/port="9090"/g' /usr/local/tomcat/conf/server.xml

# Expose the port (for documentation purposes)
EXPOSE 9090

# Command to run Tomcat
CMD ["catalina.sh", "run"]
