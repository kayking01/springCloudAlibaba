package com.tulingxueyuan.sentinel_demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*** @author wkx * @date 2024年11月20日 11:29 */
@RequestMapping("/sentinel")
@RestController
public class SentinelDemoController {

    @RequestMapping("/add")
    public String add(){
        return "add";
    }

}
