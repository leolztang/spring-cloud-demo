#eureka server集群
docker run --name eureka-server-1 -d -v /etc/hosts:/etc/hosts -p 1111:8080 eureka-server java -Djava.security.egd=file:/dev/./urandom -Dserver.port=8080 -Deureka.instance.hostname=eureka-1 -Deureka.client.serviceUrl.defaultZone=http://eureka-2:1112/eureka/ -jar eureka-server.jar


docker run --name eureka-server-2 -d -v /etc/hosts:/etc/hosts -p 1112:8080 eureka-server java -Djava.security.egd=file:/dev/./urandom -Dserver.port=8080 -Deureka.instance.hostname=eureka-2 -Deureka.client.serviceUrl.defaultZone=http://eureka-1:1111/eureka/ -jar eureka-server.jar

