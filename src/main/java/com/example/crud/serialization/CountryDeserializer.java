package com.example.crud.serialization;

import com.example.crud.models.Country;
import com.example.crud.repositories.CountryRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CountryDeserializer extends JsonDeserializer<Country> {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Long countryId = (Long) node.get("country_id").numberValue();

        return countryRepository.findById(countryId).orElse(null);
    }
}
