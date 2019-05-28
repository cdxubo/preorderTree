package com.xu.testtree.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: testtree
 * @description: 树节点实体类
 * @author: xuqigao
 * @date: 2019-05-23 15:30
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "treenode")
public class TreeNode {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 组织机构名称
     */
    @Column(name = "name", nullable = false, length = 10)
    private String name;

    /**
     * 父级组织id(根节点父级id为0)
     */
    @Column(name = "parent_id", nullable = false)
    private Integer parentId;

    @Column(name = "lft", nullable = false)
    private Integer lft;

    @Column(name = "rgt", nullable = false)
    private Integer rgt;

    @Transient
    private List<TreeNode> childNodes = new ArrayList<>();

    public TreeNode(String name, Integer parentId, Integer lft, Integer rgt) {
        this.name = name;
        this.parentId = parentId;
        this.lft = lft;
        this.rgt = rgt;
    }

    public TreeNode(String name, Integer parentId) {
        this.name = name;
        this.parentId = parentId;
    }
}
