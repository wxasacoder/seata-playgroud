package org.wx.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wx.service.SaleIncomeService;

import javax.annotation.Resource;

/**
 * @author wuxin
 * @date 2024/03/24 14:27:56
 */
@RestController
public class ServiceBController
{

    @Resource
    private SaleIncomeService incomeService;

    @PostMapping("/employee/income/save")
    public void saveEmployeeIncome(@RequestParam("id") Long id){
        incomeService.saveIncome(id);
    }
}
