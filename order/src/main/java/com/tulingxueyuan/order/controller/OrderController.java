package com.tulingxueyuan.order.controller;

import com.tulingxueyuan.order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @author wkx
 * @date 2024年11月05日 11:48
 */
@RestController
@RequestMapping("/order")

public class OrderController {

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    StockFeignService stockFeignService;

    @RequestMapping("/add")
    public String add(){
        System.out.println("下单成功");
//        String msg = restTemplate.getForObject("http://stock-service/stock/reduce",String.class);
//        return "Hello World!" + msg;
        String msg = stockFeignService.reduce();
        return "Hello Feign!" + msg;
    }
}
