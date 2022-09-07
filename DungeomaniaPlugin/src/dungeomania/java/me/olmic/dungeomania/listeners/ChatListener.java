package me.olmic.dungeomania.listeners;

import me.olmic.dungeomania.Main;
import me.olmic.dungeomania.frameworks.Class;
import me.olmic.dungeomania.frameworks.Profile;
import me.olmic.dungeomania.managers.ProfileManager;
import me.olmic.dungeomania.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static me.olmic.dungeomania.Utils.msgPlayer;

public class ChatListener implements Listener {

    private ProfileManager profileManager;

    public ChatListener(Main main) {
        profileManager = main.getProfileManager();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Profile profile = profileManager.getProfile(player.getUniqueId());
        Class clazz = profile.getClazz();
        event.setCancelled(true);
        if (clazz == null) {
            for (Player each : Bukkit.getOnlinePlayers()) {
                msgPlayer(each, "&8[&fClassless&8] &f" + player.getName() + "&7: &f" + event.getMessage());
            }
        } else {
            for (Player each : Bukkit.getOnlinePlayers()) {
                msgPlayer(each,
                        "&8[&f"+ clazz.getName() +"&8] &f" + player.getName() + "&7: &f" + event.getMessage());
            }
        }
    }
}