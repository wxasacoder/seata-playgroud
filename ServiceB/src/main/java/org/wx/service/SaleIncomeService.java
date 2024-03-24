package org.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wx.domain.SaleIncome;

/**
 *  Auto created by codeAppend plugin
 */
public interface SaleIncomeService extends IService<SaleIncome> {

    void saveIncome(Long id);
}