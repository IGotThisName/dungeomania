package me.olmic.dungeomania.commands;

import me.olmic.dungeomania.Main;
import me.olmic.dungeomania.config.PlayerConfig;
import me.olmic.dungeomania.frameworks.Profile;
import me.olmic.dungeomania.managers.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import static me.olmic.dungeomania.Utils.*;


public class SetLevel extends Command {

    private PlayerConfig playerConfig;
    private ProfileManager profileManager;

    public SetLevel(Main main) {
        super(main, "setlevel");
        playerConfig = main.getConfigManager().getPlayerConfig();
        profileManager = main.getProfileManager();
    }

    @Override
    public void execute(Player player, String[] args) {

        Player target = Bukkit.getServer().getPlayer(args[0]);
        Profile profile = profileManager.getProfile(target.getUniqueId());
        int newLevel = Integer.parseInt(args[1]);

        profile.setLevel(newLevel);

        msgPlayer(player, color("&7Set "+target.getName()+"'s level to "+newLevel));
    }
}