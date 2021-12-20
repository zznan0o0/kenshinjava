package org.kenshin.mybatisplussqlserver.controller;

import org.kenshin.mybatisplussqlserver.domain.Produceplan;
import org.kenshin.mybatisplussqlserver.service.ProduceplanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "test")
public class TestController {
    @Autowired
    ProduceplanService produceplanService;
    @GetMapping(value="getSqlserverTest")
    public Produceplan getSqlserverTest(){
        Long id = 1436491811999846400L;
        return this.produceplanService.getById(id);
    }
}
