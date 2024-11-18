package com.tulingxueyuan.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/*** @author wkx *
 *   @date 2024年11月18日 16:21
 *
 *   全局配置：当使用@Configuration 会将配置作用在所有的服务提供方
 *   局部配置：1. 通过配置类：如果只想针对某一个服务配置就不要加@Configuration
 *           2. 通过配置文件
 *
 *      局部配置示例
 **/
//@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

/*  契约配置 方式一：config里配置
    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }*/

    /*
     * Options 的第一个参数是连接的超时时间（ms），默认值是 2s；
     *  第二个是请求处理的超时时间（ms），默认值是 5s。
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }
     **/
}
