package cn.beichenhpy.util;

import cn.beichenhpy.enums.SqlConstant;
import cn.beichenhpy.modal.Tree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public class TreeHelper<T extends Tree, M extends BaseMapper<T>> {
    @Autowired
    M mapper;
    //为内存计算使用，查询出来的所有树信息,线程安全
    private List<T> allRows;


    /**
     * 内存比较小时可以使用
     * 查询树形结构-通过数据库
     * @param isOneTimeMemory 是否为一次性加载到内存
     * @param floorParentId 输入(层数-1)层的id
     *                      [比如想要查第二层开始的树形结构，那么需要输入第一层的节点的id (从上到下数)]
     *                      注意：如果查顶层的则输入'-1'
     * @return 整个树
     */
    public List<T> getTree(String floorParentId, boolean isOneTimeMemory) {
        if (!isOneTimeMemory) {
            //设置TreeList
            List<T> parents = mapper.selectList(new QueryWrapper<T>().eq(SqlConstant.ID.getValue(), floorParentId));
            //遍历父目录
            for (T tree : parents) {
                List<T> children = getChildren(tree.getId(), isOneTimeMemory);
                tree.setChildren(children);
            }
            return parents;
        } else {
            allRows = new CopyOnWriteArrayList<>(mapper.selectList(null));
            List<T> parents = allRows.stream()
                    .filter(t -> floorParentId.equals(t.getParentId()))
                    .collect(Collectors.toList());
            //遍历父目录
            for (T tree : parents) {
                List<T> children = getChildren(tree.getId(), isOneTimeMemory);
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
                for (Tree tree : trees) {
                    //递归查询，直到return null结束
                    tree.setChildren(getChildren(tree.getId(), isOneTimeMemory));
                }
                return trees;
            }
        } else {
            //内存中筛选
            trees = allRows.stream()
                    .filter(t -> parentId.equals(t.getParentId()))
                    .collect(Collectors.toList());
            if (!trees.isEmpty()) {
                for (Tree tree : trees) {
                    //递归查询，直到return null结束
                    tree.setChildren(getChildren(tree.getId(), isOneTimeMemory));
                }
                return trees;
            }
        }
        //未查询到结束递归
        return null;
    }


}
