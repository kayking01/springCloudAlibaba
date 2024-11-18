package com.tulingxueyuan.product.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*** @author wkx * @date 2024年11月18日 16:58 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public String getProductById(@PathVariable("id") String id){
        return "获取商品"+id;
    }
}
