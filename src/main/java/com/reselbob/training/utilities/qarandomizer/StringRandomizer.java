package com.reselbob.training.utilities.qarandomizer;

import java.util.Random;

    
// TODO: Auto-generated Javadoc
/**
 * The Class StringRandomizer.
 */
public class StringRandomizer {
    
    /** The simple string. */
    private final String simpleString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    /** The extended string. */
    private final String extendedString = "!@#\"$%^&*()_+=-,.<>?/\\[]':;|`~";
    
    /** The numeric string. */
    private final String numericString = "0987654321";
    
    /** The instance. */
    private static StringRandomizer instance;
    
    /** The random. */
    Random random = new Random();
    
    /**
     * Gets the single instance of StringRandomizer.
     * 
     * @return single instance of StringRandomizer
     */
    public static StringRandomizer getInstance(){
        if (instance == null) {
            instance = new StringRandomizer();
        }
        return instance;
    }
    
    /**
     * Gets the random Simple string, which can include all alphabetic characters
     * upper and lower case.
     * 
     * @param maxLength the maximum length of the string to return
     * 
     * @return the random simple string
     */
    public String getSimpleString(int maxLength){
        return getRandomString(simpleString,maxLength);
    }
    
    /**
     * Gets the random Extended string, which can include all alphabetic characters
     * upper and lower case plus and extended characters such as @%^, etc....
     * 
     * @param maxLength the maximum length of the string to return
     * 
     * @return the random extended string
     */
    public String getExtendedString(int maxLength){
        String str = simpleString + extendedString;
        return getRandomString(str,maxLength);
    }
    
    /**
     * Gets a random Complex string, which can include all alphanumeric characters plus
     * and extended characters such as @%^, etc....
     * 
     * @param maxLength the maximum length of the string to return
     * 
     * @return the random complex string
     */
    public String getComplexString(int maxLength){
        String str = simpleString + extendedString + numericString;
        return getRandomString(str,maxLength);
    }
    
    /**
     * A general string randomizer
     * 
     * @param seed The string that contains the characters which an be
     * included in the string.
     * 
     * @param maxLength the maximum length for the String to return
     * 
     * @return a random string based on the seed String
     */
    private String getRandomString(String seed, int maxLength){
        int numOfChars = random.nextInt(maxLength);
        char[] chars = seed.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<=numOfChars;i++){
            sb.append(chars[random.nextInt(seed.length())]);
        }
        
        return sb.toString();
    }

}
