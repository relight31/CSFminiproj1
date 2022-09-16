package com.vttp.miniprojserver.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Contact {
    private String name;
    private String email;
    private String mobile;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("name", name)
                .add("email", email)
                .add("mobile", mobile)
                .build();

    }

    public static Contact jsonStringToContact(String string) {
        JsonReader reader = Json.createReader(new StringReader(string));
        JsonObject object = reader.readObject();
        return jsonObjectToContact(object);
    }

    public static Contact jsonObjectToContact(JsonObject object) {
        Contact contact = new Contact();
        contact.setName(object.getString("name"));
        contact.setEmail(object.getString("email"));
        contact.setMobile(object.getString("mobile"));
        return contact;
    }
}
