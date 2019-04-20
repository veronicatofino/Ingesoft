package com.javeriana.sdp.misc.util;

/**
 * Created by Sebastian on 20/04/19
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class SpringUtils {

    /**
     * This class is not instantiable
     */
    private SpringUtils() { }

    public static final String redirect(final String view) {
        return "redirect:/" + view;
    }
}
