package com.javeriana.sdp.misc.util;

import com.javeriana.sdp.constants.SessionConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sebastian on 20/04/19
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class SessionUtils {

    /**
     * This class is not instantiable
     */
    private SessionUtils() {}

    /**
     * Validates if whether or not the user is logged in
     * @param request   the servlet request
     * @return  true if an user has logged in, false otherwise
     */
    public static final boolean isUserLogged(final HttpServletRequest request) {
        return request.getSession().getAttribute(SessionConstants.SESSION_USER_ID) != null;
    }

    /**
     * Gets the user id currently logged in
     * @param request   the servlet request
     * @return  the user id logged in
     */
    public static final int getUserLogged(final HttpServletRequest request) {
        return (Integer) request.getSession().getAttribute(SessionConstants.SESSION_USER_ID);
    }
}
