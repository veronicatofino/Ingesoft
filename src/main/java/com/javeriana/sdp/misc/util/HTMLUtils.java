package com.javeriana.sdp.misc.util;

/**
 * Created by Sebastian on 20/04/19
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class HTMLUtils {

    /**
     * This class is not instantiable
     */
    private HTMLUtils() { }

    /**
     * Represents a new line encoding
     */
    public static final String NEW_LINE ="</br>";

    /**
     * Generates an hyperlink reference for the front end
     * @param redirection   the redirection upon being clicked
     * @param item  the item name that will be displayed on the front end
     * @return  the encoding
     */
    public static final String generateHREF(final String redirection, final String item) {
        return "<a href = \"" + redirection + "\"> " + item + "</a>";
    }

}
