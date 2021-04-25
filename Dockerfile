# Important commands Docker
#----------------------------
#docker build -f Dockerfile -t productservice .
#docker images
#docker run -p 8080:8080 productservice

#docker container ls
#docker rm <containerId>
#docker rmi <imageId>

# Start with a base image containing Java runtime
#FROM java:8

# Make port 9095 available to the world outside this container
#EXPOSE 4201

#ADD target/ProductService-0.0.1-SNAPSHOT.jar ProductService-0.0.1-SNAPSHOT.jar

# Run the jar file
#ENTRYPOINT ["java","-jar","ProductService-0.0.1-SNAPSHOT.jar"]

FROM java:8
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]



#docker-compose up -d app
#docker-compose up -d prometheus
#docker-compose up -d grafana
#docker-compose up -d elasticsearch
#docker-compose up -d elastichq
#docker-compose up -d logstash
#docker-compose up -d filebeat
#docker-compose up -d kibana
#docker-compose up -d metricbeat
