package me.olmic.dungeomania;

import me.olmic.dungeomania.commands.ClassCmd;
import me.olmic.dungeomania.commands.SetLevel;
import me.olmic.dungeomania.commands.TomesCmd;
import me.olmic.dungeomania.commands.getLevel;
import me.olmic.dungeomania.config.PlayerConfig;
import me.olmic.dungeomania.frameworks.Profile;
import me.olmic.dungeomania.listeners.*;
import me.olmic.dungeomania.managers.ConfigManager;
import me.olmic.dungeomania.managers.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.logging.Logger;

import static me.olmic.dungeomania.Utils.log;
import static me.olmic.dungeomania.Utils.msgPlayer;

public final class Main extends JavaPlugin {

    private static Logger logger;

    private ConfigManager configManager;
    private ProfileManager profileManager;
    private PlayerConfig playerConfig;

    @Override
    public void onEnable() {
        logger = getLogger();

        configManager = new ConfigManager(this);
        profileManager = new ProfileManager(this);

        configManager.loadConfigs();
        profileManager.loadProfiles();

        getServer().getPluginManager().registerEvents(new JoinQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        getServer().getPluginManager().registerEvents(new AbilitiesListener(this), this);
        getServer().getPluginManager().registerEvents(new lvlListener(this), this);

        new ClassCmd(this);
        new SetLevel(this);
        new getLevel(this);
        new TomesCmd(this);

        log("Plugin fully enabled");

        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable(){
            public void run(){
                for(Player player : Bukkit.getOnlinePlayers()){

                    Profile profile = profileManager.getProfile(player.getUniqueId());

                    if (profile.getXp() > (profile.getLevel()*(9 + Math.pow(profile.getLevel(), 2)))) {

                        profile.setLevel(profile.getLevel()+1);
                        profile.setXp(0);

                        msgPlayer(player, "test");
                    }

                    player.setLevel(profile
                            .getLevel());

                    player.setExp((float) (profile.getXp()/(profile.getLevel()*(9 + Math.pow(profile.getLevel(), 2)))));
                }
            }
        }, 5l, 5l);
    }

    @Override
    public void onDisable() {
        profileManager.saveProfiles();
        configManager.saveConfigs();

        log("plugin fully disabled");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }

    public static Logger getPluginLogger() {
        return logger;
    }

    
}
