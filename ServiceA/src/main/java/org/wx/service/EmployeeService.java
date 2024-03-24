package org.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wx.domain.Employee;

/**
 *  Auto created by codeAppend plugin
 */
public interface EmployeeService extends IService<Employee> {

    void saveEmployee();
}