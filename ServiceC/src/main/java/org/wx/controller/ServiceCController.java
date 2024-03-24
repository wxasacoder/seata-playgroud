package org.wx.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wx.service.ResourseService;

import javax.annotation.Resource;

/**
 * @author wuxin
 * @date 2024/03/24 14:27:56
 */
@RestController
public class ServiceCController
{

    @Resource
    private ResourseService resourseService;

    @PostMapping("/employee/resource/save")
    public void saveResource(@RequestParam("id") Long id){
        resourseService.saveResource(id);
    }
}
