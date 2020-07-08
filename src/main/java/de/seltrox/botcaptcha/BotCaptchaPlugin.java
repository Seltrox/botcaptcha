/*
 * (C) Copyright 2020, Jan Benz, http://seltrox.de.
 *
 * This software is released under the terms of the Unlicense.
 * See https://unlicense.org/
 * for more information.
 *
 */

package de.seltrox.botcaptcha;

import org.bukkit.plugin.java.JavaPlugin;

public class BotCaptchaPlugin extends JavaPlugin {

    private static BotCaptchaPlugin plugin;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }

    public static synchronized BotCaptchaPlugin getPlugin() {
        if (plugin == null) {
            plugin = new BotCaptchaPlugin();
        }
        return plugin;
    }
}