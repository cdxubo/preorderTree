package com.xu.testtree;

import com.xu.testtree.domain.TreeNode;
import com.xu.testtree.service.TreeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: testtree
 * @description:
 * @author: xuqigao
 * @date: 2019-05-23 16:38
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TesttreeApplication.class)
public class TestTreeServiceImpl {
    @Autowired
    private TreeService service;

    /**
     * 测试新增节点
     */
    @Test
    public void testAddNode(){
        TreeNode node = new TreeNode("pad",2);
        service.addNode(node);
    }

    @Test
    public void testDeleteCategory(){
        service.deleteCategory(2);
    }
}
