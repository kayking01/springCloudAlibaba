package com.tulingxueyuan.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*** @author wkx * @date 2024年11月18日 14:18 */
@Configuration
public class RibbonRandomRruleConfig {
    @Bean
    public IRule irule(){
        return new RandomRule();
    }
}
