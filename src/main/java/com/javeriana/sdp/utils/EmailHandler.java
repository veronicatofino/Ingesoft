package com.javeriana.sdp.utils;

import com.javeriana.sdp.model.Email;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Sebastian on 25/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class EmailHandler {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor();
    protected static final BlockingQueue<Email> QUEUE = new LinkedBlockingQueue<Email>();

    static {
        EXECUTOR_SERVICE.submit(new Service());
    }

    /**
     * This cannot be instantiated
     */
    private EmailHandler() {}

    /**
     * Attempts to send an email
     * @param email the email
     */
    public static void send(final Email email) {
        QUEUE.offer(email);
    }
}

class Service extends Thread {
    private static final String USERNAME = "sdpjaveriana";
    private static final String PASSWORD = "Runescape123";

    @Override
    public void run() {
        for (;;) {
            try {
                final Email email = EmailHandler.QUEUE.take();
                Properties properties = System.getProperties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.setProperty("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                Session session = Session.getInstance(properties,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(USERNAME, PASSWORD);
                            }
                        });

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email.getFrom()));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
                message.setSubject(email.getSubject());
                message.setText(email.getMessage());
                Transport.send(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (AddressException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
