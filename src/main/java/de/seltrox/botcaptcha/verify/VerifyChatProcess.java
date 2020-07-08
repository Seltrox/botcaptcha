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
import de.seltrox.botcaptcha.utils.CodeBuilder;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class VerifyChatProcess extends VerifyProcess {

    private HashMap<UUID, String> codes = new HashMap<>();

    @Override
    public void start(Player player) {
        super.start(player);
        final String code = CodeBuilder.builder().lowercase(2).digits(4).build();
        player.sendMessage(BotCaptchaPlugin.getPlugin().getPluginConfig().getMessage("verifyChatMessage"));
        player.sendMessage(BotCaptchaPlugin.getPlugin().getPluginConfig().getMessage("verifyChatCodeMessage").replace("%code%", code));
        this.codes.put(player.getUniqueId(), code);
    }

    public boolean isCode(UUID uuid, String code) {
        return codes.get(uuid).equals(code);
    }
}