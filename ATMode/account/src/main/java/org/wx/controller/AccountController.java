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

    @PostMapping("/deduct/try")
    public boolean tryDeduct(Long userId ,
                               Integer money){
       return tblService.tryDeduct(userId, money);
    }

    @PostMapping("/deduct/confirm")
    public boolean confirm(Long userId , Integer money){
       return tblService.confirm(userId, money);
    }

    @PostMapping("/deduct/cancel")
    public boolean cancel(Long userId ,
                               Integer money){
       return tblService.cancel(userId, money);
    }
}
