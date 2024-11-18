package com.tulingxueyuan.order.Interceptor.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*** @author wkx * @date 2024年11月18日 19:32 */
public class FeignCustomInterceptor implements RequestInterceptor {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.info("Feign 拦截器！");
    }
}
