package me.olmic.dungeomania.listeners;

import me.olmic.dungeomania.Main;
import me.olmic.dungeomania.frameworks.Profile;
import me.olmic.dungeomania.managers.ProfileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    private Main main;
    private ProfileManager profileManager;

    public JoinQuitListener(Main main) {
        this.main = main;
        profileManager = main.getProfileManager();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Profile profile = profileManager.getProfile(player.getUniqueId());
        if (profile == null) {
            profile = profileManager.createProfile(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }

}
