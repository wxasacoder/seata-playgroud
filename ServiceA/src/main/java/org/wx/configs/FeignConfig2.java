//package org.wx.configs;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.context.annotation.Configuration;
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import io.seata.core.context.RootContext;
////内部调用需要token校验，这边将页面端的token和平台属性转发给feign
//@Configuration
//public class FeignConfig2 implements RequestInterceptor {
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        String xid = RootContext.getXID();
//        if (StringUtils.isNotBlank(xid)) {
//			System.out.println("feign 获得分布式事务xid："+xid);
//		}
//        requestTemplate.header("fescar-XID", xid);
//    }
//}