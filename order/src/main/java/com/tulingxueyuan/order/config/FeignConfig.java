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
}
