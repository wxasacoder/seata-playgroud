package org.wx.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.stereotype.Service;
import org.wx.domain.Tbl;
import org.wx.service.TblService;
import org.wx.service.TblTCCService;

import javax.annotation.Resource;

/**
 * @author wuxin
 * @date 2024/10/18 10:19:50
 */
@Service
public class TblTCCServiceImpl implements TblTCCService {

    @Resource
    private TblService tblService;


    @Override
    public Boolean tryDeduct(BusinessActionContext context, String commodityCode, Integer count) {
        Tbl one = tblService.getOne(Wrappers.<Tbl>lambdaQuery()
                .eq(Tbl::getCommodityCode, commodityCode)
        );
        return tblService.update(Wrappers.<Tbl>lambdaUpdate()
                .eq(Tbl::getCommodityCode,commodityCode)
                .set(Tbl::getCount, one.getCount()  - count)
                .set(Tbl::getFrozen,one.getFrozen() + count)
        );
    }

    @Override
    public Boolean confirm(BusinessActionContext context) {
        String commodityCode = (String)context.getActionContext("commodityCode");
        Integer count = (Integer)context.getActionContext("count");
        Tbl one = tblService.getOne(Wrappers.<Tbl>lambdaQuery()
                .eq(Tbl::getCommodityCode, commodityCode)
        );
        return tblService.update(Wrappers.<Tbl>lambdaUpdate()
                .eq(Tbl::getCommodityCode,commodityCode)
                .set(Tbl::getFrozen, one.getFrozen()  - count)
                .set(Tbl::getSold,one.getSold() + count)
        );
    }

    @Override
    public Boolean cancel(BusinessActionContext context) {
        String commodityCode = (String)context.getActionContext("commodityCode");
        Integer count = (Integer)context.getActionContext("count");
        Tbl one = tblService.getOne(Wrappers.<Tbl>lambdaQuery()
                .eq(Tbl::getCommodityCode, commodityCode)
        );
        return tblService.update(Wrappers.<Tbl>lambdaUpdate()
                .eq(Tbl::getCommodityCode,commodityCode)
                .set(Tbl::getCount, one.getCount() + count)
                .set(Tbl::getFrozen,one.getFrozen() - count)
        );
    }


}
