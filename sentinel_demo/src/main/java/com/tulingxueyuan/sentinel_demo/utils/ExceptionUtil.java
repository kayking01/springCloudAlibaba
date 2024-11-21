package com.tulingxueyuan.sentinel_demo.utils;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/*** @author wkx * @date 2024年11月21日 9:29 */
@Slf4j
public class ExceptionUtil {

    public static void handleException(BlockException ex){
        log.info("Test限流逻辑");
//        return "限流了";
    }
    public static void fallBackHandForAdd(Throwable ex){
        log.info("Test异常处理逻辑");
//        return "限流了";
    }

}
