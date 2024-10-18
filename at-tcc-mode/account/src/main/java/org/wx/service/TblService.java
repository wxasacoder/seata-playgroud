package org.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.seata.spring.annotation.GlobalLock;
import org.wx.domain.Tbl;

/**
 *  Auto created by codeAppend plugin
 */
public interface TblService extends IService<Tbl> {

    Boolean deductMoney(Long userId, Integer money);


    boolean deductMoneyLocalMode(Long userId, Integer money);
}