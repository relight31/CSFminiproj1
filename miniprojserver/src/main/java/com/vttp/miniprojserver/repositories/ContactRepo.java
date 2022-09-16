package com.vttp.miniprojserver.repositories;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepo {
    Logger logger = Logger.getLogger(ContactRepo.class.getName());

    private final String SQL_GET_ALL_CONTACTS = "select * from contacts";
    private final String SQL_ADD_CONTACT = "insert into contacts (name, email, mobile) values (?,?,?)";

    @Autowired
    JdbcTemplate template;

    public boolean addContact(String name, String email, String mobile) {
        int result = template.update(SQL_ADD_CONTACT,
                name,
                email,
                mobile);
        return result == 1;
    }

    public SqlRowSet getContacts(){
        return template.queryForRowSet(SQL_GET_ALL_CONTACTS);
    }
}
