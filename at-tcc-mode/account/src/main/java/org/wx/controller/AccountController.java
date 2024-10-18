package org.wx.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wx.service.TblService;
import org.wx.service.TblServiceTCC;

import javax.annotation.Resource;

/**
 * @author wuxin
 * @date 2024/10/16 22:35:00
 */
@RestController
public class AccountController {

    @Resource
    private TblService tblService;
    @Resource
    private TblServiceTCC tblServiceTCC;

    @PostMapping("/deduct/money")
    public boolean deductMoney(Long userId ,
                               Integer money){
       return tblService.deductMoney(userId, money);
    }

    @PostMapping("/deduct/money-local-mode")
    public boolean deductMoneyLocalMode(Long userId ,
                               Integer money){
       return tblService.deductMoneyLocalMode(userId, money);
    }

    /**
     * for tcc
     * @param userId
     * @param money
     * @return
     */
    @PostMapping("/deduct/try")
    public boolean tryDeduct(Long userId ,
                               Integer money){
       return tblServiceTCC.tryDeduct(null,userId, money);
    }
}
