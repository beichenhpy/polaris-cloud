import cn.beichenhpy.ConsumerApplication;
import cn.beichenhpy.enums.IsOkEnum;
import cn.beichenhpy.mapper.TreeMapper;
import cn.beichenhpy.modal.Tree;
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
        List<Tree> tree = treeHelper.getTree(IsOkEnum.N.getNum());
        log.info("{}",tree);
        TreeInfo treeInfo = new TreeInfo();
        treeInfo.setId("1");
        log.info("{}",treeInfo);
    }
}
