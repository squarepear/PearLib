package com.reygames.pearlib.api.configs;

import com.reygames.pearlib.PearLib;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import javax.inject.Inject;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

public class PlayerConfigs
        implements Listener
{

    @Inject
    public PearLib plugin;

    @Inject
    private PluginManager pluginManager;
    public HashMap<String, FileConfiguration> configs = new HashMap();

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        create(p.getUniqueId().toString());
    }

    @EventHandler
    private void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        remove(p.getUniqueId().toString());
    }

    private FileConfiguration create(String name) {
        if (!name.endsWith(".yml")) {
            name = name + ".yml";
        }
        File file = new File("PlayerConfigs", name);
        if (!file.exists()) {
            new File("PlayerConfigs").mkdir();
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
        File file = new File("PlayerConfigs", name);
        try {
            ((FileConfiguration)this.configs.get(name)).save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration get(String UUID) {
        if (!UUID.endsWith(".yml")) {
            UUID = UUID + ".yml";
        }
        create(UUID);
        File file = new File("PlayerConfigs", UUID);
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        this.configs.put(UUID, config);
        return config;
    }

    private void remove(String name) {
        this.configs.remove("name");
    }
}