package com.tulingxueyuan.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tulingxueyuan.order.feign.ProductFeignService;
import com.tulingxueyuan.order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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
    @Autowired
    ProductFeignService productFeignService;

    @RequestMapping("/add")
    public String add(){
        System.out.println("下单成功");
//        String msg = restTemplate.getForObject("http://stock-service/stock/reduce",String.class);
//        return "Hello World!" + msg;
        String msg = stockFeignService.reduce();
        String product = productFeignService.getProductById("1");
        return "Hello Feign!" + msg+product;
    }

    /**
     * @Author wkx
     * @Description //控制台 针对资源 flow 配置 Qps 限流规则
     *  统一异常处理后，
     *  针对 资源 flowQps 的限流策略 会 正常跳转 blockHandler 处理
     *  而针对RequestMapping的 限流策略 会由统一异常处理
     **/
    @RequestMapping("/flowQps")
    @SentinelResource(value = "flowQps",blockHandler = "flowBlockHandler")
    public String flowQps(){
        System.out.println("下单成功");

        return "Hello Sentinel!";
    }

    /**
     * @Author wkx
     * @Description //控制台 针对资源 flow 配置 线程数限流规则，通过不同浏览器同时访问，实现 限流规则
     **/
    @RequestMapping("/flowThreadNum")
//    @SentinelResource(value = "flowThreadNum",blockHandler = "flowBlockHandler")
    public String flowThreadNum() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);

        return "Hello Sentinel!";
    }

    private String flowBlockHandler(BlockException e){
        return "限流规则";
    }

    /**
     * @Author wkx
     * @Description // 热点规则 必须使用 @SentinelResource 注解
     *                  针对接口进行流控不会有效果的
     *  注意点： 参数索引下标 从0开始
     *          单机阈值 针对 所有参数
     *          参数值 限流阈值 针对 指定参数值
     *
     **/
    @RequestMapping("/get/{id}")
    @SentinelResource(value = "getById",blockHandler = "hotBlockHandler")
    public String getById(@PathVariable Integer id) throws InterruptedException {
        System.out.println("正常访问");
        return "正常访问";
    }

    public String hotBlockHandler(@PathVariable Integer id,BlockException be){
        be.printStackTrace();
        return "hotBlockHandle";
    }


}
