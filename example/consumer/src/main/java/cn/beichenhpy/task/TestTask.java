package cn.beichenhpy.task;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/7/29 22:11
 */
@Slf4j
@Component
public class TestTask {
    @XxlJob(value = "log")
    public void test(){
        log.info("定时任务执行");
    }
}
