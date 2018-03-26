package com.reygames.pearlib.api;

import com.reygames.pearlib.PearLib;
import com.reygames.pearlib.api.configs.PlayerConfigs;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

public class PearLibAPI
{

    @Inject
    PearLib plugin;
    PlayerConfigs pc;

    public PearLibAPI()
    {
        this.pc = new PlayerConfigs();
    }

    public PlayerConfigs getPlayerConfigs() {
        if (this.pc == null) {
            this.plugin.getLogger().log(Level.WARNING, "PlayerConfigs is null!?");
            this.pc = new PlayerConfigs();
        }
        return this.pc;
    }
}