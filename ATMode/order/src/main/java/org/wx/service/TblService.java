package org.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.wx.domain.Tbl;

/**
 *  Auto created by codeAppend plugin
 */
@LocalTCC
public interface TblService extends IService<Tbl> {

    Boolean order(Long userId, String sku, Integer count);

    @TwoPhaseBusinessAction(name = "prepareOrder", commitMethod = "confirmOrder", rollbackMethod = "cancelOrder",useTCCFence = true)
    Boolean prepareOrder(BusinessActionContext context,
                         @BusinessActionContextParameter(paramName = "userId") Long userId,
                         @BusinessActionContextParameter(paramName = "sku") String sku,
                         @BusinessActionContextParameter(paramName = "count") Integer count);


    Boolean confirmOrder(BusinessActionContext businessActionContext);


    Boolean cancelOrder(BusinessActionContext businessActionContext);
}