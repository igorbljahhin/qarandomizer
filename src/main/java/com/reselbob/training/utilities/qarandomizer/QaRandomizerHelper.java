package com.reselbob.training.utilities.qarandomizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * The Class QaRandomizerHelper.
 */
class QaRandomizerHelper {

    /**
     * Gets the random item. Use this method when you want to have the helper
     * create a new Random object internally.
     * 
     * @param set
     *            the set of type <T> from which to return an item.
     * 
     * @return the random item of type T
     * 
     * @throws IllegalArgumentException
     *             the illegal argument exception
     */
    <T> T getRandomItem(Set<T> set) {
        Random random = new Random();
        return getRandomItem(set, random);
    }

    /**
     * Gets the random item.
     * 
     * @param set
     *            the set of type <T> from which to return an item
     * @param random
     *            , an existing Random object. You can pass in an existing
     *            Random object when you want to your code to be working with a
     *            common Random object. Passing an existing Random object means
     *            that the initial seed is in place. The probability of true
     *            random behavior is great when the same seed is in place from
     *            use to use.
     * 
     * @return the random item of type T
     * 
     * @throws IllegalArgumentException
     *             the illegal argument exception
     */
    <T> T getRandomItem(Set<T> set, Random random)
        throws IllegalArgumentException {
        // Make sure that the Set in not null
        if (set == null) {
            throw new IllegalArgumentException("You passed a set that is null.");
        }

        // Make sure that there are names for extraction
        if (set.isEmpty()) {
            throw new IllegalArgumentException(
                    "You passed a set that has no items.");
        }

        int i = random.nextInt(set.size());
        if (set.toArray()[i] != null) {
            return (T) set.toArray()[i];
        }

        return null;

    }

    /**
     * This is a helper method that fetches an Xml file that is embedded as a
     * resources as an InputStream and converts that input stream into a string
     * that represents an in memory representation of that Xml file
     * 
     * @param resourceFilename
     *            the name of the xml file. You do NOT need to prepend a '/'
     *            symbol to the file name. This method make the prepend for you.
     * @return the xml file as a string
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    String getXmlFileString(String resourceFilename) throws IOException {
        //ClassLoader loader = ClassLoader.getSystemClassLoader();
        // InputStream is = loader.getResourceAsStream( String.format("%s",
        // resourceFilename));
        InputStream is = this.getClass().getResourceAsStream(
                String.format("/%s", resourceFilename));
        return convertStreamToString(is);
    }

    /**
     * This is a helper method that converts an InputStream into a string. This
     * method is used by the class to create in memory representation of Xml
     * text files
     * 
     * @param inputStream
     *            the InputStream to convert to a string
     * 
     * @return the string
     * @throws IOException
     * @see InputStream
     */
    protected static String convertStreamToString(InputStream inputStream)
        throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        // try {
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }

    /**
     * This method creates a Set<String> of names from the a particular Xml file
     * name list. The Set<String> is an in memory copy of names that are used by
     * the class to get a random name.
     * 
     * Please be aware that this method assumes that the name node in the Xml
     * file is a child node of the root element
     * 
     * @param resourceFilename
     *            the resource XML filename of a name list
     * 
     * @return the Set< string> the list of names
     * 
     * @throws DocumentException
     *             that a Dom4J Document exception has occurred.
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    Set<String> parseNameListFromXml(String resourceFilename)
        throws DocumentException, IOException {
        // initialize a list
        Set<String> set = new LinkedHashSet<String>();
        // get the name Xml list as a string of Xml
        String xml = this.getXmlFileString(resourceFilename);
        // Let Dom4J name a document object
        Document doc = DocumentHelper.parseText(xml);
        Element root = doc.getRootElement();
        // Traverse the root element and get the name out
        for (Iterator i = root.elementIterator(); i.hasNext();) {
            Element el = (Element) i.next();
            String str = el.getTextTrim();
            set.add(str);
        }
        return set;
    }
}
