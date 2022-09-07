package me.olmic.dungeomania.commands;

import me.olmic.dungeomania.Main;
import me.olmic.dungeomania.frameworks.Class;
import me.olmic.dungeomania.config.PlayerConfig;
import me.olmic.dungeomania.frameworks.Profile;
import me.olmic.dungeomania.managers.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.olmic.dungeomania.Utils.*;
import static me.olmic.dungeomania.frameworks.Items.EMPTY_TOME;

public class TomesCmd extends Command {

    private ProfileManager profileManager;

    public TomesCmd(Main main) {
        super(main, "tomes");
        profileManager = main.getProfileManager();
    }

    @Override
    public void execute(Player player, String[] args) {
        Inventory inv = Bukkit.createInventory(null, 54, color("&eTomes"));
        Profile profile = profileManager.getProfile(player.getUniqueId());

        for (int num = 0; num < inv.getSize(); num++) {
            inv.setItem(num, createItem(Material.GRAY_STAINED_GLASS_PANE, 1, false, false, "&7Select your tomes"));
        }

        inv.setItem(28, profile.getTome1());
        inv.setItem(21, profile.getTome2());
        inv.setItem(23, profile.getTome3());
        inv.setItem(34, profile.getTome4());

        for (int slot = 0; slot < inv.getSize(); slot++) {
            if (inv.getItem(slot).equals(EMPTY_TOME)) {
                inv.setItem(slot, new ItemStack(Material.AIR));
            }
        }

        player.openInventory(inv);
    }
}