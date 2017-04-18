package com.elefirst.connector.entity;

import com.elefirst.base.entity.BaseEntity;

/**
 * Created by 123 on 2016/6/23.
 */
public class DbTableInfo extends BaseEntity {
    private String name;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
