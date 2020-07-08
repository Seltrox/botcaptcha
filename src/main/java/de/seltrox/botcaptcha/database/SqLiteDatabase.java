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
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SqLiteDatabase extends Database {

    private Connection connection;
    private FileConfiguration configuration;

    private ExecutorService service = Executors.newCachedThreadPool();

    @Override
    public void connect() {
        this.configuration = BotCaptchaPlugin.getPlugin().getConfig();
        try {
            final File file = new File(System.getProperty("user.dir") + this.configuration.getString("database.sqlite.path"));
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
            this.update("CREATE TABLE IF NOT EXISTS " + this.configuration.getString("database.mySql.tableName") +
                    "(UUID varchar(64), VerifiedSince long);");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean isConnected() {
        try {
            return this.connection != null && !(this.connection.isClosed());
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean isVerified(String uuid) {
        try {
            final ResultSet resultSet = this.query("SELECT * FROM " + this.configuration.getString("database.mySql.tableName") +
                    " WHERE UUID='" + uuid + "'");
            if (resultSet.next()) {
                return resultSet.getString("UUID") != null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void addVerify(String uuid) {
        this.update("INSERT INTO " + this.configuration.getString("database.mySql.tableName") + "(UUID, VerifiedSince) " +
                "VALUES ('" + uuid + "', '" + System.currentTimeMillis() + "');");
    }

    private void update(String query) {
        this.service.execute(() -> {
            try {
                final Statement statement = this.connection.createStatement();
                statement.execute(query);
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    private ResultSet query(String query) {
        ResultSet resultSet = null;
        try {
            final Statement statement = this.connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }
}