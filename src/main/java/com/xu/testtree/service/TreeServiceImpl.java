package com.xu.testtree.service;

import com.xu.testtree.domain.TreeNode;
import com.xu.testtree.repository.TreeNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: testtree
 * @description:
 * @author: xuqigao
 * @date: 2019-05-23 15:37
 **/
@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeNodeRepository repository;


    @Override
    public TreeNode getTree(int rootId) {
        return null;
    }

    @Override
    public List<TreeNode> getRoots() {

        return null;
    }

    @Override
    public int addCategory(String nodeName, int parentId) {
        return 0;
    }


    /**
     * 删除节点及其子节点
     * @param id
     */
    @Transactional
    @Override
    public void deleteCategory(int id) {
        // 1. 获取需删除的节点信息
        Optional<TreeNode> optionalNode = repository.findById(id);
        Integer lft = 0;
        Integer rgt = 0;
        Integer width = 0;
        if (optionalNode.isPresent()){
            TreeNode node = optionalNode.get();
            lft = node.getLft();
            rgt = node.getRgt();
            width = rgt-lft+1;
        }
        // 2.修改当前节点及其子节点的lft和rgt为-1
        repository.updateDeletedNodes(lft,rgt);
        // 3. 修改右值
        repository.updateRgtWhenDeleted(width,rgt);
        // 4. 修改左值
        repository.updateLftWhenDeleted(width,rgt);
    }

    @Override
    public int addCategoryList(TreeNode category) {
        return 0;
    }




    /**
     *  新增节点
     * @param treeNode
     */
    @Transactional
    @Override
    public void addNode(TreeNode treeNode) {
        Optional<TreeNode> optionalTreeNode = repository.findById(treeNode.getParentId());
        if (!optionalTreeNode.isPresent()) {
            //
            repository.save(new TreeNode(treeNode.getName(),treeNode.getParentId(),1,2));
        }else {
            //父节点
            TreeNode parentNode = optionalTreeNode.get();
            // 父节点的rgt
            Integer rgt = parentNode.getRgt();
            // 修改右值
            repository.updateRgtWhenAdd(rgt);
            // 修改左值
            repository.updateLftWhenAdd(rgt);
            // 保存节点
            repository.save(new TreeNode(treeNode.getName(),treeNode.getParentId(),rgt,rgt+1));

        }

    }



}
