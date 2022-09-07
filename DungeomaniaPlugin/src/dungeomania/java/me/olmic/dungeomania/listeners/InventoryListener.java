package me.olmic.dungeomania.listeners;

import me.olmic.dungeomania.Main;
import me.olmic.dungeomania.frameworks.Class;
import me.olmic.dungeomania.frameworks.Profile;
import me.olmic.dungeomania.managers.ProfileManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

import static me.olmic.dungeomania.Utils.*;
import static me.olmic.dungeomania.frameworks.Items.EMPTY_TOME;

public class InventoryListener implements Listener {

    private Main main;
    private ProfileManager profileManager;

    public InventoryListener(Main main) {
        this.main = main;
        profileManager = main.getProfileManager();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equalsIgnoreCase(color("&eChoose your class"))) {
            int slot = event.getSlot();
            Profile profile = profileManager.getProfile(player.getUniqueId());
            Class[] classes = Class.values();
            if (slot < 10 || slot > 10 + classes.length) return;
            Class selected = classes[slot - 10], current = profile.getClazz();
            if (selected == current) {
                player.closeInventory();
                msgPlayer(player, "&cYou have already selected this class!");
                return;
            }
            profile.setClazz(selected);
            msgPlayer(player, "&fYou have selected the " + selected.getName() + " &fclass!");
            player.closeInventory();
        } else if (event.getView().getTitle().equalsIgnoreCase(color("&eTomes"))) {
            int slot = event.getSlot();
            Profile profile = profileManager.getProfile(player.getUniqueId());
            Inventory inv = event.getView().getTopInventory();

            ItemStack tome1 = profile.getTome1();
            ItemStack tome2 = profile.getTome2();
            ItemStack tome3 = profile.getTome3();
            ItemStack tome4 = profile.getTome4();

            if (Arrays.asList(28, 21, 23, 34).contains(slot)) {


            } else if (event.getClickedInventory() == inv) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {

        Player player = (Player) event.getPlayer();
        Inventory inv = event.getInventory();
        Profile profile = profileManager.getProfile(player.getUniqueId());

        if (event.getView().getTitle().equalsIgnoreCase(color("&eTomes"))) {

            if (inv.getItem(28) != null) {
                profile.setTome1(inv.getItem(28));
            } else {
                profile.setTome1(EMPTY_TOME);
            }

            if (inv.getItem(21) != null) {
                profile.setTome2(inv.getItem(21));
            } else {
                profile.setTome2(EMPTY_TOME);
            }

            if (inv.getItem(23) != null) {
                profile.setTome3(inv.getItem(23));
            } else {
                profile.setTome3(EMPTY_TOME);
            }

            if (inv.getItem(34) != null) {
                profile.setTome4(inv.getItem(34));
            } else {
                profile.setTome4(EMPTY_TOME);
            }
        }
    }
}