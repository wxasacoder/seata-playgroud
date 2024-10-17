package org.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wx.domain.Tbl;

/**
 *  Auto created by codeAppend plugin
 */
public interface TblService extends IService<Tbl> {

    Boolean deductMoney(Long userId, Integer money);

    boolean tryDeduct(Long userId, Integer money);

    boolean confirm(Long userId, Integer money);

    boolean cancel(Long userId, Integer money);
}