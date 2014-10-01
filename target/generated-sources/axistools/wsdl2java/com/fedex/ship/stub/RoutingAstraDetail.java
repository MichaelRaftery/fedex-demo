/**
 * RoutingAstraDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fedex.ship.stub;


/**
 * The tracking number information and the data to form the Astra
 * barcode for the label.
 */
public class RoutingAstraDetail  implements java.io.Serializable {
    /* The tracking number information for the shipment. */
    private com.fedex.ship.stub.TrackingId trackingId;

    private com.fedex.ship.stub.StringBarcode barcode;

    /* The textual description of the special service applied to the
     * package. */
    private java.lang.String astraHandlingText;

    private com.fedex.ship.stub.AstraLabelElement[] astraLabelElements;

    public RoutingAstraDetail() {
    }

    public RoutingAstraDetail(
           com.fedex.ship.stub.TrackingId trackingId,
           com.fedex.ship.stub.StringBarcode barcode,
           java.lang.String astraHandlingText,
           com.fedex.ship.stub.AstraLabelElement[] astraLabelElements) {
           this.trackingId = trackingId;
           this.barcode = barcode;
           this.astraHandlingText = astraHandlingText;
           this.astraLabelElements = astraLabelElements;
    }


    /**
     * Gets the trackingId value for this RoutingAstraDetail.
     * 
     * @return trackingId   * The tracking number information for the shipment.
     */
    public com.fedex.ship.stub.TrackingId getTrackingId() {
        return trackingId;
    }


    /**
     * Sets the trackingId value for this RoutingAstraDetail.
     * 
     * @param trackingId   * The tracking number information for the shipment.
     */
    public void setTrackingId(com.fedex.ship.stub.TrackingId trackingId) {
        this.trackingId = trackingId;
    }


    /**
     * Gets the barcode value for this RoutingAstraDetail.
     * 
     * @return barcode
     */
    public com.fedex.ship.stub.StringBarcode getBarcode() {
        return barcode;
    }


    /**
     * Sets the barcode value for this RoutingAstraDetail.
     * 
     * @param barcode
     */
    public void setBarcode(com.fedex.ship.stub.StringBarcode barcode) {
        this.barcode = barcode;
    }


    /**
     * Gets the astraHandlingText value for this RoutingAstraDetail.
     * 
     * @return astraHandlingText   * The textual description of the special service applied to the
     * package.
     */
    public java.lang.String getAstraHandlingText() {
        return astraHandlingText;
    }


    /**
     * Sets the astraHandlingText value for this RoutingAstraDetail.
     * 
     * @param astraHandlingText   * The textual description of the special service applied to the
     * package.
     */
    public void setAstraHandlingText(java.lang.String astraHandlingText) {
        this.astraHandlingText = astraHandlingText;
    }


    /**
     * Gets the astraLabelElements value for this RoutingAstraDetail.
     * 
     * @return astraLabelElements
     */
    public com.fedex.ship.stub.AstraLabelElement[] getAstraLabelElements() {
        return astraLabelElements;
    }


    /**
     * Sets the astraLabelElements value for this RoutingAstraDetail.
     * 
     * @param astraLabelElements
     */
    public void setAstraLabelElements(com.fedex.ship.stub.AstraLabelElement[] astraLabelElements) {
        this.astraLabelElements = astraLabelElements;
    }

    public com.fedex.ship.stub.AstraLabelElement getAstraLabelElements(int i) {
        return this.astraLabelElements[i];
    }

    public void setAstraLabelElements(int i, com.fedex.ship.stub.AstraLabelElement _value) {
        this.astraLabelElements[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RoutingAstraDetail)) return false;
        RoutingAstraDetail other = (RoutingAstraDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.trackingId==null && other.getTrackingId()==null) || 
             (this.trackingId!=null &&
              this.trackingId.equals(other.getTrackingId()))) &&
            ((this.barcode==null && other.getBarcode()==null) || 
             (this.barcode!=null &&
              this.barcode.equals(other.getBarcode()))) &&
            ((this.astraHandlingText==null && other.getAstraHandlingText()==null) || 
             (this.astraHandlingText!=null &&
              this.astraHandlingText.equals(other.getAstraHandlingText()))) &&
            ((this.astraLabelElements==null && other.getAstraLabelElements()==null) || 
             (this.astraLabelElements!=null &&
              java.util.Arrays.equals(this.astraLabelElements, other.getAstraLabelElements())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getTrackingId() != null) {
            _hashCode += getTrackingId().hashCode();
        }
        if (getBarcode() != null) {
            _hashCode += getBarcode().hashCode();
        }
        if (getAstraHandlingText() != null) {
            _hashCode += getAstraHandlingText().hashCode();
        }
        if (getAstraLabelElements() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAstraLabelElements());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAstraLabelElements(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RoutingAstraDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v9", "RoutingAstraDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackingId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v9", "TrackingId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v9", "TrackingId"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("barcode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v9", "Barcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v9", "StringBarcode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("astraHandlingText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v9", "AstraHandlingText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("astraLabelElements");
        elemField.setXmlName(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v9", "AstraLabelElements"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://fedex.com/ws/ship/v9", "AstraLabelElement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
