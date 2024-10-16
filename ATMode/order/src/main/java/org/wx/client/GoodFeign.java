package org.wx.client;

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



}
