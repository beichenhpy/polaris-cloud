package cn.beichenhpy.config;

import cn.beichenhpy.properties.JobProperties;
import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/7/28 20:51
 */
@EnableConfigurationProperties({JobProperties.class})
@Configuration
@AllArgsConstructor
public class JobConfiguration {
    private final JobProperties jobProperties;

    @Bean
    @ConditionalOnProperty(prefix = JobProperties.PREFIX, value = "enabled", havingValue = "true")
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobExecutor = new XxlJobSpringExecutor();
        xxlJobExecutor.setAdminAddresses(jobProperties.getAdminAddresses());
        xxlJobExecutor.setAppname(jobProperties.getExecutor().getAppName());
        xxlJobExecutor.setIp(jobProperties.getExecutor().getIp());
        xxlJobExecutor.setPort(jobProperties.getExecutor().getPort());
        xxlJobExecutor.setAccessToken(jobProperties.getAccessToken());
        xxlJobExecutor.setLogPath(jobProperties.getExecutor().getLogPath());
        xxlJobExecutor.setLogRetentionDays(jobProperties.getExecutor().getLogRetentionDays());
        return xxlJobExecutor;
    }
}
