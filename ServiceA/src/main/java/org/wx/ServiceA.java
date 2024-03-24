package org.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wuxin
 * @date ${YEAR}/${MONTH}/${DAY} ${HOUR}:${MINUTE}:${SECOND}
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@EnableFeignClients(basePackages = {"org.wx.clients"})
@MapperScan(basePackages = "org.wx.**.dao")
public class ServiceA {
    public static void main(String[] args) {
        SpringApplication.run(ServiceA.class,args);
    }
}