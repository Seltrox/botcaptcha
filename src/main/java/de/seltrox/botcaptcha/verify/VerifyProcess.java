/*
 * (C) Copyright 2020, Jan Benz, http://seltrox.de.
 *
 * This software is released under the terms of the Unlicense.
 * See https://unlicense.org/
 * for more information.
 *
 */

package de.seltrox.botcaptcha.verify;

import de.seltrox.botcaptcha.BotCaptchaPlugin;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public abstract class VerifyProcess {

    private BukkitTask task;

    public void start(Player player) {
        final int delay = BotCaptchaPlugin.getPlugin().getConfig().getInt("timeout");
        if (delay > 0) {
            this.task = new BukkitRunnable() {
                @Override
                public void run() {
                    player.kickPlayer(BotCaptchaPlugin.getPlugin().getPluginConfig().getListAsMessage("verifyTimeoutKick"));
                }
            }.runTaskLater(BotCaptchaPlugin.getPlugin(), delay * 20);
        }
    }

    public void onComplete(Player player) {
        if (this.task != null) this.task.cancel();
        player.sendMessage(BotCaptchaPlugin.getPlugin().getPluginConfig().getMessage("verifyComplete"));
        player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
        ProcessHelper.removeProcess(player);
        BotCaptchaPlugin.getPlugin().getDatabase().addVerify(player.getUniqueId().toString());
    }

}