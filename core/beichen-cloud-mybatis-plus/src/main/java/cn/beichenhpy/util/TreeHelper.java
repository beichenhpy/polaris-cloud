package cn.beichenhpy.util;

import cn.beichenhpy.enums.IsOkEnum;
import cn.beichenhpy.enums.SqlConstant;
import cn.beichenhpy.modal.Content;
import cn.beichenhpy.modal.Tree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote M mapper T 为查询类
 * @since 2021/7/24 12:53
 */
public class TreeHelper<T extends Content, M extends BaseMapper<T>> {
    @Autowired
    M mapper;

    /**
     * 查询树形结构
     * @param rootParentId 输入一级目录对应的id
     * @return 整个树
     */
    public List<Tree> getTree(String rootParentId) {
        List<T> contents = mapper.selectList(new QueryWrapper<T>().eq(SqlConstant.PARENT_ID.getValue(), rootParentId));
        //设置TreeList
        List<Tree> parents = prepare(contents);
        //遍历父目录
        for (Tree tree : parents) {
            List<Tree> children = getChildren(tree.getContent().getId());
            tree.setChildren(children);
        }
        return parents;
    }

    /**
     * 准备数据
     *
     * @param contents 内容
     * @return 返回树
     */
    private List<Tree> prepare(List<T> contents) {
        List<Tree> parents = new ArrayList<>();
        for (Content content : contents) {
            Tree tree = new Tree();
            tree.setContent(content);
            parents.add(tree);
        }
        return parents;
    }

    /**
     * 获取子目录
     *
     * @param parentId 父目录id
     * @return 返回子目录
     */
    private List<Tree> getChildren(String parentId) {
        List<T> contents = mapper.selectList(new QueryWrapper<T>().eq(SqlConstant.PARENT_ID.getValue(), parentId));
        List<Tree> trees = prepare(contents);
        //模拟数据库查询
        if (!trees.isEmpty()) {
            for (Tree tree : trees) {
                //递归查询，直到return null结束
                tree.setChildren(getChildren(tree.getContent().getId()));
            }
            return trees;
        }
        //未查询到结束递归
        return null;
    }


}
