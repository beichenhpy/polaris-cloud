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


## file文件服务
两种方式
- 本地存储
- mongodb-GridFs


## support-redis
- 提供Redis-util-只提供Util,每个服务可能对应的db都是设置不同的，所以具体配置放到对应的服务中设置

## support-common
- 提供utils
- 提供exception

## support-mybatis
- 提供Mybatis-plus的一些配置

完成进度：  
- [x] gateway 
- [x] nacos服务发现
- [ ] nacos配置中心
- [x] seata
- [ ] sentinel
  - [x] 与openfeign连用
  - [ ] 超时熔断设置不生效解决
- [ ] openfeign
  - [x] client超时设置
  - [x] fallback设置
  - [ ] sentinel结合 
