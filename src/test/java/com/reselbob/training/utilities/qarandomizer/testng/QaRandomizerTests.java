package com.reselbob.training.utilities.qarandomizer.testng;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.reselbob.training.utilities.qarandomizer.QaRandomizable;
import com.reselbob.training.utilities.qarandomizer.QaRandomizer;
import com.reselbob.training.utilities.qarandomizer.QaRandomizerException;
import com.reselbob.training.utilities.qarandomizer.RandomAddress;

public class QaRandomizerTests {
	private double acceptableVariance = .2;
	private int reps = 10;

	@Test
	public void fullNameIsAcceptable() throws QaRandomizerException {
		QaRandomizable randomizer = QaRandomizer.getInstance();
		int isDupe = 0;
		for (int i = 0; i < reps; i++) {
			String name = randomizer.getFullName();
			String buffer = randomizer.getFullName();
			if (name.equals(buffer)) {
				isDupe++;
			}
		}
		double variance = (double) isDupe / (double) reps;
		Assert.assertTrue(variance <= acceptableVariance);
	}

	@Test
	public void zipCodeIsAcceptable() throws QaRandomizerException {
		int isDupe = 0;
		for (int i = 0; i < reps; i++) {
			String zip = QaRandomizer.getInstance().getZipCode();
			String buffer = QaRandomizer.getInstance().getZipCode();
			if (zip.equals(buffer)) {
				isDupe++;
			}
		}
		double variance = (double) isDupe / (double) reps;
		Assert.assertTrue(variance <= acceptableVariance);
	}

	@Test
	public void isSingletonIsAcceptable() throws QaRandomizerException {
		int isDupe = 0;
		for (int i = 0; i < reps; i++) {
			String name = QaRandomizer.getInstance().getFullName();
			String buffer = QaRandomizer.getInstance().getFullName();
			if (name.equals(buffer)) {
				isDupe++;
			}
		}
		double variance = (double) isDupe / (double) reps;
		Assert.assertTrue(variance <= acceptableVariance);
	}


	@Test
	public void addressIsRandom() throws IllegalArgumentException,
			QaRandomizerException {
		int matches = 0;

		for (int i = 0; i < reps; i++) {
			RandomAddress address = QaRandomizer.getInstance()
					.getAddress();
			RandomAddress buffer = QaRandomizer.getInstance().getAddress();
			if (address.getAddressOne().equals(buffer.getAddressOne())
					&& address.getAddressTwo().equals(buffer.getAddressTwo())
					&& address.getCity().equals(buffer.getCity())
					&& address.getStateProvince().equals(
							buffer.getStateProvince())
					&& address.getPostalCode().equals(buffer.getPostalCode())) {
				matches++;
			}

			double realVariance = (double) matches / (double) reps;
			Assert.assertTrue(matches == 0
					|| (realVariance <= acceptableVariance));
		}
	}

	/**
	 * Tests to see that ssn duplicates no more than the value indicated by
	 * acceptableVariance.
	 * 
	 * @throws QaRandomizerException
	 */
	@Test
	public void ssnIsAcceptable() throws QaRandomizerException {
		int matches = 0;
		for (int i = 0; i < reps; i++) {
			String ssn = QaRandomizer.getInstance().getSSN();
			String buffer = QaRandomizer.getInstance().getSSN();
			if (ssn.equals(buffer)) {
				matches++;
			}
		}
		double variance = (double) matches / (double) reps;
		Assert.assertTrue(variance <= acceptableVariance);
	}
	@Test
	public void newAddressIsValidTest() throws QaRandomizerException, UnsupportedEncodingException{
	    double matches = 0;
	    double localReps = 50;
	    
	    for (int i = 0; i < localReps; i++) {
            try {
                RandomAddress address = QaRandomizer.getInstance()
                        .getAddress();
                Assert.assertFalse(address.getAddressOne()==null, address
                        .asXml());
                Assert.assertFalse(address.getAddressTwo()==null, address
                        .asXml());
                Assert
                        .assertFalse(address.getCity()==null, address
                                .asXml());
                Assert.assertFalse(address.getStateProvince()==null,
                        address.asXml());
                Assert.assertFalse(address.getPostalCode()==null, address
                        .asXml());
                Assert.assertFalse(address.getLongitude()==null, address
                        .asXml());
                Assert.assertFalse(address.getLatitude()==null, address
                        .asXml());
                Assert
                        .assertFalse(address.getType()==null, address
                                .asXml());
            } catch (AssertionError e) {
                matches++;
            }
        }   
	    double variance = (double) matches / (double) localReps;
        Assert.assertTrue(variance <= acceptableVariance);
	}
	
	@Test
	public void canGetReturnFromGetRandomItemTest() throws QaRandomizerException{
	    //make the stooges list
	    Set<Stooge> stooges = new LinkedHashSet<Stooge>();
	    for (int i = 0; i < reps; i++) {
	        String fullName = QaRandomizer.getInstance().getFullName();
	        Stooge stooge = new Stooge(fullName);
	        stooges.add(stooge);
	    }
	    
	    int matches = 0;
	    for (int i = 0; i < reps; i++) {
	        Stooge stooge = QaRandomizer.getInstance().getRandomItem(stooges);
	        Stooge buffer = QaRandomizer.getInstance().getRandomItem(stooges);
            if (stooge.equals(buffer)) {
                matches++;
            }
        }
        double variance = (double) matches / (double) reps;
        Assert.assertTrue(variance <= acceptableVariance);
    
	}
	@Test
	public void humanReadableVerificationTest() throws QaRandomizerException{
	    int max = 200;
	    String str = QaRandomizer.getInstance().getSimpleString(max);
	    System.out.println("Simple: " + str);
	    Assert.assertTrue(str.length()< max);
	    
	    str = QaRandomizer.getInstance().getExtendedString(max);
        System.out.println("Extended: " + str);
        Assert.assertTrue(str.length()< max);
        
        str = QaRandomizer.getInstance().getComplexString(max);
        System.out.println("Complex: " + str);
        Assert.assertTrue(str.length()< max);
	}
	

	private class Stooge{
	    private String stoogeName = null;

        public Stooge(String name){
            this.stoogeName = name;
        }
        /**
         * @return the stoogeName
         */
        public String getStoogeName() {
            return stoogeName;
        }
   
	}
	
}
