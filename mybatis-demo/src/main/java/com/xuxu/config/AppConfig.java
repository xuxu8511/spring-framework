package com.xuxu.config;

import com.xuxu.bean.BeanDefinitionRegistrar;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author xuxu8
 * @date 2022/6/21
 */
@ComponentScan("com.xuxu")
@Configuration
@Import(BeanDefinitionRegistrar.class)
public class AppConfig {
}
