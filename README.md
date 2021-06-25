# beichen-cloud


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


##file文件服务
两种方式
- 本地存储
- mongodb-GridFs


## support-redis
- 提供Redis-util(后续考虑解耦)

## support-common
- 提供utils
- 提供exception