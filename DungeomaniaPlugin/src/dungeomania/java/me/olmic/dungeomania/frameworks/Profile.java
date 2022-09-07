package me.olmic.dungeomania.frameworks;

import org.bukkit.inventory.ItemStack;

public class Profile {

    private Class clazz;
    private int level;
    private int xp;
    private ItemStack tome1;
    private ItemStack tome2;
    private ItemStack tome3;
    private ItemStack tome4;



    public Profile(Class clazz, int level, int xp, ItemStack tome1, ItemStack tome2, ItemStack tome3, ItemStack tome4) {
        this.clazz = clazz;
        this.level = level;
        this.xp = xp;
        this.tome1 = tome1;
        this.tome2 = tome2;
        this.tome3 = tome3;
        this.tome4 = tome4;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    //tomes
    public ItemStack getTome1() {
        return tome1;
    }
    public void setTome1(ItemStack tome1) {
        this.tome1 = tome1;
    }
    public ItemStack getTome2() {
        return tome2;
    }
    public void setTome2(ItemStack tome2) {
        this.tome2 = tome2;
    }
    public ItemStack getTome3() {
        return tome3;
    }
    public void setTome3(ItemStack tome3) {
        this.tome3 = tome3;
    }
    public ItemStack getTome4() {
        return tome4;
    }
    public void setTome4(ItemStack tome4) {
        this.tome4 = tome4;
    }
}