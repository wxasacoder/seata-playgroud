package org.wx.client;

import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuxin
 * @date 2024/10/16 22:48:45
 */
@FeignClient(contextId = "account-service",value = "account")
public interface AccountFeign {



    @PostMapping("/deduct/money")
    boolean deductMoney(@RequestParam("userId") Long userId ,
                        @RequestParam("money") Integer money);


    @PostMapping("/deduct/try")
    @TwoPhaseBusinessAction(name = "TccActionOne", commitMethod = "confirm", rollbackMethod = "cancel")
    boolean tryDeduct(@RequestParam("userId") Long userId ,
                      @RequestParam("money") Integer money);

    @PostMapping("/deduct/confirm")
    boolean confirm(@RequestParam("userId") Long userId ,
                    @RequestParam("money") Integer money);

    @PostMapping("/deduct/cancel")
    boolean cancel(@RequestParam("userId") Long userId ,
                   @RequestParam("money") Integer money);
}
