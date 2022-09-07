package me.olmic.dungeomania.commands;

import me.olmic.dungeomania.Main;
import me.olmic.dungeomania.config.PlayerConfig;
import me.olmic.dungeomania.frameworks.Profile;
import me.olmic.dungeomania.managers.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.olmic.dungeomania.Utils.color;
import static me.olmic.dungeomania.Utils.msgPlayer;

public class getLevel extends Command {

    private PlayerConfig playerConfig;
    private ProfileManager profileManager;

    public getLevel(Main main) {
        super(main, "getlevel");
        playerConfig = main.getConfigManager().getPlayerConfig();
        profileManager = main.getProfileManager();
    }

    @Override
    public void execute(Player player, String[] args) {

        Player target = Bukkit.getServer().getPlayer(args[0]);
        Profile profile = profileManager.getProfile(target.getUniqueId());

        msgPlayer(player, color("&a"+target.getName()+"'s level is "+profile.getLevel()));
    }

}