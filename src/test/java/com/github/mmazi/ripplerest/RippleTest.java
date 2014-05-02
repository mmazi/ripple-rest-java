package com.github.mmazi.ripplerest;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import si.mazi.rescu.RestProxyFactory;

import java.util.List;
import java.util.regex.Pattern;

public class RippleTest extends TestCase {
    private static final Logger log = LoggerFactory.getLogger(RippleTest.class);

    private static final Pattern UUID_PATTERN = Pattern.compile("([a-f\\d]{8}(-[a-f\\d]{4}){3}-[a-f\\d]{12}?)");

    private Ripple ripple;

    @BeforeClass
    private void createClient() {
        ripple = RestProxyFactory.createProxy(Ripple.class, "http://localhost:5990/");
    }

    @Test
    public void testGenerateUuid() throws Exception {
        final UuidResponse uuidResponse = ripple.generateUuid();
        assertResponse(uuidResponse);
        final String uuid = uuidResponse.getUuid();
        Assert.assertTrue(UUID_PATTERN.matcher(uuid).matches());
    }

    @Test
    public void testGetBalances() throws Exception {
        final BalancesResponse balancesResponse = ripple.getBalances("rQroSB4DstxmPKNaKou4D2SJLrTL9VsLDh");
        assertResponse(balancesResponse);
        final List<Balance> balances = balancesResponse.getBalances();
        Assert.assertFalse(balances.isEmpty());
        boolean xrpFound = false;
        for (Balance balance : balances) {
            if (balance.getCurrency().equals("XRP")) {
                xrpFound = true;
                Assert.assertTrue(balance.getValue().intValue() > 0);
            }
        }
        Assert.assertTrue(xrpFound);
    }


    private void assertResponse(RippleResponse response) {
        Assert.assertNotNull(response);
        final Boolean success = response.getSuccess();
        if (!success) {
            log.error("Request failed; error: {}; message: {}", response.getError(), response.getMessage());
        }
        Assert.assertTrue(success);
        Assert.assertNotNull(response.getValue());
    }
}