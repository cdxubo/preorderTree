package com.xu.testtree.repository;

import com.xu.testtree.domain.TreeNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: testtree
 * @description:
 * @author: xuqigao
 * @date: 2019-05-23 15:39
 **/
@Repository
public interface TreeNodeRepository extends JpaRepository<TreeNode,Integer> {

    @Transactional
    @Modifying
    @Query(value = "update treenode set rgt=rgt+2 where rgt>=?",nativeQuery=true)
    void updateRgtWhenAdd(Integer rgt);

    @Transactional
    @Modifying
    @Query(value = "update treenode set lft=lft+2 where lft>=?",nativeQuery = true)
    void updateLftWhenAdd(Integer rgt);


    /**
     * 查找所有子节点
     * @param id
     * @return
     */
    @Query(value = "SELECT c1.* FROM treenode c1, treenode c2 WHERE c1.lft >= c2.lft AND c2.rgt >= c1.rgt AND c2.id = ? ORDER BY c1.lft ASC",
            nativeQuery = true)
    List<TreeNode> findAllChildNodes(Integer id);

    /**
     * 查找所有父节点
     * @param id
     * @return
     */
    @Query(value = "SELECT c1.* FROM treenode c1, treenode c2 WHERE c1.lft <= c2.lft AND c1.rgt >= c2.rgt AND c2.id = ? ORDER BY c1.lft ASC",
            nativeQuery = true)
    List<TreeNode> findAllFatherNodes(Integer id);

    @Transactional
    @Modifying
    @Query(value = "update treenode set lft=-1,rgt=-1 where lft between ? and ?",nativeQuery = true)
    void updateDeletedNodes(Integer lft,Integer rgt);

    @Transactional
    @Modifying
    @Query(value = "update treenode set rgt=rgt-? where rgt>?",nativeQuery = true)
    void updateRgtWhenDeleted(Integer width,Integer rgt);


    @Transactional
    @Modifying
    @Query(value = "update treenode set lft=lft-? where lft>?",nativeQuery = true)
    void updateLftWhenDeleted(Integer width,Integer rgt);
}
