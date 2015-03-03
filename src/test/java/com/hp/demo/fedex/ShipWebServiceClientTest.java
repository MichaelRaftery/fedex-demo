package com.hp.demo.fedex;


import com.fedex.ship.stub.ClientDetail; 
import com.fedex.ship.stub.ShipServiceLocator;
import com.fedex.ship.stub.WebAuthenticationDetail; 
import junit.framework.Assert;
import org.junit.Test;

/*
Replace the above imports with the following to improve code coverage

import java.math.BigDecimal;
import com.fedex.ship.stub.ClientDetail; 
import com.fedex.ship.stub.RequestedShipment; 
import com.fedex.ship.stub.ShipServiceLocator;
import com.fedex.ship.stub.ShippingDocumentImageType; 
import com.fedex.ship.stub.WebAuthenticationDetail; 
import com.fedex.ship.stub.ProcessShipmentRequest; 
import junit.framework.Assert;
import org.junit.Test;
*/

/*
* Created by panuska on 8/10/12.
*/

public class ShipWebServiceClientTest {

    @Test
    public void testUpdateEndPoint() {
        ShipServiceLocator locator = new ShipServiceLocator();
        ShipWebServiceClient.updateEndPoint(locator);

        Assert.assertEquals(locator.getShipServicePortAddress(), "http://ALM-Client:7200/ShipService");

        final String endPoint = "http://localhost:7200/ShipService";
        System.setProperty("endPoint", endPoint);
        ShipWebServiceClient.updateEndPoint(locator);

        Assert.assertEquals(locator.getShipServicePortAddress(), endPoint);
    }

    @Test
    public void testCreateWebAuthenticationDetail() {
        WebAuthenticationDetail detail = ShipWebServiceClient.createWebAuthenticationDetail();
        Assert.assertEquals(detail.getUserCredential().getKey(), "3uO3sffShhP2b77G");
        Assert.assertEquals(detail.getUserCredential().getPassword(), "OKdkJWvqzroVK4E2xt1LiQ8iE");

        final String key = "7b039521";
        final String password = "bc7945b7dd9b73886179558d75b98c21";

        System.setProperty("key", key);
        System.setProperty("password", password);
        detail = ShipWebServiceClient.createWebAuthenticationDetail();
        Assert.assertEquals(detail.getUserCredential().getKey(), key);
        Assert.assertEquals(detail.getUserCredential().getPassword(), password);
    }

    @Test
    public void testCreateClientDetail() {
        ClientDetail detail = ShipWebServiceClient.createClientDetail();
        Assert.assertEquals(detail.getAccountNumber(), "510087720");
        Assert.assertEquals(detail.getMeterNumber(), "118542640");

        final String accountNumber = "20101021";
        final String meterNumber = "19651170";

        System.setProperty("accountNumber", accountNumber);
        System.setProperty("meterNumber", meterNumber);
        detail = ShipWebServiceClient.createClientDetail();
        Assert.assertEquals(detail.getAccountNumber(), accountNumber);
        Assert.assertEquals(detail.getMeterNumber(), meterNumber);
    }

    @Test
    public void testGetPayorAccountNumber() {
        Assert.assertEquals(ShipWebServiceClient.getPayorAccountNumber(), "510087720");

        final String payorAccountNumber = "20121002";
        System.setProperty("Payor.AccountNumber", payorAccountNumber);
        Assert.assertEquals(ShipWebServiceClient.getPayorAccountNumber(), payorAccountNumber);
    }

    @Test
    public void testBuildRequest() {
    ProcessShipmentRequest request = ShipWebServiceClient.buildRequest();
    Assert.assertEquals(request.getTransactionDetail().getCustomerTransactionId(), "java sample - Domestic Express Ship Request");
    Assert.assertEquals(request.getVersion().getServiceId(), "ship");
    RequestedShipment shipment = request.getRequestedShipment();
    Assert.assertEquals(shipment.getTotalWeight().getValue(), BigDecimal.valueOf(50));
    Assert.assertEquals(shipment.getShipper().getContact().getPhoneNumber(), "0805522713");
    Assert.assertEquals(shipment.getRecipient().getAddress().getCity(), "Windsor");
    Assert.assertEquals(shipment.getLabelSpecification().getImageType(), ShippingDocumentImageType.PDF);
    }

}
