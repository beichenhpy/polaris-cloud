package cn.beichenhpy.util;

import cn.beichenhpy.enums.SqlConstant;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote M mapper T 为Tree的子类 用于查询树状层级结构
 * <br>重要：1. 数据库对应的parentId 和 id 需要为int类型 2. 层数为从上到下
 * <br> 不足：需要手动配置bean，不然统一配置会无法注入泛型
 * @see Tree 树数据结构
 * @since 2021/7/24 12:53
 */
public class TreeHelper {

    /**
     * 双检锁赋值
     */
    public void init(){
        if (allRows == null) {
            synchronized (TreeHelper.class) {
                if (allRows == null) {
                    allRows = new CopyOnWriteArrayList<>(mapper.selectList(null));
                }
            }
        }
    }

    /**
     * 通过api新增层级时调用，刷新成员变量AllRows
     */
    public void updateCache() {
        init();
    }

    /**
     * 缺省的方法查询树形结构 默认使用一次加载到内存
     *
     * @param floor 层数 从上到下
     * @return 返回树形结构
     */
    public List<T> getTree(Integer floor) {
        int parentId = floor - 1;
        init();
        //重置当前层数
        return getChildren(parentId);
    }


    /**
     * @param parentId 负极目录id
     * @return 整个树
     */
    private List<T> getChildren(Integer parentId) {
        List<T> trees;
        int floor = parentId;
        trees = allRows.stream()
                .filter(t -> parentId.equals(t.getParentId()))
                .collect(Collectors.toList());
        if (!trees.isEmpty()) {
            floor++;
            for (T tree : trees) {
                tree.setCurrentFloorNum(floor);
                //递归查询，直到return null结束
                tree.setChildren(getChildren(tree.getId()));
            }
            return trees;
        }
        //未查询到结束递归
        return null;
    }

    /**
     * @author beichenhpy
     * @version 1.0.0
     * @apiNote 树及结构 子类可进行扩充
     * @since 2021/7/24 12:57
     */
    @Data
    public static class Tree {
        @TableId(type = IdType.AUTO)
        private Integer id;
        private Integer parentId;
        /**
         * 当前目录层级
         */
        @TableField(exist = false)
        private Integer currentFloorNum;
        /**
         * 叶子节点，（下一级）
         */
        @TableField(exist = false)
        private List<? extends TreeHelper.Tree> children;
    }
}
