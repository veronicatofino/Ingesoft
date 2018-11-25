package com.javeriana.sdp.model;

/**
 * Created by Sebastian on 25/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
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

    public String getSubject() {
        return subject;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }
}
