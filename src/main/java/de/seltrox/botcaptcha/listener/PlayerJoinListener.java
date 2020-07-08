/*
 * (C) Copyright 2020, Jan Benz, http://seltrox.de.
 *
 * This software is released under the terms of the Unlicense.
 * See https://unlicense.org/
 * for more information.
 *
 */

package de.seltrox.botcaptcha.listener;

import de.seltrox.botcaptcha.BotCaptchaPlugin;
import de.seltrox.botcaptcha.verify.ProcessHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (!BotCaptchaPlugin.getPlugin().getDatabase().isVerified(player.getUniqueId().toString())) {
            ProcessHelper.createProcess(player);
        }
    }
}