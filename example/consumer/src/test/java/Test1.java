import cn.beichenhpy.ConsumerApplication;
import cn.beichenhpy.mapper.TreeMapper;
import cn.beichenhpy.modal.TreeInfo;
import cn.beichenhpy.properties.JobProperties;
import cn.beichenhpy.util.TreeHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/7/24 13:27
 */
@Slf4j
@SpringBootTest(classes = ConsumerApplication.class)
public class Test1 {
    @Autowired
    TreeHelper<TreeInfo,TreeMapper> treeHelper;
    @Resource
    private JobProperties jobProperties;
    @Test
    public void test(){
        long start1 = System.currentTimeMillis();
        List<TreeInfo> tree2 = treeHelper.getTree(3);
        long end1 = System.currentTimeMillis() - start1;
        log.info("me:cost:{}",end1);
        log.info("result:me:{}",tree2);
        long start = System.currentTimeMillis();
        List<TreeInfo> tree = treeHelper.getTree(3);
        long end = System.currentTimeMillis() - start;
        log.info("me:cost:{}",end);
        log.info("result:me:{}",tree);
    }
    @Test
    public void test2(){
        assert jobProperties.isEnabled();
    }
}
