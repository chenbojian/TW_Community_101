package com.community101.core;

/**
 * Created by MiffyLiye on 16/07/2015.
 */
public class Category {
    private long id;
    private String name;

    public Category() {}

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
