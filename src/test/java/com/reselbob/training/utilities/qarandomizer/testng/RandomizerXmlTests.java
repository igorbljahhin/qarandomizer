
package com.reselbob.training.utilities.qarandomizer.testng;

import java.io.IOException;
import java.io.StringReader;

import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.exceptions.ConfigurationException;
import org.testng.annotations.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.reselbob.training.utilities.qarandomizer.QaRandomizer;
import com.reselbob.training.utilities.qarandomizer.QaRandomizerException;

public class RandomizerXmlTests extends XMLTestCase {

    @Test
    public void validateAddressXml() throws QaRandomizerException, ConfigurationException, SAXException, IOException {
        String xml = QaRandomizer.getInstance().getAddress().asXml();
        StringReader reader = new StringReader(xml);
        InputSource source = new InputSource(reader);
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.parse(source);
    }
}
