package com.java.crossplatform.core;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.java.crossplatform.core.models.TestModel;

import java.sql.SQLException;

/**
 * Created by nick on 25/06/2015.
 */
public class DataProvider {
    public static final int STATUS_NOT_INIT = 0;
    public static final int STATUS_INIT = 1;
    public static final int STATUS_CLOSED = 2;

    private ConnectionSource connectionSource;
    private int status = STATUS_NOT_INIT;

    public DataProvider(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
        status = STATUS_INIT;
    }

    public void close() {
        try {
            connectionSource.close();
            status = STATUS_CLOSED;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getData(ConnectionSource connectionSource) {
        try {
            // instantiate the DAO to handle Account with String id
            Dao<TestModel, String> accountDao =
                    DaoManager.createDao(connectionSource, TestModel.class);

            // if you need to create the 'accounts' table make this call
            TableUtils.createTable(connectionSource, TestModel.class);

            // create an instance of Account
            String name = "Jim Smith";
            TestModel account = new TestModel(name);

            // persist the account object to the database
            accountDao.create(account);

            // retrieve the account
            TestModel account2 = accountDao.queryForId(name);

            // close the connection source
            connectionSource.close();

            return account2.getName();
        } catch (SQLException e) {
            e.printStackTrace();
            return "SQLError";
        }
    }
}
