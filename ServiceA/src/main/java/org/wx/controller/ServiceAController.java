package org.wx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wx.configs.GlobalConfig;
import org.wx.service.EmployeeService;

import javax.annotation.Resource;

/**
 * @author wuxin
 * @date 2024/03/24 12:20:17
 */
@RestController
public class ServiceAController {

    @Resource
    private GlobalConfig globalConfig;

    @GetMapping("/config/prob")
    public  GlobalConfig probServiceProb(){
        return globalConfig;
    }

    @Resource
    private EmployeeService employeeService;

    @GetMapping("/save/employee")
    public void saveEmployee(){employeeService.saveEmployee();
    }


}
