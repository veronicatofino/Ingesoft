package com.javeriana.sdp.controllers;

import com.javeriana.sdp.constants.SessionConstants;
import com.javeriana.sdp.misc.util.SessionUtils;
import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.misc.util.SpringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

@Controller
@RequestMapping("login")
public class LoginController {

    /**
     * Represents this controller instance alias constant
     */
    public static final String ALIAS = "login";

    /**
     * Represents the message attribute
     */
    private static final String MESSAGE_ATTRIBUTE = "mensaje";

    /**
     * Represents the default message on invalid username or password
     */
    private static final String INVALID_USER_OR_PASSWORD = "Invalid username or password";

    /**
     * Process the default get of this controller
     * @param request   the request instance to provide specific session attributes
     * @return  the default view
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get(HttpServletRequest request) {
        /** Validate that the user hasn't logged in yet **/
        if (SessionUtils.isUserLogged(request)) {
            return new ModelAndView(SpringUtils.redirect(IndexController.ALIAS));
        }
        return new ModelAndView(ALIAS);
    }

    @RequestMapping(method = RequestMethod.POST, params = {"user", "password"})
    public ModelAndView login(HttpServletRequest request, @RequestParam(value = "user") String username,
                              @RequestParam(value = "password") String password) throws SQLException {
        /** Take the connection with the BD **/
        final Connection connection = SQLProvider.getSingleton().take();
        final Statement statement = connection.createStatement();
        final ResultSet set = statement.executeQuery("SELECT * FROM usuarios WHERE username = '" + username + "' AND password = '" + password + "'");
        /** Add the user id to the session and redirect to the home page **/
        if (set.next()) {
            request.getSession().setAttribute(SessionConstants.SESSION_USER_ID, set.getInt("id"));
            return new ModelAndView(SpringUtils.redirect(IndexController.ALIAS));
        }
        /** Release the connection with the BD **/
        SQLProvider.getSingleton().dispose(connection);
        /** Notify wrong password and don't advance **/
        return new ModelAndView(ALIAS).addObject(MESSAGE_ATTRIBUTE, INVALID_USER_OR_PASSWORD);
    }
}
