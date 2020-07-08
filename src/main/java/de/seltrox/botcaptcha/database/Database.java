/*
 * (C) Copyright 2020, Jan Benz, http://seltrox.de.
 *
 * This software is released under the terms of the Unlicense.
 * See https://unlicense.org/
 * for more information.
 *
 */

package de.seltrox.botcaptcha.database;

public abstract class Database {

    public abstract void connect();

    public abstract void disconnect();

    public abstract boolean isConnected();

    public abstract boolean isVerified(String uuid);

    public abstract void addVerify(String uuid);

}