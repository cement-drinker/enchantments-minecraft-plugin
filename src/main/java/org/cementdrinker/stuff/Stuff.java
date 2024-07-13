package org.cementdrinker.stuff;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Stuff extends JavaPlugin {
    public Logger logger = getLogger();
    @Override
    public void onEnable() {
        // Plugin startup logic
        logger.info("Hello World!");
        getServer().getPluginManager().registerEvents(new EnchantmentUseListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
