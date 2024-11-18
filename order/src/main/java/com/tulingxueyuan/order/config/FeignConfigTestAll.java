package com.tulingxueyuan.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*** @author wkx *
 *   @date 2024年11月18日 16:21
 *
 *   测试 全局配置 和 局部 配置的优先级
 *
 *  有限局部配置的config 优先级高
 *                   config全局配置 < config局部配置 <  配置文件
 **/
@Configuration
public class FeignConfigTestAll {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
