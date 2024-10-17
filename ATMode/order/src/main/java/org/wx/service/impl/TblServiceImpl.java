package org.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.BusinessActionContextUtil;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.aop.framework.AopContext;
import org.springframework.transaction.annotation.Transactional;
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



    @Override
    public Boolean prepareOrder(BusinessActionContext context,
                                @BusinessActionContextParameter(paramName = "userId") Long userId,
                                @BusinessActionContextParameter(paramName = "sku") String sku,
                                @BusinessActionContextParameter(paramName = "count") Integer count) {
        Tbl tbl = new Tbl();
        tbl.setCount(count);
        tbl.setCommodityCode(sku);
        tbl.setMoney(count * moneyPerGoods);
        tbl.setUserId(userId);
        save(tbl);
        BusinessActionContextUtil.addContext("order", tbl.getId());
        goodFeign.tryDeduct(sku, count);
        accountFeign.tryDeduct(userId,count * moneyPerGoods);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean confirmOrder(BusinessActionContext businessActionContext) {
        goodFeign.confirm((String) businessActionContext.getActionContext().get("sku"),
                (Integer) businessActionContext.getActionContext().get("count"));
        accountFeign.confirm(new Long((Integer) businessActionContext.getActionContext().get("userId")),
                (Integer) businessActionContext.getActionContext().get("count"));
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean cancelOrder(BusinessActionContext businessActionContext) {
        goodFeign.cancel((String) businessActionContext.getActionContext().get("sku"),
                (Integer) businessActionContext.getActionContext().get("count"));
        accountFeign.cancel(new Long((Long) businessActionContext.getActionContext().get("userId")),
                (Integer) businessActionContext.getActionContext().get("count"));
        removeById((Long) businessActionContext.getActionContext().get("orderId"));
        return true;
    }



}