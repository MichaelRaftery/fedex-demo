package com.hp.demo.fedex;

import com.fedex.ship.stub.*;
import org.apache.axis.types.NonNegativeInteger;
import org.apache.axis.types.PositiveInteger;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Calendar;

/** 
 * Sample code to call the FedEx Ship Service
 * <p>
 * com.fedex.ship.stub is generated via WSDL2Java, like this:<br>
 * <pre>
 * java org.apache.axis.wsdl.WSDL2Java -w -p com.fedex.ship.stub http://www.fedex.com/...../ShipService?wsdl
 * </pre>
 * 
 * This sample code has been tested with JDK 5 and Apache Axis 1.4
 */
// a change fixes defect #1
// second change
// third change
// fourth change
// fifth change
// one more
//Change to test ALI integration fix - *
//Sample code to call the FedEx Ship Service - Domestic Express
//test 11032014 blabla aaabbb
public class ShipWebServiceClient 
{
    public static final String LABEL_LOCATION;
    static {
        LABEL_LOCATION = System.getProperty("java.io.tmpdir")+"\\SV_FedEx_Demo_";
    }
    //
    public static void main(String[] args) 
    {
        ProcessShipmentRequest request = buildRequest();
        //
        try {
            // Initialize the service
            ShipServiceLocator service;
            ShipPortType port;
            //
            service = new ShipServiceLocator();
            updateEndPoint(service);
            port = service.getShipServicePort();
            //
            ProcessShipmentReply reply = port.processShipment(request); // This is the call to the ship web service passing in a request object and returning a reply object
            //
            if (isResponseOk(reply.getHighestSeverity())) // check if the call was successful
            {
                writeServiceOutput(reply);
            }    

            printNotifications(reply.getNotifications());

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    private static ProcessShipmentRequest buildRequest()
    {
        ProcessShipmentRequest request = new ProcessShipmentRequest(); // Build a request object

        request.setClientDetail(createClientDetail());
        request.setWebAuthenticationDetail(createWebAuthenticationDetail());
        // 
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setCustomerTransactionId("java sample - Domestic Express Ship Request"); // The client will get the same value back in the response
        request.setTransactionDetail(transactionDetail);

        //
        VersionId versionId = new VersionId("ship", 9, 0, 0);
        request.setVersion(versionId);
        //
        RequestedShipment requestedShipment = new RequestedShipment();
        requestedShipment.setShipTimestamp(Calendar.getInstance()); // Ship date and time
        requestedShipment.setDropoffType(DropoffType.REGULAR_PICKUP); // Dropoff Types are BUSINESS_SERVICE_CENTER, DROP_BOX, REGULAR_PICKUP, REQUEST_COURIER, STATION
        requestedShipment.setServiceType(ServiceType.PRIORITY_OVERNIGHT); // Service types are STANDARD_OVERNIGHT, PRIORITY_OVERNIGHT, FEDEX_GROUND ...
        requestedShipment.setPackagingType(PackagingType.YOUR_PACKAGING); // Packaging type FEDEX_BOX, FEDEX_PAK, FEDEX_TUBE, YOUR_PACKAGING, ...
        //
        Weight weight = new Weight(); // Total weight information
        weight.setValue(new BigDecimal(50.0));
        weight.setUnits(WeightUnits.LB);
        requestedShipment.setTotalWeight(weight);

        //
        Party shipperParty = new Party(); // Sender information
        Contact shipperContact = new Contact();
        shipperContact.setPersonName("Sender Name");
        shipperContact.setCompanyName("Sender Company Name");
        shipperContact.setPhoneNumber("3127679987");
        Address shipperAddress = new Address();
        shipperAddress.setStreetLines(new String[] {"Address Line 1"});
        shipperAddress.setCity("Austin");
        shipperAddress.setStateOrProvinceCode("TX");
        shipperAddress.setPostalCode("73301");
        shipperAddress.setCountryCode("US");        
        shipperParty.setContact(shipperContact);
        shipperParty.setAddress(shipperAddress);
        requestedShipment.setShipper(shipperParty);
        //
        Party recipientParty = new Party(); // Recipient information
        Contact recipientContact = new Contact();
        recipientContact.setPersonName("Recipient Name");
        recipientContact.setCompanyName("Recipient Company Name");
        recipientContact.setPhoneNumber("9012637906");
        Address recipientAddress = new Address();
        recipientAddress.setStreetLines(new String[] {"Address Line 1"});
        recipientAddress.setCity("Windsor");
        recipientAddress.setStateOrProvinceCode("CT");
        recipientAddress.setPostalCode("06006");
        recipientAddress.setCountryCode("US");
        recipientAddress.setResidential(true);
        recipientParty.setContact(recipientContact);
        recipientParty.setAddress(recipientAddress);
        requestedShipment.setRecipient(recipientParty);

        //
        Payment payment = new Payment(); // Payment information
        payment.setPaymentType(PaymentType.SENDER);
        Payor payor = new Payor();
        payor.setAccountNumber(getPayorAccountNumber());
        payor.setCountryCode("US");
        payment.setPayor(payor);
        requestedShipment.setShippingChargesPayment(payment);
        //
        ShipmentSpecialServicesRequested shipmentSpecialServices = new ShipmentSpecialServicesRequested();
        ShipmentSpecialServiceType shipmentSpecialServiceType[]=new ShipmentSpecialServiceType[1];
        shipmentSpecialServiceType[0]=ShipmentSpecialServiceType.COD;
        shipmentSpecialServices.setSpecialServiceTypes(shipmentSpecialServiceType);
        CodDetail codDetail = new CodDetail();
        codDetail.setCollectionType(CodCollectionType.ANY);
        Money codMoney = new Money();
        codMoney.setCurrency("USD");
        codMoney.setAmount(new BigDecimal(150.0));
        codDetail.setCodCollectionAmount(codMoney);
        shipmentSpecialServices.setCodDetail(codDetail);
        requestedShipment.setSpecialServicesRequested(shipmentSpecialServices);
        
        //
        requestedShipment.setRequestedPackageLineItems(new RequestedPackageLineItem[] {new RequestedPackageLineItem()});
        requestedShipment.getRequestedPackageLineItems(0).setSequenceNumber(new PositiveInteger("1"));
        requestedShipment.getRequestedPackageLineItems(0).setSpecialServicesRequested(new PackageSpecialServicesRequested());
        requestedShipment.getRequestedPackageLineItems(0).getSpecialServicesRequested().setDryIceWeight(new Weight(WeightUnits.LB, new BigDecimal(10.0)));                
        //        
        LabelSpecification labelSpecification = new LabelSpecification(); // Label specification        
        labelSpecification.setImageType(ShippingDocumentImageType.PDF);// Image types PDF, PNG, DPL, ...    
        labelSpecification.setLabelFormatType(LabelFormatType.COMMON2D); //LABEL_DATA_ONLY, COMMON2D
        //labelSpecification.setLabelStockType(LabelStockType.value2); // STOCK_4X6.75_LEADING_DOC_TAB        
        //labelSpecification.setLabelPrintingOrientation(LabelPrintingOrientationType.TOP_EDGE_OF_TEXT_FIRST);
        requestedShipment.setLabelSpecification(labelSpecification);
        //
        RateRequestType rateRequestType[] = new RateRequestType[1];
        rateRequestType[0] = RateRequestType.ACCOUNT; // Rate types requested LIST, MULTIWEIGHT, ...
        requestedShipment.setRateRequestTypes(rateRequestType);
        requestedShipment.setPackageCount(new NonNegativeInteger("1"));
        requestedShipment.setPackageDetail(RequestedPackageDetailType.INDIVIDUAL_PACKAGES);
        //
        RequestedPackageLineItem rp[] = new RequestedPackageLineItem[1];
        Weight rpWeight = new Weight(); // Package weight information
        rpWeight.setValue(new BigDecimal(50.0));
        rpWeight.setUnits(WeightUnits.LB);
        rp[0] = new RequestedPackageLineItem();
        rp[0].setWeight(rpWeight);
        rp[0].setSequenceNumber(new PositiveInteger("1"));
        //
        requestedShipment.setRequestedPackageLineItems(rp);
        //
        request.setRequestedShipment(requestedShipment);
        //
        return request;
    }
    
    private static void writeServiceOutput(ProcessShipmentReply reply) throws Exception
    {
        try
        {
            CompletedShipmentDetail csd = reply.getCompletedShipmentDetail(); 
            CompletedPackageDetail cpd[] = csd.getCompletedPackageDetails();
            System.out.println("Package details\n");
            for (CompletedPackageDetail aCpd : cpd) { // Package details / Rating information for each package
                String trackingNumber = aCpd.getTrackingIds()[0].getTrackingNumber();
                System.out.println("Tracking #: " + trackingNumber
                        + " Form ID: " + aCpd.getTrackingIds()[0].getFormId());
                System.out.println("\nRate details\n");
                //
                PackageRateDetail[] prd = aCpd.getPackageRating().getPackageRateDetails();
                for (PackageRateDetail aPrd : prd) {
                    if (null != aPrd.getBillingWeight())
                        System.out.println("Billing weight: " + aPrd.getBillingWeight().getValue()
                                + " " + aPrd.getBillingWeight().getUnits());
                    if (null != aPrd.getBaseCharge())
                        System.out.println("Base charge: " + aPrd.getBaseCharge().getAmount()
                                + " " + aPrd.getBaseCharge().getCurrency());
                    if (null != aPrd.getNetCharge())
                        System.out.println("Net charge: " + aPrd.getNetCharge().getAmount()
                                + " " + aPrd.getBaseCharge().getCurrency());
                    if (null != aPrd.getSurcharges()) {
                        Surcharge[] s = aPrd.getSurcharges();
                        for (Surcharge value : s) {
                            if (null != value.getSurchargeType())
                                System.out.println(value.getSurchargeType() + " surcharge " +
                                        value.getAmount().getAmount() + " " + value.getAmount().getCurrency());
                        }
                    }
                    if (null != aPrd.getTotalSurcharges())
                        System.out.println("Total surcharge: " + aPrd.getTotalSurcharges().getAmount() + " " +
                                aPrd.getTotalSurcharges().getCurrency());
                    System.out.println("\nRouting details\n");
                    if (null != csd.getRoutingDetail().getUrsaPrefixCode())
                        System.out.println("URSA prefix: " + csd.getRoutingDetail().getUrsaPrefixCode()
                                + " suffix: " + csd.getRoutingDetail().getUrsaSuffixCode());
                    if (null != csd.getRoutingDetail().getCommitDay())
                        System.out.println("Service commitment: " + csd.getRoutingDetail().getCommitDay()
                                + " Airport ID: " + csd.getRoutingDetail().getAirportId());
                    if (null != csd.getRoutingDetail().getDeliveryDay())
                        System.out.println("Delivery day: " + csd.getRoutingDetail().getDeliveryDay());
                }
                //    Write label buffer to file
                ShippingDocument sd = aCpd.getLabel();
                saveLabelToFile(sd, trackingNumber);
                CodReturnShipmentDetail detail = csd.getCodReturnDetail();
                if (detail != null && detail.getLabel() != null) {
                    ShippingDocument codLabel = detail.getLabel();
                    saveCodLabelToFile(codLabel, trackingNumber);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveCodLabelToFile(ShippingDocument shippingDocument, String trackingNumber) throws Exception {
        ShippingDocumentPart[] sdparts = shippingDocument.getParts();
        for (int a=0; a < sdparts.length; a++) {
            ShippingDocumentPart sdpart = sdparts[a];
            String labelLocation = System.getProperty("file.label.location");
            if (labelLocation == null) {
                labelLocation = LABEL_LOCATION;
            }
            String codLabelFileName =  labelLocation + "cod_label." + trackingNumber + "_" + a + ".pdf";
            File codLabelFile = new File(codLabelFileName);
            FileOutputStream fos = new FileOutputStream( codLabelFile );
            fos.write(sdpart.getImage());
            fos.close();
            System.out.println("\nCod return label file name " + codLabelFile.getAbsolutePath());
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + codLabelFile.getAbsolutePath());
        }
    }
    
    private static void saveLabelToFile(ShippingDocument shippingDocument, String trackingNumber) throws Exception {
        ShippingDocumentPart[] sdparts = shippingDocument.getParts();
        for (int a=0; a < sdparts.length; a++) {
            ShippingDocumentPart sdpart = sdparts[a];
            String labelLocation = System.getProperty("file.label.location");
            if (labelLocation == null) {
                labelLocation = LABEL_LOCATION;
            }
            String labelFileName =  labelLocation + "shipping_label." + trackingNumber + "_" + a + ".pdf";
            File labelFile = new File(labelFileName);
            FileOutputStream fos = new FileOutputStream( labelFile );
            fos.write(sdpart.getImage());
            fos.close();
            System.out.println("\nlabel file name " + labelFile.getAbsolutePath());
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + labelFile.getAbsolutePath());
        }
    }
    
    static String getPayorAccountNumber() {
        // See if payor account number is set as system property,
        // if not default it to "XXX"
        String payorAccountNumber = System.getProperty("Payor.AccountNumber");
        if (payorAccountNumber == null) {
            payorAccountNumber = "510087720"; // Replace "XXX" with the payor account number
        }
        return payorAccountNumber;
    }

    private static boolean isResponseOk(NotificationSeverityType notificationSeverityType) {
        if (notificationSeverityType == null) {
            return false;
        }
        if (notificationSeverityType.equals(NotificationSeverityType.WARNING) ||
            notificationSeverityType.equals(NotificationSeverityType.NOTE)    ||
            notificationSeverityType.equals(NotificationSeverityType.SUCCESS)) {
            return true;
        }
         return false;
    }
    
    static ClientDetail createClientDetail() {
        ClientDetail clientDetail = new ClientDetail();
        String accountNumber = System.getProperty("accountNumber");
        String meterNumber = System.getProperty("meterNumber");

        //
        // See if the accountNumber and meterNumber properties are set,
        // if set use those values, otherwise default them to "XXX"
        //
        if (accountNumber == null) {
            accountNumber = "510087720"; // Replace "XXX" with clients account number
        }
        if (meterNumber == null) {
            meterNumber = "118542640"; // Replace "XXX" with clients meter number
        }
        clientDetail.setAccountNumber(accountNumber);
        clientDetail.setMeterNumber(meterNumber);
        return clientDetail;
    }
    
    static WebAuthenticationDetail createWebAuthenticationDetail() {
        WebAuthenticationCredential wac = new WebAuthenticationCredential();
        String key = System.getProperty("key");
        String password = System.getProperty("password");
        
        //
        // See if the key and password properties are set,
        // if set use those values, otherwise default them to "XXX"
        //
        if (key == null) {
            key = "3uO3sffShhP2b77G"; // Replace "XXX" with clients key
        }
        if (password == null) {
            password = "OKdkJWvqzroVK4E2xt1LiQ8iE"; // Replace "XXX" with clients password
        }
        wac.setKey(key);
        wac.setPassword(password);
        return new WebAuthenticationDetail(wac);
    }
    
    private static void printNotifications(Notification[] notifications) {
        System.out.println("Notifications:");
        if (notifications == null || notifications.length == 0) {
            System.out.println("  No notifications returned");
            return;
        }
        for (int i=0; i < notifications.length; i++){
            Notification n = notifications[i];
            System.out.print("  Notification no. " + i + ": ");
            if (n == null) {
                System.out.println("null");
                continue;
            } else {
                System.out.println("");
            }
            NotificationSeverityType nst = n.getSeverity();

            System.out.println("    Severity: " + (nst == null ? "null" : nst.getValue()));
            System.out.println("    Code: " + n.getCode());
            System.out.println("    Message: " + n.getMessage());
            System.out.println("    Source: " + n.getSource());
        }
    }    

    static void updateEndPoint(ShipServiceLocator serviceLocator) {
        String endPoint = System.getProperty("endPoint");
        if (endPoint == null) {
            endPoint = "http://ALM-Client:7200/ShipService";
//            endPoint = "https://wsbeta.fedex.com:443/web-services/ship";
        }
        serviceLocator.setShipServicePortEndpointAddress(endPoint);
    }

}
