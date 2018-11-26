package com.javeriana.sdp.controllers.logout;

import com.javeriana.sdp.GeneralConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sebastian on 25/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 *
 * This class takes care of the logout process of the website
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

    /**
     * The logout view redirects to the index
     * This controller must only remove the admin attribute
     * @param request   the http request
     * @return  the view of the index page
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView defaultRender(HttpServletRequest request) {
        // Set the session attribute to false
        request.getSession().setAttribute(GeneralConstants.ADMIN_KEY, GeneralConstants.SESSION_ATTRIBUTE_FALSE);
        // Render the changes
        return new ModelAndView("redirect:/");
    }

}
