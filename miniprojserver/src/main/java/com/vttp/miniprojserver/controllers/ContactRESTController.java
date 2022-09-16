package com.vttp.miniprojserver.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vttp.miniprojserver.models.Contact;
import com.vttp.miniprojserver.services.ContactService;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ContactRESTController {
    @Autowired
    private ContactService service;

    @PostMapping(path = "/addcontact", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addContact(@RequestBody String payload) {
        try {
            Contact contact = Contact.jsonStringToContact(payload);
            if (service.addContact(contact)) {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("{'message': 'Created new contact'}");
            } else {
                throw new Exception("Could not add new contact");
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping(path = "/contacts")
    public ResponseEntity<String> getContacts() {
        Optional<List<Contact>> opt = service.getContacts();
        if (opt.isEmpty() || opt.get().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("");
        } else {
            List<Contact> contacts = opt.get();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            contacts.stream()
                    .forEach((contact) -> {
                        arrayBuilder.add(contact.toJson());
                    });
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(arrayBuilder.build().toString());
        }
    }
}
