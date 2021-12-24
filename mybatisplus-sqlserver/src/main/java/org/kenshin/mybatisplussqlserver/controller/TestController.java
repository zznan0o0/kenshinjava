package org.kenshin.mybatisplussqlserver.controller;


import com.baomidou.dynamic.datasource.annotation.DS;
import org.kenshin.mybatisplussqlserver.domain.ArrearsInfo;
import org.kenshin.mybatisplussqlserver.domain.EmployeeInfo;
import org.kenshin.mybatisplussqlserver.service.ArrearsInfoService;
import org.kenshin.mybatisplussqlserver.service.EmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "test")
public class TestController {
    @Autowired
    EmployeeInfoService employeeInfoService;

    @Autowired
    ArrearsInfoService arrearsInfoService;

    @GetMapping(value="getSqlserverTest")
    public Long getSqlserverTest(){
        Long id = 1436491811999846400L;
        return id;
    }

    @GetMapping(value = "/getEmployeeInfo")
    public EmployeeInfo getEmployeeInfo(){
        return this.employeeInfoService.getById(1252066001961488384L);
    }

    @GetMapping(value = "/getArrearsInfo")
    @DS("slave")
    public ArrearsInfo getArrearsInfo(){
        return this.arrearsInfoService.getById(1400352048100282368L);
    }
}
