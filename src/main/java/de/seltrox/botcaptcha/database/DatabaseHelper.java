/*
 * (C) Copyright 2020, Jan Benz, http://seltrox.de.
 *
 * This software is released under the terms of the Unlicense.
 * See https://unlicense.org/
 * for more information.
 *
 */

package de.seltrox.botcaptcha.database;

import de.seltrox.botcaptcha.BotCaptchaPlugin;

public class DatabaseHelper {

    public static Database getDatabase() {
        switch (BotCaptchaPlugin.getPlugin().getConfig().getString("database.type").toLowerCase()) {
            case "mysql":
            default:
                return new MySqlDatabase();
            case "sqlite":
                return new SqLiteDatabase();
            case "mongodb":
                return new MongoDbDatabase();
        }
    }

}