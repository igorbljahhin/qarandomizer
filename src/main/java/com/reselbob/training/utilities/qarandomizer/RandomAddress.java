package com.reselbob.training.utilities.qarandomizer;

import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

// TODO: Auto-generated Javadoc
/**
 * The class, RandomAddress describes the structure of an address. This object
 * is to be used to provide an address structure that contains random values.
 * 
 * @author Bob Reselman
 * @since 06.10.2009
 */
public class RandomAddress {

    /** The address one. */
    private String addressOne;

    /** The address two. */
    private String addressTwo;

    /** The city. */
    private String city;

    /** The state province. */
    private String stateProvince;

    /** The country. */
    private String country;

    /** The postal code. */
    private String postalCode;
    
    private String longitude;
    
    private String latitude;
    
    private String type;
    
    private String county;

    
    
    /**
     * @return the county
     */
    public String getCounty() {
        return county;
    }


    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    
    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Instantiates a new random address.
     * 
     * @param addressOne the primary address line
     * @param addressTwo the secondary address line
     * @param city the city
     * @param stateProvince in force by locale
     * @param country the country
     * @param postalCode the postal code
     * @param longitude the longitude
     * @param latitude the latitude
     * @param type the type
     */
    public RandomAddress(String addressOne, String addressTwo, String city,
            String stateProvince, String county, String postalCode, String country, 
            String longitude, String latitude, String type) {
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.city = city;
        this.stateProvince = stateProvince;
        this.county = county;
        this.country = country;
        this.postalCode = postalCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
    }

    /**
     * Gets the postal code that corresponds to the locale requirements. In this
     * USA one uses a ZIP code.
     * 
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Gets the state or province names as is relevant to the locale.
     * 
     * @return the stateProvince
     */
    public String getStateProvince() {
        return stateProvince;
    }

    /**
     * Gets the city.
     * 
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the secondary address line. Usually this is denotation of a suite,
     * floor or apartment, etc....
     * 
     * @return the addressTwo
     */
    public String getAddressTwo() {
        return addressTwo;
    }

    /**
     * Gets the primary address line. Usually this is the street name.
     * 
     * @return the addressOne
     */
    public String getAddressOne() {
        return addressOne;
    }

    /**
     * Gets the country.
     * 
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Provides the default toString() behavior for the class.
     * 
     * @return the class's toString() string.
     */
    @Override
    public String toString() {
        return String.format("%s %s %s%s %s %s", this.getAddressOne(), this
                .getAddressTwo(), this.getCity(), this.getStateProvince(), this
                .getPostalCode(), this.getCountry());
    }

    public String asXml() throws UnsupportedEncodingException {
        Document document = DocumentHelper.createDocument();

        Element address = document.addElement("zipcode");
        document.addDocType(null, null, null);
        Element elm = address.addElement("addressOne");
        elm.addText(this.addressOne);

        elm = address.addElement("addressTwo");
        elm.addText(this.addressTwo);

        elm = address.addElement("city");
        elm.addText(this.city);

        elm = address.addElement("stateProvince");
        elm.addText(this.stateProvince);

        elm = address.addElement("postalCode");
        elm.addText(this.postalCode);

        elm = address.addElement("country");
        elm.addText(this.country);
        return document.asXML();
    }

}
