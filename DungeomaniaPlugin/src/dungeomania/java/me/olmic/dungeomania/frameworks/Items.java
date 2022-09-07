package me.olmic.dungeomania.frameworks;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import static me.olmic.dungeomania.Utils.*;

public class Items {

    public static final ItemStack WARRIOR_ICON = createItem(Material.IRON_AXE, 1, false, false, "&cWarrior", "&7A closeup melee class");
    public static final ItemStack ARCHER_ICON = createItem(Material.BOW, 1, false, false, "&aArcher", "&7A longrange bow class");
    public static final ItemStack MAGE_ICON = createItem(Material.STICK, 1, true, false, "&bMage", "&7A midrange magic class");
    public static final ItemStack WARLOCK_ICON = createItem(Material.IRON_HOE, 1, true, false, "&5Warlock", "&7A " +
            "melee magic class", "&5Main Attack &7- &dSuck all mobs in towards the hit mob", "&5Right Click &7- " +
            "&dDash towards the direction you are looing at", "&5Shift Right Click &7- &dDamage all mobs in an aoe " +
            "area infront of you");
    public static final ItemStack KNIGHT_ICON = createItem(Material.IRON_SWORD, 1, false, false, "&eKnight", "&7A closeup melee class");
    public static final ItemStack PRIEST_ICON = createItem(Material.BOOK, 1, true, false, "&fPriest", "&7A magic healing class");
    public static final ItemStack DRUID_ICON = createItem(Material.BAMBOO,1, true, false, "&2Druid", "&7A magic support class", "&cWORK IN PROGRESS");

    public static final ItemStack EMPTY_TOME = createItem(Material.GLASS_PANE, 1, false, false, "&fEmpty slot", "&7add any new tome to this slot");
}

