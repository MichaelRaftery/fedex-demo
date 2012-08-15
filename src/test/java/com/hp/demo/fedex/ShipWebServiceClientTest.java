package com.hp.demo.fedex;

import com.fedex.ship.stub.ShipServiceLocator;
import com.fedex.ship.stub.WebAuthenticationDetail;
import junit.framework.Assert;
import org.junit.Test;

/**
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
}
