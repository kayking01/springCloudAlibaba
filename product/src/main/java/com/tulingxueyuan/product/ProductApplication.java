package com.tulingxueyuan.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ProductApplication.class, args);
        while (true){
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            String config = applicationContext.getEnvironment().getProperty("user.config");
            String text = applicationContext.getEnvironment().getProperty("user.text");
            System.out.println("common name :"+userName+"; age: "+userAge+"; config: "+config+"; text: "+text);
            TimeUnit.SECONDS.sleep(1);
        }

    }

}
