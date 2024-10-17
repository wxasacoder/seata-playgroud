package org.wx.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wx.domain.Tbl;
import org.wx.dao.TblDao;
import org.wx.service.TblService;
import org.springframework.stereotype.Service;

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

    @Override
    public Boolean tryDeduct(String commodityCode, Integer count) {
        Tbl one = getOne(Wrappers.<Tbl>lambdaQuery()
                .eq(Tbl::getCommodityCode, commodityCode)
        );
        return update(Wrappers.<Tbl>lambdaUpdate()
                .eq(Tbl::getCommodityCode,commodityCode)
                .set(Tbl::getCount, one.getCount()  - count)
                .set(Tbl::getFrozen,one.getFrozen() + count)
        );
    }

    @Override
    public Boolean confirm(String commodityCode, Integer count) {
        Tbl one = getOne(Wrappers.<Tbl>lambdaQuery()
                .eq(Tbl::getCommodityCode, commodityCode)
        );
        return update(Wrappers.<Tbl>lambdaUpdate()
                .eq(Tbl::getCommodityCode,commodityCode)
                .set(Tbl::getFrozen, one.getFrozen()  - count)
                .set(Tbl::getSold,one.getSold() + count)
        );
    }

    @Override
    public Boolean cancel(String commodityCode, Integer count) {
        Tbl one = getOne(Wrappers.<Tbl>lambdaQuery()
                .eq(Tbl::getCommodityCode, commodityCode)
        );
        return update(Wrappers.<Tbl>lambdaUpdate()
                .eq(Tbl::getCommodityCode,commodityCode)
                .set(Tbl::getCount, one.getCount() + count)
                .set(Tbl::getFrozen,one.getFrozen() - count)
        );
    }

}