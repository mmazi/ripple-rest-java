package com.github.mmazi.ripplerest.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * @author Matija Mazi <br>
 */
public class RippleTimeDeserializer extends JsonDeserializer<Date> {

    public static final int JAN_1_2000_UTC = 946684800;

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return new Date((jp.getValueAsLong() + JAN_1_2000_UTC) * 1000);
    }
}
