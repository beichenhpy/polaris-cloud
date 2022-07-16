package cn.beichenhpy.annotation;

import cn.beichenhpy.config.AutoWebConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote beichen-web入口
 * @since 2021/7/23 22:14
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableDiscoveryClient
@Import(AutoWebConfiguration.class)
public @interface EnablePolarisWeb {
}
