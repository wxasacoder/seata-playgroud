package org.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.wx.ServiceA;
import org.wx.clients.ServiceBClient;
import org.wx.clients.ServiceCClient;
import org.wx.domain.Employee;
import org.wx.dao.EmployeeDao;
import org.wx.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao, Employee> implements EmployeeService {

    @Resource
    private ServiceBClient serviceBClient;
    @Resource
    private ServiceCClient serviceCClient;

    @Override
    @GlobalTransactional(name = "saveEmployee")
    public void saveEmployee() {
        Employee employee = new Employee();
        employee.setName("jahjaha");
        employee.setSal(new BigDecimal(111111));
        employee.setDepart(1);
        save(employee);
        Long id = employee.getId();
        serviceBClient.saveEmployeeIncome(id);
        serviceCClient.saveResource(id);
    }

    // use "baseMapper" to call jdbc
    // example: baseMapper.insert(entity);
    // example: baseMapper.selectByPage(params);
   
}