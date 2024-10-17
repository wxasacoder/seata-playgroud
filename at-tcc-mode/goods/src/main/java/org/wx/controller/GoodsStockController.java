package org.wx.controller;

import lombok.Lombok;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wx.service.TblService;

import javax.annotation.Resource;

/**
 * @author wuxin
 * @date 2024/10/16 22:39:31
 */
@RestController
public class GoodsStockController {
    @Resource
    private TblService tblService;


    @PostMapping("/deduct/goods/count")
    public Boolean deductGoodsStock(String commodityCode, Integer count){
     return   tblService.deductGoodsStock(commodityCode,count);
    }

    @PostMapping("/deduct/goods/try")
    public Boolean tryDeduct(String commodityCode, Integer count){
     return   tblService.tryDeduct(commodityCode,count);
    }

    @PostMapping("/deduct/goods/confirm")
    public Boolean confirm(String commodityCode, Integer count){
     return  tblService.confirm(commodityCode,count);
    }

    @PostMapping("/deduct/goods/cancel")
    public Boolean cancel(String commodityCode, Integer count){
     return  tblService.cancel(commodityCode,count);
    }


}
