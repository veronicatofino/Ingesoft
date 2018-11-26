package com.javeriana.sdp.controllers.login;

import com.javeriana.sdp.GeneralConstants;
import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.sql.SQLUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by Sebastian on 25/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 *
 * This class manages the login controller and handles all the input validation
 * required by the login
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * Default render view
     * @return  the default view
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView defaultRender() {
        // Render the changes
        return new ModelAndView("login");
    }

    /**
     * This request provides full validation of the login
     * @param request   the http request
     * @param username  the username
     * @param password  the password
     * @return  the default view based on the correct or wrong parameters
     */
    @RequestMapping(method = RequestMethod.POST, params = {"send"})
    public ModelAndView attemptLogin(HttpServletRequest request, @RequestParam(value = "user") String username, @RequestParam(value = "password") String password) {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        final Connection connection = SQLProvider.getSingleton().take();
        String query = "SELECT * FROM Admins WHERE username = '" + username + "' AND password = '" + password + "'";
        final LinkedList<Object[]> date = SQLUtils.getDataFromQuery(connection, query, "username", "password");
        if (date.size() == 0) {
            // Wrong username or password
            return new ModelAndView("login").addObject("specialMessage", "Wrong username or password");
        }
        // Fully logged in
        request.getSession().setAttribute(GeneralConstants.ADMIN_KEY, GeneralConstants.SESSION_ATTRIBUTE_TRUE);
        SQLProvider.getSingleton().dispose(connection);
        // Render the changes
        return new ModelAndView("redirect:/");
    }
}
