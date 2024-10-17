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
public interface TblService extends IService<Tbl> {
    /**
     * for test at or xa
     * @param userId
     * @param sku
     * @param count
     * @return
     */
    Boolean order(Long userId, String sku, Integer count);
}