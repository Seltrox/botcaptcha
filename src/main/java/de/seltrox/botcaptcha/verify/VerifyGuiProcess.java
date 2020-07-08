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
import de.seltrox.botcaptcha.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class VerifyGuiProcess extends VerifyProcess {

    @Override
    public void start(Player player) {
        super.start(player);
        final Inventory inventory = Bukkit.createInventory(null, 27, BotCaptchaPlugin.getPlugin().getPluginConfig().getMessage("gui.inventoryName"));

        final ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, (byte) 15).setDisplayName(" ").build();
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, glass);
        }

        inventory.setItem(new Random().nextInt(25), new ItemBuilder(Material.CHEST).setDisplayName(BotCaptchaPlugin.getPlugin().getPluginConfig().getMessage("gui.itemName"))
                .setLore(BotCaptchaPlugin.getPlugin().getPluginConfig().getStringList("gui.itemLore")).build());

        player.openInventory(inventory);
    }
}