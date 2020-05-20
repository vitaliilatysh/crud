package com.example.crud.serialization;

import com.example.crud.models.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserSerializer extends JsonSerializer<Set<User>> {

    @Override
    public void serialize(Set<User> value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        List<String> users = new ArrayList<>();

        for (User user : value) {
            users.add(user.getName());
        }

        jgen.writeStartArray();
        for (String user : users) {
            jgen.writeString(user);
        }
        jgen.writeEndArray();
    }

}

