package com.github.mmazi.ripplerest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Currencies implements Serializable {
    private List<CurrencyAndIssuer> currencies;

    private Currencies(List<CurrencyAndIssuer> currencies) {
        this.currencies = currencies;
    }

    public static Currencies of(CurrencyAndIssuer ... currencies) {
        return new Currencies(Arrays.asList(currencies));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (CurrencyAndIssuer currency : currencies) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(currency.toString());
        }
        return sb.toString();
    }
}
