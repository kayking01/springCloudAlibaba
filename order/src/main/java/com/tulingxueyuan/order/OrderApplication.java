package com.tulingxueyuan.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*** @author wkx * @date 2024年11月05日 13:58 */
@SpringBootApplication
//@RibbonClients(value = {
//    @RibbonClient(value = "stock-service",configuration = RibbonRandomRruleConfig.class)
//})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }

    @Bean
    @LoadBalanced   // 负载均衡器 解析 服务名称对应服务地址
    public RestTemplate getRestTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
