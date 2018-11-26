package com.javeriana.sdp.model;

/**
 * Created by Sebastian on 25/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 *
 * This class encapsulates the concept of an email
 */
public class Email {

    /**
     * Represents the email subject
     */
    private final String subject;

    /**
     * Represents from who the message has been sent
     */
    private final String from;

    /**
     * Represents to who the message will go
     */
    private final String to;

    /**
     * Represents the message
     */
    private final String message;

    /**
     * Constructs an email representation
     * @param from  to who
     * @param to    from who
     * @param message   the message
     */
    public Email(final String header, final String from, final String to, final String message) {
        this.subject = header;
        this.from = from;
        this.to = to;
        this.message = message;
    }

    /**
     * Gets the subject of the email
     * @return  the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Gets the from who message
     * @return the from who
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the to who string
     * @return  the to who
     */
    public String getTo() {
        return to;
    }

    /**
     * Gets the message associated with the email
     * @return  the message
     */
    public String getMessage() {
        return message;
    }
}
