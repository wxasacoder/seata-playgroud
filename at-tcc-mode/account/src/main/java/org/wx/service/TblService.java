package org.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wx.domain.Tbl;

import java.math.BigDecimal;

/**
 *  Auto created by codeAppend plugin
 */
public interface TblService extends IService<Tbl> {

    Boolean deductMoney(Long userId, Integer money);


    boolean queryForUpdate(Long userId, Integer money);


    BigDecimal queryForUpdate(Long userId);
}