package org.wx.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.stereotype.Service;
import org.wx.domain.Tbl;
import org.wx.service.TblService;
import org.wx.service.TblServiceTCC;

import javax.annotation.Resource;

/**
 * @author wuxin
 * @date 2024/10/18 10:08:57
 */
@Service
public class TblServiceTCCImpl implements TblServiceTCC {

    @Resource
    private TblService tblService;

    @Override
    public boolean tryDeduct(BusinessActionContext context, Long userId, Integer money) {
        Tbl one = tblService.getOne(Wrappers.<Tbl>lambdaQuery().eq(Tbl::getUserId, userId));
        if(one.getMoney() - money < 0){
            throw new RuntimeException("钱不够哦");
        }
        return tblService.update(Wrappers.<Tbl>lambdaUpdate()
                .set(Tbl::getLockMoney, money)
                .set(Tbl::getMoney, one.getMoney() - money)

        );
    }

    @Override
    public boolean confirm(BusinessActionContext context) {
        Long userId = new Long((Integer) context.getActionContext("userId"));
        Integer money = (Integer) context.getActionContext("money");
        Tbl one = tblService.getOne(Wrappers.<Tbl>lambdaQuery().eq(Tbl::getUserId, userId));
        if(one.getMoney() - money < 0){
            throw new RuntimeException("钱不够哦");
        }
        return tblService.update(Wrappers.<Tbl>lambdaUpdate().set(Tbl::getLockMoney, one.getLockMoney() - money));    }

    @Override
    public boolean cancel(BusinessActionContext context) {
        Long userId = new Long((Integer) context.getActionContext("userId"));
        Integer money = (Integer) context.getActionContext("money");
        Tbl one = tblService.getOne(Wrappers.<Tbl>lambdaQuery().eq(Tbl::getUserId, userId));
        if (one.getMoney() - money < 0) {
            throw new RuntimeException("钱不够哦");
        }
        return tblService.update(Wrappers.<Tbl>lambdaUpdate()
                .set(Tbl::getLockMoney, one.getMoney() - money)
                .set(Tbl::getMoney, one.getMoney() + money)

        );
    }
}
