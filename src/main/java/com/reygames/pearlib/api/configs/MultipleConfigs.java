package com.reygames.pearlib.api.configs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MultipleConfigs
{
    public JavaPlugin plugin;
    public HashMap<String, FileConfiguration> configs = new HashMap();

    public MultipleConfigs(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public FileConfiguration create(String name) {
        if (!name.endsWith(".yml")) {
            name = name + ".yml";
        }
        File file = new File(this.plugin.getDataFolder(), name);
        if (!file.exists()) {
            this.plugin.getDataFolder().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        this.configs.put(name, config);
        return config;
    }

    public void save(String name) {
        if (!name.endsWith(".yml")) {
            name = name + ".yml";
        }
        File file = new File(this.plugin.getDataFolder(), name);
        try {
            ((FileConfiguration)this.configs.get(name)).save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration get(String name) {
        if (!name.endsWith(".yml")) {
            name = name + ".yml";
        }
        create(name);
        File file = new File(this.plugin.getDataFolder(), name);
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        this.configs.put(name, config);
        return config;
    }
}