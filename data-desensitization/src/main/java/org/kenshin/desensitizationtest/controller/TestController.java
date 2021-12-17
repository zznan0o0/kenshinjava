package org.kenshin.desensitizationtest.controller;

import org.kenshin.desensitizationtest.domain.TbTest;
import org.kenshin.desensitizationtest.service.TbTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/")
public class TestController {
    @Autowired
    TbTestService tbTestService;

    @GetMapping(value = "/test")
    public TbTest test(){
        TbTest tbTest = tbTestService.getById(2);
        System.out.println(tbTest);
        return tbTest;
    }
}
