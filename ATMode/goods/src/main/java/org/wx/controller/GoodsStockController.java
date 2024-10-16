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
     return    tblService.deductGoodsStock(commodityCode,count);
    }



}
