package me.olmic.dungeomania.frameworks;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static me.olmic.dungeomania.Utils.*;
import static me.olmic.dungeomania.frameworks.Items.*;

public enum Class {

    WARRIOR("&cWarrior", WARRIOR_ICON),
    ARCHER("&aArcher", ARCHER_ICON),
    MAGE("&bMage", MAGE_ICON),
    WARLOCK("&5Warlock", WARLOCK_ICON),
    KNIGHT("&eKnight", KNIGHT_ICON),
    PRIEST("&fPriest", PRIEST_ICON),
    DRUID("&2Druid", DRUID_ICON);

    private String name, rawName;
    private ItemStack[] armor, items;
    private ItemStack icon;

    Class(String name, ItemStack icon) {
        this.name = name;
        rawName = decolor(name);
        this.icon = icon;
    }

    public static Class getClassByName(String name) {
        for (Class clazz : values()) {
            if (name.equalsIgnoreCase(clazz.getRawName())) {
                return clazz;
            }
        }
        return null;
    }

    public ItemStack getIcon() {
        return icon;
    }
    public String getName() {
        return name;
    }
    public String getRawName() {
        return rawName;
    }
}
