package org.wx.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalLock;
import org.wx.domain.Tbl;
import org.wx.dao.TblDao;
import org.wx.service.TblService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Wrapper;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class TblServiceImpl extends ServiceImpl<TblDao, Tbl> implements TblService {
    @Override
    public Boolean deductMoney(Long userId, Integer money) {
        Tbl one = getOne(Wrappers.<Tbl>lambdaQuery().eq(Tbl::getUserId, userId));
        if(one.getMoney() - money < 0){
            throw new RuntimeException("钱不够哦");
        }

        boolean update = update(Wrappers.<Tbl>lambdaUpdate().set(Tbl::getMoney, one.getMoney() - money));
        /**
         * 和下面的方式一测试 全局锁
         */
        try {
            Thread.sleep(200000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return update;
    }

    /**
     * GlobalLock 会对数据申请全局锁 否不会
     * @param userId
     * @param money
     * @return
     */
    @Override
    @GlobalLock
    public boolean queryForUpdate(Long userId, Integer money) {
        return update(Wrappers.<Tbl>lambdaUpdate().eq(Tbl::getUserId, userId).set(Tbl::getMoney, money));
    }

    /**
     * 加锁读必须加    @GlobalLock 否则无法完成全局锁的check
     * @param userId
     * @return
     */
    @Override
    @GlobalLock
    public BigDecimal queryForUpdate(Long userId) {
        return new BigDecimal(getOne(Wrappers.<Tbl>lambdaQuery().eq(Tbl::getUserId, userId).last("for update")).getMoney());
    }
}