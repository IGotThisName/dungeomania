package me.olmic.dungeomania.commands;

import me.olmic.dungeomania.Main;
import me.olmic.dungeomania.frameworks.Class;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static me.olmic.dungeomania.Utils.color;
import static me.olmic.dungeomania.Utils.msgPlayer;

public class ClassCmd extends Command {

    public ClassCmd(Main main) {
        super(main, "class");
    }

    @Override
    public void execute(Player player, String[] args) {
        Inventory inv = Bukkit.createInventory(null, 27, color("&eChoose your class"));

        int slot = 10;

        for (Class clazz : Class.values()) {
            inv.setItem(slot, clazz.getIcon());
            slot++;
        }

        player.openInventory(inv);
    }
}
