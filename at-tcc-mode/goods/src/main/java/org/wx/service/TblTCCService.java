package org.wx.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author wuxin
 * @date 2024/10/18 10:13:52
 */
@LocalTCC
public interface TblTCCService {

    @TwoPhaseBusinessAction(name = "tryDeductInventory", commitMethod = "confirm", rollbackMethod = "cancel",useTCCFence = true)
    Boolean tryDeduct(BusinessActionContext context, @BusinessActionContextParameter("commodityCode") String commodityCode,
                      @BusinessActionContextParameter("count") Integer count);

    Boolean confirm(BusinessActionContext context);

    Boolean cancel(BusinessActionContext context);
}
