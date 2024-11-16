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
    nacos-discovery 依赖了 ribbon，可以不用再引入ribbon依赖

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
	
