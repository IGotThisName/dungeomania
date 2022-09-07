package me.olmic.dungeomania.commands;

import me.olmic.dungeomania.Main;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

public abstract class Command implements CommandExecutor {

    protected Main main;

    public Command(Main main, String name) {
        this.main = main;
        PluginCommand pluginCommand = main.getCommand(name);
        pluginCommand.setExecutor(this);
    }

    public abstract void execute(Player player, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String alias, String[] args) {

        if (sender instanceof Player) {
            execute((Player) sender, args);
        }

        return true;
    }

}
