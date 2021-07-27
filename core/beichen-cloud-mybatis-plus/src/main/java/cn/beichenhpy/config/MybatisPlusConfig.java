package cn.beichenhpy.config;

import cn.beichenhpy.handler.FiledInjectHandler;
import cn.beichenhpy.util.TreeHelper;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote MybatisPlusConfig description：
 * @since 2021/6/25 16:41
 */
@Configuration
@EnableTransactionManagement
@Import({FiledInjectHandler.class})
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
