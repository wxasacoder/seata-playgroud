package org.wx.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuxin
 * @date 2024/03/24 13:54:13
 */
@FeignClient(value = "service-b")
public interface ServiceBClient {

    @PostMapping("/employee/income/save")
    void saveEmployeeIncome(@RequestParam("id") Long id);
}
