# beichen-cloud
版本  
SpringCloud 2020.0.3  
SpringCloudAlibaba 2021.1  

spring-cloud-gateway api网关  
spring-cloud-openfeign (无ribbon和hystrix)  
spring-cloud-loadbalancer (替代ribbon负载均衡)  
spring-cloud-alibaba-sentinel (替代Hystrix熔断)  
spring-cloud-alibaba-seata 分布式事务  
spring-cloud-alibaba-nacos 1.4.2 服务发现及配置中心

## nacos  + gateway 
- nacos作为服务发现及配置中心
- gateway作为网关
### nacos配置
打算以namespace作为beichencloud-dev/beichencloud-prod的分割
- 配置文件以 bootstrap.yaml来进行active激活
- bootstrap-dev/bootstrap-prod来区分dev/prod的namespace配置
- 在nacos中只需在不同的namespace中添加不同的以application-name命名的配置文件

### gateway配置
- cors跨域
- authFilter


# 重构中。。。
