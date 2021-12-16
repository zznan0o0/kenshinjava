package org.kenshin.mybatisplustest.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.kenshin.mybatisplustest.domain.TbLabel;
import org.kenshin.mybatisplustest.service.TbLabelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "test")
public class TestController {
    final TbLabelService tbLabelService;

    public TestController(TbLabelService tbLabelService) {
        this.tbLabelService = tbLabelService;
    }

    @GetMapping(value = "getName")
    public String getName(){
        return "kenshin";
    }
    @GetMapping(value = "getLabel")
    //从库
    public TbLabel getLabel(){
        return this.tbLabelService.getLabel();
    }
    @GetMapping(value = "getLabelById")
    //默认数据库
    public TbLabel getLabelById(){return this.tbLabelService.getById(1);}

    @GetMapping(value = "getLabelByIdInSlave")
    @DS("slave")
    //在接口层指定访问数据库组
    public TbLabel getLabelByIdInSlave(){return this.tbLabelService.getById(1);}
}
