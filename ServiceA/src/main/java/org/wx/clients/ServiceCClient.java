package org.wx.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuxin
 * @date 2024/03/24 13:54:13
 */
@FeignClient(value = "service-c")
public interface ServiceCClient {
    @PostMapping("/employee/resource/save")
    void saveResource(@RequestParam("id") Long id);
}
