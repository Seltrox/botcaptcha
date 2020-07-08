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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getCurrentItem() != null) {
            final Player player = (Player) event.getWhoClicked();
            if (event.getClickedInventory().getName().equals(BotCaptchaPlugin.getPlugin().getPluginConfig().getMessage("gui.inventoryName"))) {
                event.setCancelled(true);
                if (event.getCurrentItem().getType() == Material.CHEST) {
                    ProcessHelper.getProcess(player).onComplete(player);
                } else {
                    player.kickPlayer(BotCaptchaPlugin.getPlugin().getPluginConfig().getListAsMessage("verifyIncorrectKick"));
                }
            }
        }
    }

}