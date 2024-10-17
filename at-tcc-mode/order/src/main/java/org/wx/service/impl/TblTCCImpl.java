package org.wx.service.impl;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.BusinessActionContextUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wx.client.AccountFeign;
import org.wx.client.GoodFeign;
import org.wx.domain.Tbl;
import org.wx.service.TblService;
import org.wx.service.TblTCC;

import javax.annotation.Resource;

/**
 * @author wuxin
 * @date 2024/10/17 21:23:07
 */
@Service
public class TblTCCImpl implements TblTCC {


    @Resource
    private TblService tblService;
    @Resource
    private GoodFeign goodFeign;
    @Resource
    private AccountFeign accountFeign;


    @Override
    public Boolean prepareOrder(BusinessActionContext context,
                                @BusinessActionContextParameter(paramName = "userId") Long userId,
                                @BusinessActionContextParameter(paramName = "sku") String sku,
                                @BusinessActionContextParameter(paramName = "count") Integer count) {
        Tbl tbl = new Tbl();
        tbl.setCount(count);
        tbl.setCommodityCode(sku);
        tbl.setMoney(count * 1);
        tbl.setUserId(userId);
        tblService.save(tbl);
        BusinessActionContextUtil.addContext("order", tbl.getId());
        goodFeign.tryDeduct(sku, count);
        accountFeign.tryDeduct(userId,count * 1);
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
        tblService.removeById((Long) businessActionContext.getActionContext().get("orderId"));
        return true;
    }

}
