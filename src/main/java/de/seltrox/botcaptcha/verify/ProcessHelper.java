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
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ProcessHelper {

    private static HashMap<UUID, VerifyProcess> processes = new HashMap<>();

    public static VerifyProcess createProcess(Player player) {
        switch (BotCaptchaPlugin.getPlugin().getConfig().getString("type").toLowerCase()) {
            case "gui":
            default:
                VerifyGuiProcess guiProcess = new VerifyGuiProcess();
                guiProcess.start(player);
                processes.put(player.getUniqueId(), guiProcess);
                return guiProcess;
            case "chat":
                VerifyChatProcess chatProcess = new VerifyChatProcess();
                chatProcess.start(player);
                processes.put(player.getUniqueId(), chatProcess);
                return chatProcess;
        }
    }

    public static VerifyProcess getProcess(final Player player) {
        return processes.get(player.getUniqueId());
    }

    public static void removeProcess(Player player) {
        processes.remove(player.getUniqueId());
    }

}