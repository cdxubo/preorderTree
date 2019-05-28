package com.xu.testtree;

import com.xu.testtree.domain.TreeNode;
import com.xu.testtree.repository.TreeNodeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: testtree
 * @description:
 * @author: xuqigao
 * @date: 2019-05-23 15:49
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TesttreeApplication.class)
public class TestRepository {
    @Autowired
    private TreeNodeRepository repository;

    @Test
    public void testAddRoot(){
        repository.save(new TreeNode("root",0,1,2));
    }


    /**
     * 测试查找所有子节点
     */
    @Test
    public void testFindAllChildNodes(){
        List<TreeNode> allChildNodes = repository.findAllChildNodes(3);
        allChildNodes.forEach(System.out::println);
        System.out.println("*******************************************************");
        // transferToTree函数需传入当前节点的父节点id
        List<TreeNode> treeNodes = transferToTree(allChildNodes, 1);
        treeNodes.forEach(System.out::println);

    }


    /**
     * 测试查找所有父节点
     */
    @Test
    public void testFindAllFatherNodes(){
        List<TreeNode> allFatherNodes = repository.findAllFatherNodes(11);
        allFatherNodes.forEach(System.out::println);
        System.out.println("******************************************");
        List<TreeNode> treeNodes = transferToTree(allFatherNodes, 0);
        treeNodes.forEach(System.out::println);
    }


//    public void testDelete

    /**
     * 将平级树数据转化为具有层级关系的结构
     * @param treeNodes
     * @param parentId :父节点id
     * @return
     */
    public static List<TreeNode> transferToTree(List<TreeNode> treeNodes,Integer parentId){
        List<TreeNode> nodes = new ArrayList<>();
        for (TreeNode n1 : treeNodes) {
            if (parentId.equals(n1.getParentId())) {
                nodes.add(n1);
            }
            for (TreeNode n2 : treeNodes) {
                // 说明n2是n1的子节点
                if (n1.getId().equals(n2.getParentId())) {
                    n1.getChildNodes().add(n2);
                }
            }

        }
        return nodes;
    }

}
