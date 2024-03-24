package org.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.wx.domain.Resourse;
import org.wx.dao.ResourseDao;
import org.wx.service.ResourseService;
import org.springframework.stereotype.Service;

/**
 * Auto created by codeAppend plugin
 */
@Service
public class ResourseServiceImpl extends ServiceImpl<ResourseDao, Resourse> implements ResourseService {
    @Override
    public void saveResource(Long id) {
        Resourse resourse = new Resourse();
        resourse.setEmployeeId(id);
        resourse.setResourseLevel(id.toString());
        save(resourse);
    }

    // use "baseMapper" to call jdbc
    // example: baseMapper.insert(entity);
    // example: baseMapper.selectByPage(params);
   
}