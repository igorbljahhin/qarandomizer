/**
 * 
 */
package com.reselbob.training.utilities.qarandomizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * This class is a Singleton that provides data randomization services.
 * 
 * @throws QaRandomizerException *
 * 
 * @author BobReselman
 * @since 07.02.2009
 */
public final class QaRandomizer implements QaRandomizable {

    /**
     * Constructor for EdmundsRandomizer.
     * 
     * Please note that the constructor is private in order to support the
     * Singleton Design Pattern
     * 
     * @throws QaRandomizerException , a custom Exception
     */
    private QaRandomizer() throws QaRandomizerException {
        firstNameList = this.getFirstNamesList();
        lastNameList = this.getLastNamesList();
        zipCodeRootElement = this.createZipCodeRootElement();
        zipCodeList = this.getZipCodesList();
        emailDomainTypes = getEmailDomainType();
    }

    /** The instance. */
    private static QaRandomizer instance;

    // Constant values that describe the name of the Xml files
    // containing the data for the firstName, lastName, and USPS zipcode
    // files
    /** The Constant FIRST_NAME_FILESPEC. */
    private static final String FIRST_NAME_FILESPEC = "firstName.xml";

    /** The Constant LAST_NAME_FILESPEC. */
    private static final String LAST_NAME_FILESPEC = "lastName.xml";

    /** The Constant ZIPCODES_FILESPEC. */
    private static final String ZIPCODES_FILESPEC = "zipcodes.xml";

    // An in memory copy of an Xml element that contains child nodes that
    // have zipcode data as defined in the zipcode Xml file.
    /** The zip code root element. */
    private static Element zipCodeRootElement = null;

    /** The zip code list. */
    private Set<String> zipCodeList = null;

    /** The first name list. */
    private Set<String> firstNameList = null;

    /** The last name list. */
    private Set<String> lastNameList = null;

    /** The member Random object. */
    private Random random = new Random();

    /** The helper. */
    private QaRandomizerHelper helper = null;
    
    private Set<String> emailDomainTypes = null;

    /**
     * Gets the singleton instance of QaRandomizer.
     * 
     * @return single instance of QaRandomizer
     * 
     * @throws QaRandomizerException , a custom Exception
     */
    public static QaRandomizer getInstance() throws QaRandomizerException {
        if (instance == null) {
            instance = new QaRandomizer();
        }

        return instance;
    }

    /**
	*
	*
	* @return a RandomAddress object that contains random city, state, county, longitude and latitude
	*/
	protected RandomAddress getAddressFromZipCode(String zipcode) {
        String country = "USA";
        String state = null;
        String county = null;
        String city = null;
        String longitude = null;
        String latitude = null;
        String type = null;
        
        for (Iterator i = this.zipCodeRootElement.elementIterator(); i
                .hasNext();) {
            Element el = (Element) i.next();
            if (el.elementText("zip").equals(zipcode)) {
                state = el.elementText("state");
                county = el.elementText("county");
                city = el.elementText("city");
                latitude = el.elementText("latitude");
                longitude = el.elementText("longitude");
                type = el.elementText("type");
                break;
            }
        }

        RandomAddress address = new RandomAddress(this.getAddressOne(), this
                .getAddressTwo(), city, state, county, zipcode, country,longitude,latitude,type);
        return address;
    }

    /**
     * Gets the first names list, the list of of first names from which the random
	 * first name is extracted.
     * 
     * @return the first names list
     * 
     * @throws QaRandomizerException , a custom Exception
     * 
     * @see com.reselbob.training.utilities.qarandomizer.AbstractRandomizer#getFirstNamesList()
     */
    protected Set<String> getFirstNamesList() throws QaRandomizerException {
        Set<String> set;
        String errorMsg = "There is a problem loading the first name data";
        try {
            set = new QaRandomizerHelper()
                    .parseNameListFromXml(FIRST_NAME_FILESPEC);
        } catch (DocumentException e) {
            throw new QaRandomizerException(errorMsg);
        } catch (IOException e) {
            throw new IllegalArgumentException(String
                    .format("There is a problem loading the last data"));
        }
        return set;
    }

    /**
     * Gets the last names list, a list of last names from which the random last
	 * name is extracted.
     * 
     * @return the last names list
     * 
     * @throws QaRandomizerException , a custom Exception
     * 
     * @see com.reselbob.training.utilities.qarandomizer.AbstractRandomizer#getLastNamesList()
     */
    protected Set<String> getLastNamesList() throws QaRandomizerException {
        Set<String> set;
        String errorMsg = "There is a problem loading the first name data";
        try {
            set = new QaRandomizerHelper()
                    .parseNameListFromXml(LAST_NAME_FILESPEC);
        } catch (DocumentException e) {
            throw new QaRandomizerException(errorMsg);
        } catch (IOException e) {
            throw new IllegalArgumentException(String
                    .format("There is a problem loading the last data"));
        }
        return set;
    }

    /**
     * Gets the list is United States Post Service zip codes.
     * 
     * @return the zip codes list
     * 
     * @throws QaRandomizerException , a custom Exception
     * 
     * @see com.reselbob.training.utilities.qarandomizer.AbstractRandomizer#getZipCodesList()
     */
    protected Set<String> getZipCodesList() throws QaRandomizerException {

        String errorMessage = "There is a problem, check the inner exception";

        try {
            return this
                    .getZipCodeListFromRoot(QaRandomizer.zipCodeRootElement);
        } catch (DocumentException e) {
            throw new QaRandomizerException(errorMessage, e);
        }
    }

    /**
     * Creates the zip code root element.
     * 
     * @return the element
     * 
     * @throws QaRandomizerException , a custom Exception
     */
    private Element createZipCodeRootElement() throws QaRandomizerException {
        // load the zip code root element
        String xml;
        String errorMessage = "There is a problem, check the inner exception";
        try {
            xml = this.getHelper().getXmlFileString(ZIPCODES_FILESPEC);
        } catch (IOException e) {
            throw new QaRandomizerException(errorMessage, e);
        }
        Document doc;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            throw new QaRandomizerException(errorMessage, e);
        }
        return doc.getRootElement();
    }

    /**
     * Gets Address One. Address One is typically the street address
     * 
     * @return the address one
     */
    private String getAddressOne() {
        String streetType = this.getHelper().getRandomItem(
                this.getStreetTypes(), this.getClassRandom());
        return String
                .format("%d %s %s", this.getClassRandom().nextInt(1000), this
                        .getHelper().getRandomItem(getStreetNameList()),
                        streetType);
    }

    /**
     * Gets the AddressTwo string. The structure for an Address Two string is
     * [Random AddressTwo Type] [Random Number] [Random Number] is between 0-99
     * 
     * @return the address two
     */
    private String getAddressTwo() {
        String twoType = new QaRandomizerHelper().getRandomItem(this
                .getAddressTwoTypeList(), this.getClassRandom());
        return String.format("%s %d", twoType, random.nextInt(100));
    }

    /**
     * Gets the AddressTwo data, a list of strings that contain AddressTwo types, 
	 * such as "MailStop", "Suite", etc....
     * 
     * @return the address two type list
     */
    protected Set<String> getAddressTwoTypeList() {
        Set<String> set = new LinkedHashSet<String>();
        set.add("Apt.");
        set.add("Suite");
        set.add("Room");
        set.add("MailStop");
        set.add("Desk");
        set.add("Floor");
        return set;
    }

    /**
     * Gets the street name list, the list that is used to construct street names.
     * This list is generated from internally defined values.
     * 
     * @return the street name list
     */
    protected Set<String> getStreetNameList() {
        Set<String> set = new LinkedHashSet<String>();
        set.add("Main");
        set.add("Elm");
        set.add("Spruce");
        set.add("Cherry");
        set.add("Pine");
        set.add("Orange");
        set.add("Jefferson");
        set.add("Washington");
        set.add("Lincoln");
        set.add("Roosevelt");
        set.add("Hollywood");
        set.add("Olympic");
        set.add("Madison");

        return set;

    }

    /**
     * Gets a Set<String> that contains street address types, such as St., Ave,
     * Blvd, etc...
     * 
     * @return the street types
     */
    protected Set<String> getStreetTypes() {
        Set<String> set = new LinkedHashSet<String>();
        set.add("St.");
        set.add("Ave.");
        set.add("Blvd.");
        set.add("Ct.");
        set.add("Dr.");
        set.add("Terr.");
        set.add("Ln.");

        return set;
    }

    /**
     * Gets the zip code list.
     * 
     * @param zipCodeRoot the zip code root
     * 
     * @return the zip code list
     * 
     * @throws DocumentException the document exception
     */
    private Set<String> getZipCodeListFromRoot(Element zipCodeRoot)
        throws DocumentException {
        Set<String> set = new LinkedHashSet<String>();
        for (Iterator i = zipCodeRoot.elementIterator(); i.hasNext();) {
            Element el = (Element) i.next();
            Element buffer = el.element("zip");
            String str = buffer.getTextTrim();

            set.add(str);
        }
        return set;
    }

    /**
     * Gets the QaRandomizerHelper class that provides access 
     * to the internal XML files that contain address by zip code
     * information, first name and last name data. Also, provides
     * intelligence for return a random item from a strong types Set<>.
     * 
     * @return the helper
     */
    protected QaRandomizerHelper getHelper() {
        if (helper == null) {
            helper = new QaRandomizerHelper();
        }

        return helper;
    }

    public RandomAddress getAddress() {
        String zipcode = this.getHelper().getRandomItem(zipCodeList, random);
        return this.getAddressFromZipCode(zipcode);
    }


    public String getFirstName() {
        return this.getHelper().getRandomItem(firstNameList, random);
    }

    public String getFullName() {
        return String.format("%s %s", this.getFirstName(), this.getLastName());
    }

    public String getLastName() {
        return new QaRandomizerHelper().getRandomItem(lastNameList, random);
    }

    public String getSSN() {
        return String.format("%d%d%d-%d%d-%d%d%d%d", random.nextInt(10), random
                .nextInt(10), random.nextInt(10), random.nextInt(10), random
                .nextInt(10), random.nextInt(10), random.nextInt(10), random
                .nextInt(10), random.nextInt(10));
    }

    public String getZipCode() {
        return new QaRandomizerHelper().getRandomItem(zipCodeList, random);
    }

    /**
     * This method allows you to get a random item from a Set<T>. The generic
     * nature of the Set<T> allows you to pass any collection of type, T and get
     * back an item from that collection.
     * 
     * @param set , a Set<T> collection, where T is the Type upon that is
     * supported by the generic Set.
     * 
     * @return the random item
     */
    public <T> T getRandomItem(Set<T> set) {return new QaRandomizerHelper().getRandomItem(set);}

    /**
     * This is the accessor that retrieves the class instance of the Random
     * object. Use this method if you want to access the common Random object.
     * 
     * @return the class random
     */
    protected Random getClassRandom() {
        return random;
    }


    public String getComplexString(int maxLength){
        return StringRandomizer.getInstance().getComplexString(maxLength);
    }

 
    public String getExtendedString(int maxLength) {
        return StringRandomizer.getInstance().getExtendedString(maxLength);
    }


    public String getSimpleString(int maxLength) {
        return StringRandomizer.getInstance().getSimpleString(maxLength);
    }


    public String getEmailAddress() {
        String fn = getFirstName().replace(" ", "_").replace(".", "_").replace("-", "_");
        String ext = getRandomItem(emailDomainTypes);
        String domain = getSimpleString(20);
        
        return fn + "@" + domain + "." + ext;
    }
    
    /**
     * Gets a list of email domain types.
     * 
     * @return a list of email domain types
     */
    private Set<String> getEmailDomainType(){
        Set<String> list = new HashSet<String>();
        list.add("com");
        list.add("net");
        list.add("gov");
        list.add("edu");
        list.add("org");
        list.add("mil");
        list.add("biz");
        list.add("int");
        list.add("us");
        list.add("tv");
        
        return list;
    }
}
