package org.kenshin.aoplog.controller;

import org.kenshin.aoplog.utils.NameUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(value = "/getName")
    public String getName(String name){
        name = NameUtil.sp(name);
        NameUtil nameUtil = new NameUtil();
        name = nameUtil.sp2(name);
        return name;
    }
}
