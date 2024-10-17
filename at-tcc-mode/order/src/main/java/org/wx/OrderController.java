package org.wx;

import io.seata.spring.annotation.GlobalTransactional;
import org.glassfish.json.api.BufferPool;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wx.service.TblService;
import org.wx.service.TblTCC;

import javax.annotation.Resource;

/**
 * @author wuxin
 * @date 2024/10/16 23:09:24
 */
@RestController
public class OrderController {

    @Resource
    private TblTCC tblTCC;

    @Resource
    private TblService tblService;

    /**
     * at模式和xa 模式不是不能共存的原因是其数据库链接池的代理方式不一样
     * 这两种模式的切换只需要在配置文件中切换即可 seata 默认是at
     * seata 默认的是at模式
     * @param userId
     * @param sku
     * @param count
     * @return
     */
    @PostMapping("/order-at-xa")
    public Boolean ordering(Long userId, String sku, Integer count){
        return tblService.order(userId, sku, count);
    }

    /**
     * tcc 模式
     * tcc 本质上是 2pc 的是服务化， 事物的控制在xa中是需要数据库支持，在这里彻底迁移到服务段， 由开发者来书写tryConfirm cancel的能力
     * @param userId
     * @param sku
     * @param count
     * @return
     */
    @PostMapping("/order-tcc")
    @GlobalTransactional
    public Boolean orderingTCC(Long userId, String sku, Integer count){
        return tblTCC.prepareOrder(null,userId, sku, count);
    }

}


