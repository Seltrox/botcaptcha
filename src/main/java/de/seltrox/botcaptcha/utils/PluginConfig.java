/*
 * (C) Copyright 2020, Jan Benz, http://seltrox.de.
 *
 * This software is released under the terms of the Unlicense.
 * See https://unlicense.org/
 * for more information.
 *
 */

package de.seltrox.botcaptcha.utils;

import de.seltrox.botcaptcha.BotCaptchaPlugin;

import java.util.ArrayList;
import java.util.List;

public class PluginConfig {

    public String getMessage(final String key) {
        return BotCaptchaPlugin.getPlugin().getConfig().getString(key)
                .replace("%prefix%", BotCaptchaPlugin.getPlugin().getConfig().getString("prefix"))
                .replace("%policyWebsite%", BotCaptchaPlugin.getPlugin().getConfig().getString("policyWebsite"))
                .replace("&", "§");
    }

    public ArrayList<String> getStringList(final String key) {
        final ArrayList<String> list = new ArrayList<>();
        for (String string : BotCaptchaPlugin.getPlugin().getConfig().getStringList(key)) {
            list.add(string.replace("%prefix%", BotCaptchaPlugin.getPlugin().getConfig().getString("prefix"))
                    .replace("%policyWebsite%", BotCaptchaPlugin.getPlugin().getConfig().getString("policyWebsite"))
                    .replace("&", "§"));
        }
        return list;
    }

    public String getListAsMessage(final String key) {
        final List<String> list = BotCaptchaPlugin.getPlugin().getConfig().getStringList(key);
        final StringBuilder builder = new StringBuilder();
        for (String string : list) {
            builder.append("\n").append(string.replace("%prefix%", BotCaptchaPlugin.getPlugin().getConfig().getString("prefix"))
                    .replace("%policyWebsite%", BotCaptchaPlugin.getPlugin().getConfig().getString("policyWebsite"))
                    .replace("&", "§"));
        }
        return builder.toString().replaceFirst("\n", "");
    }

}