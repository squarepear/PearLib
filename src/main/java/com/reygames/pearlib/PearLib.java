package com.reygames.pearlib;

import ch.jalu.injector.*;
import com.reygames.pearlib.api.PearLibAPI;
import com.reygames.pearlib.api.configs.PlayerConfigs;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PearLib extends JavaPlugin {

    private PearLibAPI pearLibAPI = new PearLibAPI();
    private PlayerConfigs pConfigs = pearLibAPI.getPlayerConfigs();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this.pearLibAPI.getPlayerConfigs(), this);

        Injector injector = new InjectorBuilder()
                .addDefaultHandlers("com.reygames.pearlib")
                .create();
        injectServices(injector);
        injector.register(PearLib.class, this);
        injector.register(Server.class, getServer());
        injector.register(PluginManager.class, getServer().getPluginManager());
        injector.register(PearLibAPI.class, this.pearLibAPI);

    }

    protected void injectServices(Injector injector) {

    }

    public PearLibAPI getAPI() {
        return this.pearLibAPI;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
