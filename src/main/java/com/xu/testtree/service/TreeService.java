package com.xu.testtree.service;

import com.xu.testtree.domain.TreeNode;

import java.util.List;

public interface TreeService {

    /**
     * 获得rootId节点下的所有子节点
     * @param rootId
     * @return
     */
    TreeNode getTree(int rootId);

    /**
     * 获得所有根节点
     * @return
     */
    List<TreeNode> getRoots();


    /**
     * 添加一个分类
     * @param nodeName
     * @param parentId
     * @return
     */
    int addCategory(String nodeName, int parentId);

    /**
     * 删除一个分类
     * @param id
     * @return
     */
    void deleteCategory(int id);

    /**
     * 添加一个分类列表作为一个全新的分类
     * @param category
     * @return
     */
    int addCategoryList(TreeNode category);


    /**
     * 增加节点
     * @param treeNode
     */
    void addNode(TreeNode treeNode);
}
