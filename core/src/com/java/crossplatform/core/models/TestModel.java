package com.java.crossplatform.core.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by nick on 25/03/2015.
 */
@DatabaseTable(tableName = "testmodels")
public class TestModel {
    @DatabaseField(id = true)
    private String name;

    public TestModel() {

    }

    public TestModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}