package org.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wx.domain.Resourse;

/**
 *  Auto created by codeAppend plugin
 */
public interface ResourseService extends IService<Resourse> {

    void saveResource(Long id);
}