/*
 * (C) Copyright 2020, Jan Benz, http://seltrox.de.
 *
 * This software is released under the terms of the Unlicense.
 * See https://unlicense.org/
 * for more information.
 *
 */

package de.seltrox.botcaptcha.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import de.seltrox.botcaptcha.BotCaptchaPlugin;
import org.bson.Document;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.Collections;

public class MongoDbDatabase extends Database {

    private FileConfiguration configuration;

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @Override
    public void connect() {
        this.configuration = BotCaptchaPlugin.getPlugin().getConfig();
        final MongoCredential credential = MongoCredential.createCredential(configuration.getString("database.mongoDb.username"),
                configuration.getString("database.mongoDb.database"),
                configuration.getString("database.mongoDb.password").toCharArray());
        this.client = new MongoClient(new ServerAddress(configuration.getString("database.mongoDb.host"),
                configuration.getInt("database.mongoDb.port")),
                Collections.singletonList(credential));
        this.database = this.client.getDatabase(configuration.getString("database.mongoDb.database"));
        this.collection = this.database.getCollection(configuration.getString("database.mongoDb.collectionName"));
    }

    @Override
    public void disconnect() {
        this.client.close();
    }

    @Override
    public boolean isConnected() {
        return this.client != null;
    }

    @Override
    public boolean isVerified(String uuid) {
        return this.collection.find(Filters.eq("UUID", uuid))
                .first() != null;
    }

    @Override
    public void addVerify(String uuid) {
        this.collection.insertOne(new Document("UUID", uuid)
                .append("VerifiedSince", System.currentTimeMillis()));
    }
}