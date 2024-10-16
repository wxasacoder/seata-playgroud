package org.wx.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wx.domain.Tbl;
import org.wx.dao.TblDao;
import org.wx.service.TblService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Wrapper;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class TblServiceImpl extends ServiceImpl<TblDao, Tbl> implements TblService {
    @Override
    public Boolean deductMoney(Long userId, Integer money) {
        Tbl one = getOne(Wrappers.<Tbl>lambdaQuery().eq(Tbl::getUserId, userId));
        if(one.getMoney() - money < 0){
            throw new RuntimeException("钱不够哦");
        }
        return update(Wrappers.<Tbl>lambdaUpdate().set(Tbl::getMoney, one.getMoney() - money));
    }
   
}