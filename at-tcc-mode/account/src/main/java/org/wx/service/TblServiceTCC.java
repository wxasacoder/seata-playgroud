package org.wx.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author wuxin
 * @date 2024/10/18 10:08:33
 */
@LocalTCC
public interface TblServiceTCC {

    @TwoPhaseBusinessAction(name = "tryDeductAccountMoney", commitMethod = "confirm", rollbackMethod = "cancel",useTCCFence = true)
    boolean tryDeduct(BusinessActionContext context, @BusinessActionContextParameter("userId") Long userId,
                      @BusinessActionContextParameter("money") Integer money);

    boolean confirm(BusinessActionContext context);

    boolean cancel(BusinessActionContext context);

}
