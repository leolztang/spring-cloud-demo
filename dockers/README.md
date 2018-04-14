# spring-cloud-demo
* **dns**     
    模拟内网域名解析，不能使用localhost作为容器内服务的服务发现和注册域名
    
* **eureka**   
    两个注册服务集群，使用-v关联宿主机hosts配置，模拟内网域名解析。其他java参数覆盖了application.yml的配置
