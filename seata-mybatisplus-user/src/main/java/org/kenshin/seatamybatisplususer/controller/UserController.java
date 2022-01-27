package org.kenshin.seatamybatisplususer.controller;

import org.kenshin.seatamybatisplususer.domain.TbUser;
import org.kenshin.seatamybatisplususer.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    TbUserService tbUserService;

    @GetMapping("/deduct")
    @Transactional
    public String deduct(Integer id, Integer money){
        TbUser tbUser = this.tbUserService.getById(id);
        tbUser.setMoney(tbUser.getMoney() - money);
        this.tbUserService.updateById(tbUser);
        return "{\"ok\": true}";
    }
}
