package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import si.mazi.rescu.ClientConfig;
import si.mazi.rescu.RestProxyFactory;
import si.mazi.rescu.serialization.jackson.JacksonConfigureListener;

public enum RippleClientFactory {
    ;

    public static Ripple createClient(String baseUrl) {
        final ClientConfig conf = new ClientConfig();
        conf.setJacksonConfigureListener(new JacksonConfigureListener() {
            @Override
            public void configureObjectMapper(ObjectMapper objectMapper) {
                objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            }
        });
        return RestProxyFactory.createProxy(Ripple.class, baseUrl, conf);
    }
}
