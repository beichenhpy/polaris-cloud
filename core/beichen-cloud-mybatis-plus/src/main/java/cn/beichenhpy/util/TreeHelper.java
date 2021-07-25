package cn.beichenhpy.util;

import cn.beichenhpy.enums.SqlConstant;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @see Tree 树数据结构
 * @since 2021/7/24 12:53
 */
public class TreeHelper<T extends TreeHelper.Tree, M extends BaseMapper<T>> {
    @Autowired
    M mapper;
    //为内存计算使用，查询出来的所有树信息,线程安全
    private List<T> allRows;
    //当前对应的层数
    private Integer currentFloorNum;

    /**
     * 通过api新增层级时调用，刷新成员变量AllRows
     */
    public void updateCache(){
        allRows = new CopyOnWriteArrayList<>(mapper.selectList(null));
    }

    /**
     * 缺省的方法查询树形结构 默认使用一次加载到内存
     *
     * @param floor 层数 从上到下
     * @return 返回树形结构
     */
    public List<T> getTree(Integer floor) {
        //加载一次,缓存机制
        if (allRows != null){
            updateCache();
        }
        //重置当前层数
        currentFloorNum = floor - 1;
        return getChildren(floor - 1, true);
    }

    /**
     * 缺省的方法查询树形结构 默认使用一次加载到内存
     *
     * @param floor           层数 从上到下
     * @param isOneTimeMemory 是否一次性加载到内存
     * @return 返回树形结构
     */
    public List<T> getTree(Integer floor, boolean isOneTimeMemory) {
        //加载一次，缓存机制
        if (isOneTimeMemory && allRows != null) {
            updateCache();
        }
        //重置当前层数
        currentFloorNum = floor - 1;
        return getChildren(floor - 1, isOneTimeMemory);
    }

    /**
     * @param isOneTimeMemory 是否为一次性加载到内存
     * @param parentId        负极目录id
     * @return 整个树
     */
    private List<T> getChildren(Integer parentId, boolean isOneTimeMemory) {
        List<T> trees;
        if (!isOneTimeMemory) {
            trees = mapper.selectList(new QueryWrapper<T>().eq(SqlConstant.PARENT_ID.getValue(), parentId));
            if (!trees.isEmpty()) {
                currentFloorNum++;
                //bugfix 由于递归深入会改变成员变量，所以要新建中间临时变量存储当前的层数
                int tempFloor = currentFloorNum;
                for (T tree : trees) {
                    tree.setCurrentFloorNum(tempFloor);
                    //递归查询，直到return null结束
                    tree.setChildren(getChildren(tree.getId(), false));
                }
                return trees;
            }
        } else {
            //内存中筛选
            trees = allRows.stream()
                    .filter(t -> parentId.equals(t.getParentId()))
                    .collect(Collectors.toList());
            if (!trees.isEmpty()) {
                currentFloorNum++;
                //bugfix 由于递归深入会改变成员变量，所以要新建中间临时变量存储当前的层数
                int tempFloor = currentFloorNum;
                for (T tree : trees) {
                    tree.setCurrentFloorNum(tempFloor);
                    //递归查询，直到return null结束
                    tree.setChildren(getChildren(tree.getId(), true));
                }
                return trees;
            }
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
