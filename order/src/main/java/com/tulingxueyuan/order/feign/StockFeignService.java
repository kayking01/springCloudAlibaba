package com.tulingxueyuan.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/*** @author wkx * @date 2024年11月18日 16:06
 * name : 指定rest接口接口对应的服务名
 * path : 指定调用rest接口所在StockController指定的@RequestMapping
 * */
@FeignClient(name = "stock-service",path = "/stock")
public interface StockFeignService {

    //声明需要调用的rest接口的对应的方法
    @RequestMapping("/reduce")
    String reduce();

}
/*
 * @RestController
@RequestMapping("/stock")
public class StockController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/reduce")
    public String reduce(){
        System.out.println("扣减库存"+port);
        return "扣减库存"+port;
    }
}
 **/