package me.olmic.dungeomania.managers;

import me.olmic.dungeomania.Main;
import me.olmic.dungeomania.config.Config;
import me.olmic.dungeomania.config.PlayerConfig;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private Main main;
    private List<Config> configs = new ArrayList<>();
    private PlayerConfig playerConfig;

    public ConfigManager(Main main) {
        this.main = main;
        configs.add(playerConfig = new PlayerConfig(main));
    }

    public void loadConfigs() {
        for (Config config : configs) {
            config.loadConfig();
        }
    }

    public void saveConfigs() {
        for (Config config : configs) {
            config.saveConfig();
        }
    }

    public PlayerConfig getPlayerConfig() {
        return playerConfig;
    }
}