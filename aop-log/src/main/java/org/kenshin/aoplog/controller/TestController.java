package org.kenshin.aoplog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(value = "/getName")
    public String getName(String name){
        return  name + "12123123";
    }
}
