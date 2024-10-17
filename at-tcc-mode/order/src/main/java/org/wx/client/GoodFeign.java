package org.wx.client;

import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuxin
 * @date 2024/10/16 22:48:45
 */
@FeignClient(contextId = "goods-service",value = "goods")
public interface GoodFeign {

    @PostMapping("/deduct/goods/count")
    Boolean deductGoodsStock(@RequestParam("commodityCode") String commodityCode,
                             @RequestParam("count") Integer count);


    @PostMapping("/deduct/goods/try")
    Boolean tryDeduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);

    @PostMapping("/deduct/goods/confirm")
    Boolean confirm(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);

    @PostMapping("/deduct/goods/cancel")
    Boolean cancel(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);


}
