package org.wx.controller;

import lombok.Lombok;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wx.service.TblService;
import org.wx.service.TblTCCService;

import javax.annotation.Resource;

/**
 * @author wuxin
 * @date 2024/10/16 22:39:31
 */
@RestController
public class GoodsStockController {
    @Resource
    private TblService tblService;
    @Resource
    private TblTCCService tblTCCService;


    @PostMapping("/deduct/goods/count")
    public Boolean deductGoodsStock(String commodityCode, Integer count){
     return   tblService.deductGoodsStock(commodityCode,count);
    }

    /**
     * for tcc
     * @param commodityCode
     * @param count
     * @return
     */
    @PostMapping("/deduct/goods/try")
    public Boolean tryDeduct(String commodityCode, Integer count){
     return  tblTCCService.tryDeduct(null, commodityCode,count);
    }



}
