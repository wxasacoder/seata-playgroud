package org.wx;

import org.glassfish.json.api.BufferPool;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wx.service.TblService;

import javax.annotation.Resource;

/**
 * @author wuxin
 * @date 2024/10/16 23:09:24
 */
@RestController
public class OrderController {


    @Resource
    private TblService tblService;

    @PostMapping("/order")
    public Boolean ordering(Long userId, String sku, Integer count){
        return tblService.order(userId, sku, count);
    }

}


