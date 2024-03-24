package org.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wx.domain.SaleIncome;
import org.wx.dao.SaleIncomeDao;
import org.wx.service.SaleIncomeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class SaleIncomeServiceImpl extends ServiceImpl<SaleIncomeDao, SaleIncome> implements SaleIncomeService {
    @Override
    public void saveIncome(Long id) {

        SaleIncome saleIncome = new SaleIncome();
        saleIncome.setEmployeeId(id);
        saleIncome.setIncome(new BigDecimal(1111));
        save(saleIncome);
    }

    // use "baseMapper" to call jdbc
    // example: baseMapper.insert(entity);
    // example: baseMapper.selectByPage(params);
   
}