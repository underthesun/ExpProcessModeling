/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;

/**
 * 数据对应的POJO类
 *
 * @author shuai
 */
public class DataPOJO {

    private ArrayList<ArrayList<ItemPOJO>> itemPOJO;
    private ArrayList<ArrayList<ItemPOJO>> itemOrientedPOJO;
    private ArrayList<ArrayList<PackagePOJO>> packPOJO;
    private ArrayList<ArrayList<PackagePOJO>> packOrientedPOJO;
    private int packNum;
    private int itemNum;

    public ArrayList<ArrayList<ItemPOJO>> getItemPOJO() {
        return itemPOJO;
    }

    public void setItemPOJO(ArrayList<ArrayList<ItemPOJO>> itemPOJO) {
        this.itemPOJO = itemPOJO;
    }

    public ArrayList<ArrayList<PackagePOJO>> getPackPOJO() {
        return packPOJO;
    }

    public void setPackPOJO(ArrayList<ArrayList<PackagePOJO>> packPOJO) {
        this.packPOJO = packPOJO;
    }

    public int getPackNum() {
        return packNum;
    }

    public void setPackNum(int packNum) {
        this.packNum = packNum;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public ArrayList<ArrayList<ItemPOJO>> getItemOrientedPOJO() {
        return itemOrientedPOJO;
    }

    public void setItemOrientedPOJO(ArrayList<ArrayList<ItemPOJO>> itemOrientedPOJO) {
        this.itemOrientedPOJO = itemOrientedPOJO;
    }

    public ArrayList<ArrayList<PackagePOJO>> getPackOrientedPOJO() {
        return packOrientedPOJO;
    }

    public void setPackOrientedPOJO(ArrayList<ArrayList<PackagePOJO>> packOrientedPOJO) {
        this.packOrientedPOJO = packOrientedPOJO;
    }
}
