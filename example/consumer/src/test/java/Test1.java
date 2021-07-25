import cn.beichenhpy.ConsumerApplication;
import cn.beichenhpy.mapper.TreeMapper;
import cn.beichenhpy.modal.TreeInfo;
import cn.beichenhpy.util.TreeHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    @Test
    public void test(){
        long start = System.currentTimeMillis();
        List<TreeInfo> tree = treeHelper.getTree("1",false);
        long end = System.currentTimeMillis() - start;
        log.info("db:cost:{}",end);
        log.info("result:db:{}",tree);
        long start1 = System.currentTimeMillis();
        List<TreeInfo> tree2 = treeHelper.getTree("1",true);
        long end1 = System.currentTimeMillis() - start1;
        log.info("me:cost:{}",end1);
        log.info("result:me:{}",tree2);
    }
}
