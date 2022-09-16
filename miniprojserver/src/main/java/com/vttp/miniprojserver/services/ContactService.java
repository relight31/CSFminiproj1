package com.vttp.miniprojserver.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.vttp.miniprojserver.models.Contact;
import com.vttp.miniprojserver.repositories.ContactRepo;

@Service
public class ContactService {
    Logger logger = Logger.getLogger(ContactService.class.getName());
    @Autowired
    private ContactRepo repo;

    public boolean addContact(Contact contact) {
        return repo.addContact(contact.getName(),
                contact.getEmail(),
                contact.getMobile());
    }

    public Optional<List<Contact>> getContacts() {
        List<Contact> contacts = new LinkedList<>();
        try {
            SqlRowSet rowSet = repo.getContacts();
            logger.info("Retrieved contacts from DB");
            while (rowSet.next()) {
                Contact contact = new Contact();
                // logger.info("created new empty Contact object");
                contact.setName(rowSet.getString("name"));
                // logger.info("set Name");
                contact.setEmail(rowSet.getString("email"));
                // logger.info("set Email");
                contact.setMobile(rowSet.getString("mobile"));
                // logger.info("set Mobile");
                contacts.add(contact);
                logger.info("Contact added");
            }
            return Optional.of(contacts);
        } catch (Exception e) {
            logger.warning("Unable to create Contact Java object(s)");
            logger.warning(e.getMessage());
            return Optional.empty();
        }
    }
}
