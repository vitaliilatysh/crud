package com.example.crud.serialization;

import com.example.crud.models.Country;
import com.example.crud.models.User;
import com.example.crud.repositories.CountryRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserDeserializer extends JsonDeserializer<User> {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        int countryId = (Integer) node.get("country_id").numberValue();
        String name = node.get("name").textValue();
        String email = node.get("email").textValue();

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setCountry(countryRepository.findById((long) countryId).orElse(null));

        return user;
    }
}
