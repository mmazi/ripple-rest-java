package com.github.mmazi.ripplerest;

import si.mazi.rescu.RestProxyFactory;

public enum RippleClientFactory {
    ;

    public static Ripple createClient(String baseUrl) {
        return RestProxyFactory.createProxy(Ripple.class, baseUrl);
    }
}
