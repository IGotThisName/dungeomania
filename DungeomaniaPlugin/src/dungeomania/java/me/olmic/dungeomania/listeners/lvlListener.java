package me.olmic.dungeomania.listeners;

import me.olmic.dungeomania.Main;
import me.olmic.dungeomania.frameworks.Class;
import me.olmic.dungeomania.frameworks.Profile;
import me.olmic.dungeomania.managers.ProfileManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;

import static me.olmic.dungeomania.Utils.*;

public class lvlListener implements Listener {

    private ProfileManager profileManager;
    private Main main;
    private Profile profile;

    public lvlListener(Main main) {
        this.main = main;
        profileManager = main.getProfileManager();
    }

    @EventHandler
    public void mobKilled(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {

            Player player = (Player) event.getEntity().getKiller();
            Profile profile = profileManager.getProfile(player.getUniqueId());

            profile.setXp(profile.getXp()+5);

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    new TextComponent(color("&e("+profile.getXp()+" / "+(profile.getLevel()*(9 + Math.pow(profile.getLevel(), 2)))+")")));
        }
    }

    @EventHandler
    public void xpCancel(PlayerExpChangeEvent event) {
        event.setAmount(0);
    }
}