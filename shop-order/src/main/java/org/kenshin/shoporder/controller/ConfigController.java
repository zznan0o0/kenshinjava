package org.kenshin.shoporder.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {
    @Value("${configTest}")
    String configTest;

    @Value("${configTest2}")
    String configTest2;

    @GetMapping("getConfigTest")
    public String getConfigTest(){
        return this.configTest+"|"+this.configTest2;
    }
}
