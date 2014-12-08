package com.github.mmazi.ripplerest.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.mmazi.ripplerest.Amount;

import java.io.IOException;

public class AmountSlashSerializer extends JsonSerializer<Amount> {
    @Override
    public void serialize(Amount value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(value.toStringSlashes());
    }
}
