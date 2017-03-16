
package com.reselbob.training.utilities.qarandomizer;
/**
 * The Class QaRandomizerException.
 */
public class QaRandomizerException extends Exception {

    /**
     * Instantiates a new qa randomizer exception.
     * 
     * @param errorMessage
     *            the error message
     */
    public QaRandomizerException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Instantiates a new qa randomizer exception.
     * 
     * @param innerException
     *            the inner exception
     */
    public QaRandomizerException(Exception innerException) {
        super(innerException);
    }

    /**
     * Instantiates a new qa randomizer exception.
     * 
     * @param errorMessage
     *            the error message
     * @param innerException
     *            the inner exception
     */
    public QaRandomizerException(String errorMessage, Exception innerException) {
        super(errorMessage, innerException);
    }

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3880064643943476597L;

}
