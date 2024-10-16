package org.wx.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wx.service.TblService;

import javax.annotation.Resource;

/**
 * @author wuxin
 * @date 2024/10/16 22:35:00
 */
@RestController
public class AccountController {

    @Resource
    private TblService tblService;

    @PostMapping("/deduct/money")
    public boolean deductMoney(Long userId ,
                               Integer money){
       return tblService.deductMoney(userId, money);
    }
}
