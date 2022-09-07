package me.olmic.dungeomania.config;

import me.olmic.dungeomania.Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static me.olmic.dungeomania.Utils.log;

public abstract class Config extends YamlConfiguration {

    protected Main main;
    protected String name;
    protected File file;

    public Config(Main main, String name) {
        this.main = main;
        this.name = name;
        file = new File(main.getDataFolder(), name);
    }

    public Set<String> getSection(String path) {
        ConfigurationSection section = getConfigurationSection(path);
        if (section != null) return section.getKeys(false);
        return new HashSet<>();
    }

    private void checkFile() {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            main.saveResource(name, false);
        }
    }

    public void loadConfig() {
        try {
            load(file);
            log("Loaded data from " + name);
        } catch (InvalidConfigurationException | IOException exception) {
            exception.printStackTrace();
            log("error loading data from " + name);
        }
    }

    public void saveConfig() {
        try {
            save(file);
            log("saved data from" + name);
        } catch (IOException exception) {
            exception.printStackTrace();
            log("error saving data from " + name);
        }
    }
}