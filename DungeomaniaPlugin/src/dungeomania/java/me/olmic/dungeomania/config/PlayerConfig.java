package me.olmic.dungeomania.config;

import me.olmic.dungeomania.Main;
import me.olmic.dungeomania.frameworks.Class;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static me.olmic.dungeomania.frameworks.Items.EMPTY_TOME;

public class PlayerConfig extends Config {

    public PlayerConfig(Main main) {
        super(main, "players.yml");
    }

    public Class getClass(UUID id) {
        return Class.getClassByName(getString(id.toString() + ".class"));
    }

    public void setClass(UUID id, Class clazz) {
        set(id + ".class", clazz.getRawName());
    }

    public int getLevel(UUID id) {
        return  getInt(id.toString() + ".level");
    }

    public void setLevel(UUID id, int level) {
        set(id.toString() + ".level", level);
    }

    public int getXP(UUID id) {
        return getInt(id.toString() + ".xp");
    }

    public void setXp(UUID id, int xp) {
        set(id.toString() + ".xp", xp);
    }

    //tomes
    public void setTome1(UUID id, ItemStack tome) {

        ItemMeta meta = tome.getItemMeta();

        Material type = tome.getType();
        String name = meta.getDisplayName();
        List<String> lore = meta.getLore();

        set(id.toString() + ".Tomes.tome1.type", type.toString());
        set(id.toString() + ".Tomes.tome1.name", name);
        set(id.toString() + ".Tomes.tome1.lore", lore);
    }
    public ItemStack getTome1(UUID id) {

        ItemStack tome = EMPTY_TOME;
        ItemMeta meta1 = EMPTY_TOME.getItemMeta();
        tome.setType(Material.getMaterial(getString(id.toString() + ".Tomes.tome1.type")));
        meta1.setDisplayName(getString(id.toString() + ".Tomes.tome1.name"));
        meta1.setLore((List<String>) getList(id.toString() + ".Tomes.tome1.lore"));
        tome.setItemMeta(meta1);

        return tome;
    }

    public void setTome2(UUID id, ItemStack tome) {

        ItemMeta meta = tome.getItemMeta();

        Material type = tome.getType();
        String name = meta.getDisplayName();
        List<String> lore = meta.getLore();

        set(id.toString() + ".Tomes.tome2.type", type.toString());
        set(id.toString() + ".Tomes.tome2.name", name);
        set(id.toString() + ".Tomes.tome2.lore", lore);
    }
    public ItemStack getTome2(UUID id) {

        ItemStack tome = EMPTY_TOME;
        ItemMeta meta2 = EMPTY_TOME.getItemMeta();
        tome.setType(Material.getMaterial(getString(id.toString() + ".Tomes.tome2.type")));
        meta2.setDisplayName(getString(id.toString() + ".Tomes.tome2.name"));
        meta2.setLore((List<String>) getList(id.toString() + ".Tomes.tome2.lore"));
        tome.setItemMeta(meta2);

        return tome;
    }

    public void setTome3(UUID id, ItemStack tome) {

        ItemMeta meta = tome.getItemMeta();

        Material type = tome.getType();
        String name = meta.getDisplayName();
        List<String> lore = meta.getLore();

        set(id.toString() + ".Tomes.tome3.type", type.toString());
        set(id.toString() + ".Tomes.tome3.name", name);
        set(id.toString() + ".Tomes.tome3.lore", lore);
    }
    public ItemStack getTome3(UUID id) {

        ItemStack tome = EMPTY_TOME;
        ItemMeta meta3 = EMPTY_TOME.getItemMeta();
        tome.setType(Material.getMaterial(getString(id.toString() + ".Tomes.tome3.type")));
        meta3.setDisplayName(getString(id.toString() + ".Tomes.tome3.name"));
        meta3.setLore((List<String>) getList(id.toString() + ".Tomes.tome3.lore"));
        tome.setItemMeta(meta3);

        return tome;
    }

    public void setTome4(UUID id, ItemStack tome) {

        ItemMeta meta = tome.getItemMeta();

        Material type = tome.getType();
        String name = meta.getDisplayName();
        List<String> lore = meta.getLore();

        set(id.toString() + ".Tomes.tome4.type", type.toString());
        set(id.toString() + ".Tomes.tome4.name", name);
        set(id.toString() + ".Tomes.tome4.lore", lore);
    }
    public ItemStack getTome4(UUID id) {

        ItemStack tome = EMPTY_TOME;
        ItemMeta meta4 = EMPTY_TOME.getItemMeta();
        tome.setType(Material.getMaterial(getString(id.toString() + ".Tomes.tome4.type")));
        meta4.setDisplayName(getString(id.toString() + ".Tomes.tome4.name"));
        meta4.setLore((List<String>) getList(id.toString() + ".Tomes.tome4.lore"));
        tome.setItemMeta(meta4);

        return tome;
    }
}