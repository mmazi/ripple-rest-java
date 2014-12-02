package com.github.mmazi.ripplerest.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Matija Mazi <br>
 */
public class LongNullDeserializer extends JsonDeserializer<Long> {

    @Override
    public Long deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final String str = jp.getValueAsString();
        return Arrays.asList("", "undefined", "null").contains(str) ? null : Long.parseLong(str);
    }
}
