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
import de.seltrox.botcaptcha.verify.VerifyChatProcess;
import de.seltrox.botcaptcha.verify.VerifyProcess;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final VerifyProcess process = ProcessHelper.getProcess(player);
        if (process != null) {
            if (process instanceof VerifyChatProcess) {
                final VerifyChatProcess chatProcess = (VerifyChatProcess) process;
                final String message = event.getMessage();
                if (chatProcess.isCode(player.getUniqueId(), message)) {
                    chatProcess.onComplete(player);
                } else {
                    player.kickPlayer(BotCaptchaPlugin.getPlugin().getPluginConfig().getListAsMessage("verifyIncorrectKick"));
                }
            }
        }

    }
}