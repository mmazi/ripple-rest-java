package com.github.mmazi.ripplerest;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class RippleTest extends TestCase {

    public static final String ADDRESS1 = "rQroSB4DstxmPKNaKou4D2SJLrTL9VsLDh";
    public static final String ADDRESS2 = "rhiAtoMmU2hFuzS6eWdtix29doajBLwatM";

    public static final String ADDRESS1_SECRET = "sanGEaJxs4Q9buPXmYjeGYgvdtzbX";

    public static final String ADDRESS2_SECRET = "ssfSZYo2zikb7brQYHBmcE21rZfAN";

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
        final BalancesResponse balancesResponse = ripple.getBalances(ADDRESS1);
        assertResponse(balancesResponse);
        final List<Amount> balances = balancesResponse.getBalances();
        Assert.assertFalse(balances.isEmpty());
        boolean xrpFound = false;
        for (Amount balance : balances) {
            if (balance.getCurrency().equals("XRP")) {
                xrpFound = true;
                Assert.assertTrue(balance.getValue().intValue() > 0);
            }
        }
        Assert.assertTrue(xrpFound);
    }

    @Test
    public void testGetSettings() throws Exception {
        final SettingsResponse balancesResponse = ripple.getSettings(ADDRESS1);
        assertResponse(balancesResponse);
        final AccountSettings settings = balancesResponse.getSettings();
        Assert.assertTrue(settings.getTransactionSequence() >= 1);
    }

    @Test
    public void testSetSettings() throws Exception {
        AccountSettings settings = ripple.getSettings(ADDRESS1).getSettings();
        final Boolean originalValue = settings.getRequireDestinationTag();
        testSetRequireDestinationTag(settings, !originalValue);
        testSetRequireDestinationTag(settings, originalValue);
    }

    private void testSetRequireDestinationTag(AccountSettings settings, Boolean set) throws IOException {
        settings.setRequireDestinationTag(set);
        final String uuid = createUUID();
        final SettingsResponse result = ripple.setSettings(ADDRESS1, new SetSettingsRequest(ADDRESS1_SECRET, uuid, settings));
        assertResponse(result);
        assertEquals(result.getSettings().getRequireDestinationTag(), set);
        settings = ripple.getSettings(ADDRESS1).getSettings();
        Assert.assertEquals(settings.getRequireDestinationTag(), set);
    }

    // TODO: test new AccountSettings().

    @Test
    public void testPayment() throws Exception {
        final PaymentResponse paymentResponse = ripple.pay(new PaymentRequest(ADDRESS1_SECRET, createUUID(), new Payment(
                ADDRESS1, ADDRESS2, new Amount(BigDecimal.valueOf(1), "XRP")
        )));
        assertResponse(paymentResponse);
        Assert.assertNotNull(paymentResponse.getStatusUrl());
        log.info("Payment status url: {}", paymentResponse.getStatusUrl());
    }

    @Test
    public void testServerInfo() throws Exception {
        final ServerInfoResponse serverInfoResponse = ripple.getServerInfo();
        assertResponse(serverInfoResponse);
        Assert.assertTrue(serverInfoResponse.getRippledServerUrl().contains("://"));
        final RippledServerStatus rippledServerStatus = serverInfoResponse.getRippledServerStatus();
        Assert.assertTrue(rippledServerStatus.getLastClose().getConvergeTimeS() < 100);
        Assert.assertTrue(rippledServerStatus.getValidatedLedger().getSeq() >= 6404992);
    }

    @Test
    public void testServerConnected() throws Exception {
        final ConnectedResponse serverConnected = ripple.isServerConnected();
        assertResponse(serverConnected);
        Assert.assertTrue(serverConnected.isConnected());
    }

    private String createUUID() {
        return UUID.randomUUID().toString();
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