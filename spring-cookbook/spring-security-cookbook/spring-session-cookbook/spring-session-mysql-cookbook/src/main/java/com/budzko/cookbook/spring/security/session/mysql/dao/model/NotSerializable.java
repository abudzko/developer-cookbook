package com.budzko.cookbook.spring.security.session.mysql.dao.model;

import java.util.Date;

public class NotSerializable {
    private Date date;

    public NotSerializable() {
        date = new Date();
    }

    @Override
    public String toString() {
        return getClass().getName() + " was created at " + date;
    }
}
