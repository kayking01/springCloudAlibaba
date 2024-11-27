package com.tulingxueyuan.order.feign;


import com.tulingxueyuan.order.config.ProductFallBackService;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/*** @author wkx * @date 2024年11月18日 17:11
 * @FeignClient 没有声明 configuration 所以使用 全局的配置
 * */
@FeignClient(name = "product-service",path = "/product",fallback = ProductFallBackService.class)
public interface ProductFeignService {

    /**
     * @Author wkx
     * @Description feign中调用rest接口较为严格 @PathVariable("id") 的”id“ 需要显示声明
     **/
//    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
//    String getProductById(@PathVariable("id") String id);
    @RequestLine(value = "GET /get/{id}")
    String getProductById(@Param("id") String id);
}
/*
    @RequestMapping("/{id}")
    public String getProductById(@PathVariable Long id){
        return "获取商品"+id;
    }*/
