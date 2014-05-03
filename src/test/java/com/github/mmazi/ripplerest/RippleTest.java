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
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

public class RippleTest extends TestCase {

    public static final String ADDRESS1 = "rDiWNyxZqEfRrdsNbPPwgBUZFb4Xf17cNJ";
    public static final String ADDRESS2 = "rhiAtoMmU2hFuzS6eWdtix29doajBLwatM";

    public static final String ADDRESS2_SECRET = "sanGEaJxs4Q9buPXmYjeGYgvdtzbX";
    public static final String ADDRESS1_SECRET = "ssx95aBiN5YYHWsuHrosLqyqyJsp2";

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

    // todo! make this work
    @Test
    public void testPayment() throws Exception {
        final String uuid = createUUID();
//        final String uuid = "f2f811b7-dc3b-4078-a2c2-e4ca9e453981";
//        final String uuid = ripple.generateUuid().getUuid();
        final BigDecimal value = BigDecimal.valueOf(new Random().nextInt(8) + 1);
        final CreatePaymentResponse createPaymentResponse = ripple.createPayment(new PaymentRequest(ADDRESS1_SECRET, uuid, new Payment(
                ADDRESS1, ADDRESS2, new Amount(value, "XRP")
        )));
        assertResponse(createPaymentResponse);
        Assert.assertNotNull(createPaymentResponse.getStatusUrl());
        log.info("Payment status url: {}", createPaymentResponse.getStatusUrl());
        PaymentResponse paymentResponse = ripple.getPayment(ADDRESS1, null, uuid);
        assertResponse(paymentResponse);
        Payment pmt = paymentResponse.getPayment();
        Assert.assertEquals(pmt.getSourceAmount().getValue(), value);
        final String pmtHash = pmt.getHash();
        paymentResponse = ripple.getPayment(ADDRESS1, pmtHash, null);
        assertResponse(paymentResponse);
        pmt = paymentResponse.getPayment();
        Assert.assertEquals(pmt.getSourceAmount().getValue(), value);
        Assert.assertEquals(paymentResponse.getClientResourceId(), uuid);
    }

    @Test
    public void testGetPayments() throws Exception {
        final PaymentsResponse paymentsResponse = ripple.getPayments(ADDRESS1, null, false, null, null, true, 10, 1);
        assertResponse(paymentsResponse);
        final List<PaymentWithId> payments = paymentsResponse.getPayments();
        log.info("Got {} payments.", payments.size());
        Assert.assertFalse(payments.isEmpty());
        for (PaymentWithId paymentWithId : payments) {
            final Payment pmt = paymentWithId.getPayment();
            Assert.assertTrue(Arrays.asList(pmt.getSourceAccount(), pmt.getDestinationAccount()).contains(ADDRESS1));
            Assert.assertTrue(pmt.getSourceAmount().getValue().floatValue() > 0);
        }
    }

    // todo! make this work
    @Test
    public void testTrustlines() throws Exception {
        TrustlinesResponse trustlinesResponse = ripple.getTrustlines(ADDRESS1, null, null);
        assertResponse(trustlinesResponse);
        final int initialTrustlines = trustlinesResponse.getTrustlines().size();
        log.debug("initialTrustlines = {}", initialTrustlines);

        final BigDecimal value = BigDecimal.ONE;
        final String clientResourceId = createUUID();
        final AddTrustlineResponse addTrustlineResponse =
                ripple.addTrustline(ADDRESS1, AddTrustlineRequest.create(ADDRESS1_SECRET, true, new Trustline(value, "USD", ADDRESS2), clientResourceId));
        assertResponse(addTrustlineResponse);

        final Trustline addedTrustline = addTrustlineResponse.getTrustline();
        Assert.assertEquals(addedTrustline.getAccount(), ADDRESS1);
        Assert.assertEquals(addedTrustline.getCounterparty(), ADDRESS2);
        Assert.assertEquals(addedTrustline.getCurrency(), "USD");
        Assert.assertEquals(addedTrustline.getLimit(), value);


        trustlinesResponse = ripple.getTrustlines(ADDRESS1, null, null);
        assertResponse(trustlinesResponse);
        final List<Trustline> finalTrustlines = trustlinesResponse.getTrustlines();
        Assert.assertEquals(finalTrustlines.size(), initialTrustlines + 1);

        boolean addedFound = false;
        for (Trustline finalTrustline : finalTrustlines) {
            if (finalTrustline.getHash().equals(addedTrustline.getHash())) {
                addedFound = true;
            }
        }
        Assert.assertTrue(addedFound);
    }

    @Test
    public void testGetNotification() throws Exception {
        final String hash = "C61B3B8849EE84B17B767F0BC5F2BC5CD8D40161C931EAF921CB7201C0417CBC";
        final NotificationResponse notificationResponse = ripple.getNotifictaion(ADDRESS2, hash);
        assertResponse(notificationResponse);
        final Notification notification = notificationResponse.getNotification();
        Assert.assertEquals(notification.getHash(), hash);
        Assert.assertEquals(notification.getAccount(), ADDRESS2);
        Assert.assertTrue(notification.getTimestamp().getTime() < System.currentTimeMillis());
        Assert.assertTrue(notification.getTimestamp().getTime() > 1399046367);
        Assert.assertTrue(notification.getTransactionUrl().contains(hash));
    }

    @Test
    public void testGetServerInfo() throws Exception {
        final ServerInfoResponse serverInfoResponse = ripple.getServerInfo();
        assertResponse(serverInfoResponse);
        Assert.assertTrue(serverInfoResponse.getRippledServerUrl().contains("://"));
        final RippledServerStatus rippledServerStatus = serverInfoResponse.getRippledServerStatus();
        Assert.assertTrue(rippledServerStatus.getLastClose().getConvergeTimeS() < 100);
        Assert.assertTrue(rippledServerStatus.getValidatedLedger().getSeq() >= 6404992);
    }

    @Test
    public void testIsServerConnected() throws Exception {
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
        Assert.assertTrue(success, "Request failed: " + response.getError() + ": " + response.getMessage());
        Assert.assertNotNull(response.getValue());
    }
}
