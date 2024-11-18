# Getting Started
## springCloud Alibaba first start
  父模块
  
##### 版本对应关系
https://sca.aliyun.com/docs/2.2.x/overview/version-explain/?spm=5176.29160081.0.0.74805c72qmeSNb

版本：
   1. springboot：2.3.12.RELEASE
   2. spring cloud: Hoxton.SR12
   3. spring cloud alibaba : 2.2.9.RELEASE

##### 云原生 脚手架
https://start.aliyun.com/bootstrap.html

##### nacos：稳定版本
https://github.com/alibaba/nacos/releases/download/2.2.3/nacos-server-2.2.3.tar.gz
需要 2.1.0 版本？ 先用2.2.3的试一下

##### Ribbon：
nacos-discovery 依赖了 ribbon，可以不用再引入ribbon依赖。     
实现负载均衡策略的两种方式：  
    1.配置类   
        不能写在@SpringbootApplication注解的@CompentScan扫描得到的地方，否则自定义的配置类就会被所有的 RibbonClients共享。    
        不建议这么使用，推荐yml方式      
    2. 修改.yaml配置文件  
```yaml
        stock-service:        
            ribbon:      
                # 指定使用Nacos提供的负载均衡策略（优先调用同一集群的实例，基于随机&权重） 开启后 nacos 控制台可以配置 权重       
                NFLoadBalancerRuleClassName: com.alibaba.cloud.nacos.ribbon.NacosRule           
```
默认懒加载模式，开启饥饿加载：     
```yaml
ribbon:
  eager-load:
  #开启ribbon饥饿加载
    enabled:true
  #配置mall-user使用ribbon饥饿加载，多个使用逗号分隔
    clients:stock-service
```
##### OpenFeign:
引入依赖：
```xml
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
```

##### Sentinel:
```xml
    <dependency>
        <groupId>com.alibaba.csp</groupId>
        <artifactId>sentinel-core</artifactId>
        <version>1.8.0</version>
    </dependency>
```


##### Seata：
https://github.com/apache/incubator-seata/releases/download/v2.0.0/seata-server-2.0.0.tar.gz

##### gateway:
引入依赖：
```xml
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>
```

##### skywalking:
https://archive.apache.org/dist/skywalking/
	
