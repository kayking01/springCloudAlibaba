package com.tulingxueyuan.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*** @author wkx * @date 2024年11月05日 13:58 */
@SpringBootApplication
//@RibbonClients(value = {
//    @RibbonClient(value = "stock-service",configuration = RibbonRandomRruleConfig.class)
//})
@EnableFeignClients
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }

//    @Bean
//    @LoadBalanced   // 负载均衡器 解析 服务名称对应服务地址
//    public RestTemplate getRestTemplate(RestTemplateBuilder builder){
//        return builder.build();
//    }
}
