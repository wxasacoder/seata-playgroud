package org.wx;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wuxin
 * @date ${YEAR}/${MONTH}/${DAY} ${HOUR}:${MINUTE}:${SECOND}
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@MapperScan(basePackages = {"org.wx.**.dao"})
public class ServiceB {
    public static void main(String[] args) {
        SpringApplication.run(ServiceB.class,args);
    }
}