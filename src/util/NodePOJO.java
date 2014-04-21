/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.awt.Dimension;
import java.awt.Point;

/**
 * 原子过程Node对应的POJO类
 * @author shuai
 */
public class NodePOJO {
    private String name;
    private int id;
    private int proId;
    private Point point;
    private Dimension dimension;
    private int[] preNodes;
    private int[] postNodes;

    public NodePOJO(){}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public int[] getPreNodes() {
        return preNodes;
    }

    public void setPreNodes(int[] preNodes) {
        this.preNodes = preNodes;
    }

    public int[] getPostNodes() {
        return postNodes;
    }

    public void setPostNodes(int[] postNodes) {
        this.postNodes = postNodes;
    }
    
}
