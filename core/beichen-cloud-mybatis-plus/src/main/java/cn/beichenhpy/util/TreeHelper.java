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
 * @see Tree 树数据结构
 * @since 2021/7/24 12:53
 */
public class TreeHelper<T extends TreeHelper.Tree, M extends BaseMapper<T>> {
    @Autowired
    M mapper;
    //为内存计算使用，查询出来的所有树信息,线程安全
    private List<T> allRows;

    /**
     * 缺省的方法查询树形结构 默认使用一次加载到内存
     *
     * @param floorParentId 输入(层数-1)层的id
     *                      [比如想要查第二层开始的树形结构，那么需要输入第一层的节点的id (从上到下数)]
     *                      注意：如果查顶层的则输入'-1'
     * @return 返回树形结构
     */
    public List<T> getTree(String floorParentId) {
        return getParent(floorParentId, true);
    }

    /**
     * 缺省的方法查询树形结构 默认使用一次加载到内存
     *
     * @param floorParentId   输入(层数-1)层的id
     *                        [比如想要查第二层开始的树形结构，那么需要输入第一层的节点的id (从上到下数)]
     *                        注意：如果查顶层的则输入'-1'
     * @param isOneTimeMemory 是否一次性加载到内存
     * @return 返回树形结构
     */
    public List<T> getTree(String floorParentId, boolean isOneTimeMemory) {
        return getParent(floorParentId, isOneTimeMemory);
    }


    /**
     * 内存比较小时可以使用
     * 查询树形结构-通过数据库
     *
     * @param isOneTimeMemory 是否为一次性加载到内存
     * @param floorParentId   输入(层数-1)层的id
     *                        [比如想要查第二层开始的树形结构，那么需要输入第一层的节点的id (从上到下数)]
     *                        注意：如果查顶层的则输入'-1'
     * @return 整个树
     */
    private List<T> getParent(String floorParentId, boolean isOneTimeMemory) {
        List<T> parents, children;
        if (!isOneTimeMemory) {
            //设置TreeList
            parents = mapper.selectList(new QueryWrapper<T>().eq(SqlConstant.PARENT_ID.getValue(), floorParentId));
            //遍历父目录
            for (T tree : parents) {
                children = getChildren(tree.getId(), false);
                tree.setChildren(children);
            }
            return parents;
        } else {
            allRows = new CopyOnWriteArrayList<>(mapper.selectList(null));
            parents = allRows.stream()
                    .filter(t -> floorParentId.equals(t.getParentId()))
                    .collect(Collectors.toList());
            //遍历父目录
            for (T tree : parents) {
                children = getChildren(tree.getId(), true);
                tree.setChildren(children);
            }
            return parents;
        }
    }


    /**
     * 获取子目录-通过遍历查询数据库
     *
     * @param parentId 父目录id
     * @return 返回子目录
     */
    private List<T> getChildren(String parentId, boolean isOneTimeMemory) {
        List<T> trees;
        if (!isOneTimeMemory) {
            trees = mapper.selectList(new QueryWrapper<T>().eq(SqlConstant.PARENT_ID.getValue(), parentId));
            if (!trees.isEmpty()) {
                for (T tree : trees) {
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
                for (T tree : trees) {
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
    public static class Tree{
        private String id;
        private String parentId;
        /**
         * 叶子节点，（下一级）
         */
        @TableField(exist = false)
        private List<? extends TreeHelper.Tree> children;
    }
}
