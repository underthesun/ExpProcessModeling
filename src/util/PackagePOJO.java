/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
/**
 * 物品包Package对应的POJO类
 * @author shuai
 */
public class PackagePOJO extends ItemPOJO{
    private int[] itemIds;
    private int[] packIds;

    public int[] getItemIds() {
        return itemIds;
    }

    public void setItemIds(int[] itemIds) {
        this.itemIds = itemIds;
    }

    public int[] getPackIds() {
        return packIds;
    }

    public void setPackIds(int[] packIds) {
        this.packIds = packIds;
    }
    
}
