/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**
 * 
 * @author shuai
 */
public class ItemPOJO {
    private int id;
    private int packId;
    private int dstNodeId;
    private long lastTimeChecked;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemPOJO other = (ItemPOJO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPackId() {
        return packId;
    }

    public void setPackId(int packId) {
        this.packId = packId;
    }

    public int getDstNodeId() {
        return dstNodeId;
    }

    public void setDstNodeId(int dstNodeId) {
        this.dstNodeId = dstNodeId;
    }

    public long getLastTimeChecked() {
        return lastTimeChecked;
    }

    public void setLastTimeChecked(long lastTimeChecked) {
        this.lastTimeChecked = lastTimeChecked;
    }
    
}
