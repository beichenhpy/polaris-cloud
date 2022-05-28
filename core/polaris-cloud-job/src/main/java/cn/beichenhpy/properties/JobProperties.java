package cn.beichenhpy.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/7/28 20:47
 */
@Data
@ConfigurationProperties(prefix = JobProperties.PREFIX)
public class JobProperties {
    public static final String PREFIX = "job";
    // 执行器AppName[选填]:执行器心跳注册分组依据,为空则关闭自动注册(xxl-job executor app name)
    private static final String DEFAULT_EXECUTOR_APP_NAME = "xxl-job-executor-sample";

    private static final int DEFAULT_EXECUTOR_PORT = 9999;
    private static final String DEFAULT_EXECUTOR_LOG_PATH = "/data/xxl-job/log/job-handler";
    private static final int DEFAULT_EXECUTOR_LOG_RETENTION_DAYS = 7;

    /**
     *  开启自动装配
     */
    private boolean enabled;

    private Executor executor = new Executor();
    /**
     * http://127.0.0.1:8080/xxl-job-admin
     */
    private String adminAddresses;
    /**
     * token
     */
    private String accessToken;

    @Data
    public static class Executor {
        /**
         *  执行器AppName[选填]:执行器心跳注册分组依据,为空则关闭自动注册(xxl-job executor app name)
         */
        private String appName = DEFAULT_EXECUTOR_APP_NAME;
        /**
         *  执行器IP[选填]:默认为空表示自动获取IP,多网卡时可手动设置指定IP,该IP不会绑定Host仅作为通讯实用.
         *  地址信息用于'执行器注册'和'调度中心请求并触发任务'.
         */
        private String ip;
        /**
         * 执行器端口号[选填]:小于等于0则自动获取,默认端口为9999,单机部署多个执行器时,注意要配置不同执行器端口.
         */
        private int port = DEFAULT_EXECUTOR_PORT;
        /**
         *  执行器运行日志文件存储磁盘路径[选填]:需要对该路径拥有读写权限,为空则使用默认路径.
         */
        private String logPath = DEFAULT_EXECUTOR_LOG_PATH;
        /**
         * 执行器日志文件保存天数[选填],过期日志自动清理,限制值大于等于3时生效;否则,如-1,关闭自动清理功能.
         */
        private int logRetentionDays = DEFAULT_EXECUTOR_LOG_RETENTION_DAYS;
    }
}
