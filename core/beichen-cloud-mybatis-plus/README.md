# mybatis-plus
添加依赖后需要在配置文件中添加
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: beichenhpy
    url: jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&autoReconnect=true
    hikari:
      # 连接池最大连接数
      maximum-pool-size: 12
      # 空闲时保持最小连接数
      minimum-idle: 5
      # 空闲连接存活时间
      idle-timeout: 300000
      # 连接超时时间
      connection-timeout: 20000
      # 测试sql
      connection-test-query: select 1
      pool-name: beichen
mybatis-plus:
  configuration:
    map-underscore-to-camel-case: true
    #生产关闭
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    banner: false
    db-config:
      id-type: assign_id
```