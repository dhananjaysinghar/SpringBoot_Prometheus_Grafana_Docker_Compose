# Docker Compose with Prometheus, Grafana & ELK stacks

~~~
mvn clean install

docker-compose up -d app
docker-compose up -d prometheus
docker-compose up -d grafana
docker-compose up -d elasticsearch
docker-compose up -d elastichq
docker-compose up -d logstash
docker-compose up -d filebeat
docker-compose up -d kibana
docker-compose up -d metricbeat

Application
http://localhost:8080/swagger-ui.html

Prometheus
http://localhost:9090/targets

Grafana
http://localhost:3000/
~~~
