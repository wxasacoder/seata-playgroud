package org.wx.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wx.domain.Tbl;
import org.wx.dao.TblDao;
import org.wx.service.TblService;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class TblServiceImpl extends ServiceImpl<TblDao, Tbl> implements TblService {
    @Override
    public Boolean deductGoodsStock(String commodityCode, Integer count) {
        Tbl one = getOne(Wrappers.<Tbl>lambdaQuery()
                .eq(Tbl::getCommodityCode, commodityCode)
        );
        return update(Wrappers.<Tbl>lambdaUpdate()
                .eq(Tbl::getCommodityCode,commodityCode)
                .set(Tbl::getCount, one.getCount()  - count));
    }
   
}