/*
 * (C) Copyright 2020, Jan Benz, http://seltrox.de.
 *
 * This software is released under the terms of the Unlicense.
 * See https://unlicense.org/
 * for more information.
 *
 */

package de.seltrox.botcaptcha;

import de.seltrox.botcaptcha.database.Database;
import de.seltrox.botcaptcha.database.DatabaseHelper;
import de.seltrox.botcaptcha.listener.AsyncPlayerChatListener;
import de.seltrox.botcaptcha.listener.InventoryClickListener;
import de.seltrox.botcaptcha.listener.PlayerJoinListener;
import de.seltrox.botcaptcha.utils.PluginConfig;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BotCaptchaPlugin extends JavaPlugin {

    private static BotCaptchaPlugin plugin;

    private Database database;
    private PluginConfig pluginConfig;

    @Override
    public void onEnable() {
        plugin = this;

        this.saveDefaultConfig();
        this.pluginConfig = new PluginConfig();

        this.database = DatabaseHelper.getDatabase();
        this.database.connect();

        final PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new AsyncPlayerChatListener(), this);
        pluginManager.registerEvents(new InventoryClickListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
        if (this.database.isConnected()) this.database.disconnect();
    }

    public Database getDatabase() {
        return database;
    }

    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }

    public static BotCaptchaPlugin getPlugin() {
        return plugin;
    }
}