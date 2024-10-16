package org.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.wx.client.AccountFeign;
import org.wx.client.GoodFeign;
import org.wx.domain.Tbl;
import org.wx.dao.TblDao;
import org.wx.service.TblService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class TblServiceImpl extends ServiceImpl<TblDao, Tbl> implements TblService {
    @Resource
    private GoodFeign goodFeign;

    @Resource
    private AccountFeign accountFeign;

    public static final Integer moneyPerGoods = 1;

    @Override
    @GlobalTransactional(name = "saveEmployee")
    public Boolean order(Long userId, String sku, Integer count) {
        Tbl tbl = new Tbl();
        tbl.setCount(count);
        tbl.setCommodityCode(sku);
        tbl.setMoney(count * moneyPerGoods);
        tbl.setUserId(userId);
        save(tbl);
        goodFeign.deductGoodsStock(sku, count);
        accountFeign.deductMoney(userId, count);
        return true;
    }

   
}