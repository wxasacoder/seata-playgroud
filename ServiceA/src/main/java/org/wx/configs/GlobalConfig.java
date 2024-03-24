package org.wx.configs;

import lombok.Data;
import org.aspectj.weaver.ast.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wuxin
 * @date 2024/03/24 12:21:45
 */
@Data
@Component
@ConfigurationProperties(prefix = "config.service-a")
public class GlobalConfig {


    private String testRefresh;

    private List<String> testConfigList;

    private TestObjectInject testObjectInject;

    @Data
    public static class TestObjectInject{

        private String propertiesA;

        private List<Integer> propertiesB;

    }
}
