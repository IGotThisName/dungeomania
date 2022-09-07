package me.olmic.dungeomania.managers;

import me.olmic.dungeomania.Main;
import me.olmic.dungeomania.config.PlayerConfig;
import me.olmic.dungeomania.frameworks.Class;
import me.olmic.dungeomania.frameworks.Profile;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import static me.olmic.dungeomania.frameworks.Items.*;

import java.util.*;

public class ProfileManager {

    private Main main;
    private PlayerConfig playerConfig;
    private Map<UUID, Profile> profiles = new HashMap<>();

    public ProfileManager(Main main) {
        this.main = main;
        playerConfig = main.getConfigManager().getPlayerConfig();
    }

    public void loadProfiles() {
        for (String key : playerConfig.getSection("")) {
            UUID id = UUID.fromString(key);
            Class clazz = playerConfig.getClass(id);
            int level = playerConfig.getLevel(id);
            int xp = playerConfig.getXP(id);
            ItemStack tome1 = null;
            ItemStack tome2 = null;
            ItemStack tome3 = null;
            ItemStack tome4 = null;

            int num = 0;

            if (playerConfig.getConfigurationSection(id.toString()).contains(".Tomes")) {
                //tome gui
                for (String keyy : playerConfig.getConfigurationSection(id+".Tomes.").getKeys(false)) {

                    num = num+1;

                    String itemType = playerConfig.getString(id.toString()+ ".Tomes." + keyy + ".type");
                    String displayName = playerConfig.getString(id.toString()+ ".Tomes." + keyy + ".name");
                    List<String> lore = (List<String>) playerConfig.getList(id.toString()+ ".Tomes." + keyy + ".lore");

                    ItemStack stack = new ItemStack(Material.getMaterial(itemType), 1);

                    if (stack.getType() == Material.AIR) {
                        stack = EMPTY_TOME;
                    }
                    ItemMeta meta = stack.getItemMeta();
                    meta.setDisplayName(displayName);
                    meta.setLore(lore);
                    stack.setItemMeta(meta);

                    if (num == 1) {
                        tome1 = stack;
                    } else if(num == 2) {
                        tome2 = stack;
                    } else if(num == 3) {
                        tome3 = stack;
                    } else if(num == 4) {
                        tome4 = stack;
                    }
                }
            } else {
                tome1 = EMPTY_TOME;
                tome2 = EMPTY_TOME;
                tome3 = EMPTY_TOME;
                tome4 = EMPTY_TOME;
            }

            Profile profile = new Profile(clazz, level, xp, tome1, tome2, tome3, tome4);
            profiles.put(id, profile);
        }
    }

    public void saveProfiles() {
        for (UUID uuid : profiles.keySet()) {
            Profile profile = profiles.get(uuid);
            playerConfig.setClass(uuid, profile.getClazz());
            playerConfig.setLevel(uuid, profile.getLevel());
            playerConfig.setXp(uuid, profile.getXp());
            playerConfig.setTome1(uuid, profile.getTome1());
            playerConfig.setTome2(uuid, profile.getTome2());
            playerConfig.setTome3(uuid, profile.getTome3());
            playerConfig.setTome4(uuid, profile.getTome4());
        }
    }

    public Profile createProfile(Player player) {
        Profile profile = new Profile(null, 1, 0, EMPTY_TOME, EMPTY_TOME, EMPTY_TOME, EMPTY_TOME);
        profiles.put(player.getUniqueId(), profile);
        return profile;
    }

    public Profile getProfile(UUID uuid) {
        return profiles.get(uuid);
    }
}
