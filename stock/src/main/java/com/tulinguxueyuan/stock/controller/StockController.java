package com.tulinguxueyuan.stock.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*** @author wkx * @date 2024年11月05日 13:37 */
@RestController
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
