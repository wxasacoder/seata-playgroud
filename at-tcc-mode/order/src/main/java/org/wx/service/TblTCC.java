package org.wx.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author wuxin
 * @date 2024/10/17 21:20:03
 */

/**
 * 注意！！！！！！增加了此注解的interface的实现只能使用TCC模式
 * 如果尝试使用XA或者AT则会导致事务失效
 */
@LocalTCC
public interface TblTCC {

    @TwoPhaseBusinessAction(name = "prepareOrder", commitMethod = "confirmOrder", rollbackMethod = "cancelOrder",useTCCFence = true)
    Boolean prepareOrder(BusinessActionContext context,
                         @BusinessActionContextParameter(paramName = "userId") Long userId,
                         @BusinessActionContextParameter(paramName = "sku") String sku,
                         @BusinessActionContextParameter(paramName = "count") Integer count);


    Boolean confirmOrder(BusinessActionContext businessActionContext);


    Boolean cancelOrder(BusinessActionContext businessActionContext);


}
