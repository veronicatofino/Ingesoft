package com.javeriana.sdp.controllers.news;

import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.sql.SQLUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;

/**
 * Created by Sebastian on 24/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 *
 * This class provides a general view of all the news and its information
 */
@Controller
@RequestMapping("/noticiasgeneral")
public class NewsController {

    /**
     * Represents the new category id
     */
    private static final int CATEGORY_ID = 4;

    /**
     * Represents the default view of the news controller
     * @return the default view
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView defaultRender() {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Poll the content
        String content = SQLUtils.pollContent(CATEGORY_ID, connection);
        // Display all the content with the corresponding new line of html
        content = content.replaceAll("\n", "<br/>");
        // Free the connection
        SQLProvider.getSingleton().dispose(connection);
        // Render the changes
        return new ModelAndView("newsGeneral").addObject("content", content);
    }
}
