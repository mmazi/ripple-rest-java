package com.github.mmazi.ripplerest;

import java.util.List;

public class TrustlinesResponse extends RippleResponse<List<Trustline>> {

    private List<Trustline> trustlines;

    public List<Trustline> getTrustlines() {
        return trustlines;
    }

    @Override
    public List<Trustline> getValue() {
        return getTrustlines();
    }
}
