package com.tulingxueyuan.order.config;

import com.tulingxueyuan.order.feign.ProductFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*** @author wkx * @date 2024年11月21日 9:29 */
@Component
public class ProductFallBackService implements ProductFeignService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getProductById(String id) {
        log.error("feign 接口 熔断降级");
        return "进入兜底方法";
    }
}
