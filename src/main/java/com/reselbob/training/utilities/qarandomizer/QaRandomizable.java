
package com.reselbob.training.utilities.qarandomizer;
// TODO: Auto-generated Javadoc

/**
 * The Interface QaRandomizable describes the basic randomization services that
 * are to be provided by implementing classes.
 * 
 * @author Bob Reselman
 * @since 06.10.2009
 */
public interface QaRandomizable {

    /**
     * Gets a random first name.
     * 
     * @return the first name
     */
    String getFirstName();

    /**
     * Gets a random last name.
     * 
     * @return the last name
     */
    String getLastName();

    /**
     * Gets a random full name for a person.
     * 
     * @return the full name
     */
    String getFullName();

    /**
     * Provides a random social security number in the format ddd-dd-dddd.
     * 
     * @return the SSN.
     */
    String getSSN();

    /**
     * Gets a instance of the RandomAddress object. The data values of the
     * RandomAddress object are random.
     * 
     * @return the address
     */
    RandomAddress getAddress();

    /**
     * Gets a random zip code.
     * 
     * @return the zip code
     */
    String getZipCode();
    
    /**
     * Gets the random Simple string, which can include all alphabetic characters
     * upper and lower case. For example: "eTmekM".
     * 
     * @param maxLength the maximum length of the string to return
     * 
     * @return the random simple string
     */
    String getSimpleString(int maxLength);
    
    /**
     * Gets the random Extended string, which can include all alphabetic characters
     * upper and lower case plus and extended characters such as @%^, etc....
     * For example: yuI^#kJlP".
     * 
     * @param maxLength the maximum length of the string to return
     * 
     * @return the random extended string
     */
    String getExtendedString(int maxLength);
    
    /**
     * Gets a random Complex string, which can include all alphanumeric characters plus
     * and extended characters such as @%^, etc....
     * For example: "7Yt6%e*&@mG"
     * 
     * @param maxLength the maximum length of the string to return
     * 
     * @return the random complex string
     */
    String getComplexString(int maxLength);
    
    /**
     * Gets a random email address.
     * 
     * @return a random email address
     */
    String getEmailAddress();

}
