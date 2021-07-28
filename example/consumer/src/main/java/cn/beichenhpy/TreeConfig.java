package cn.beichenhpy;

import cn.beichenhpy.mapper.TreeMapper;
import cn.beichenhpy.modal.TreeInfo;
import cn.beichenhpy.util.TreeHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/7/28 00:17
 */
@Configuration
public class TreeConfig {
    @Bean
    public TreeHelper<TreeInfo, TreeMapper> treeHelper(){
        return new TreeHelper<>();
    }
}
