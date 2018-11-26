package com.javeriana.sdp.controllers.news;

import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.sql.SQLUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;

/**
 * Created by Sebastian on 24/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
@Controller
@RequestMapping("/noticias")
public class NewsSelectorController {

    private static final String ID_ATTRIBUTE =  "newsId";
    private static final String BUTTON_TYPE = "buttonType";
    private static final int EDIT_STATE = 1;
    private static final int SAVE_STATE = 0;

    /** Search engine provider **/
    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ModelAndView renderSearchProvider(@RequestParam(value = "id") int id) {
        return defaultRender(id);
    }

    @RequestMapping(method = RequestMethod.POST, params = {"editFlag", ID_ATTRIBUTE})
    public ModelAndView editAction(@RequestParam(value = ID_ATTRIBUTE) long courseId) {
        /** Requesting to edit the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // This can only happen in 20 years
        if (courseId >= Integer.MAX_VALUE) {
            throw new RuntimeException("ID Exceeded the integer max value! Clear the BD :)");
        }
        // Poll the content of the web page
        String content = SQLUtils.pollContent((int) courseId, connection);
        // Dispose the connection
        SQLProvider.getSingleton().dispose(connection);
        return new ModelAndView("news").addObject("editableContent", content).addObject(BUTTON_TYPE, SAVE_STATE).addObject(ID_ATTRIBUTE, courseId);
    }

    @RequestMapping(method = RequestMethod.POST, params = {"storeFlag", ID_ATTRIBUTE})
    public ModelAndView storeAction(@RequestParam(value = "modifiedContent") String modification, @RequestParam(value = ID_ATTRIBUTE) long newsId) {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Execute all the queries
        SQLUtils.executeQueries(connection, "UPDATE Content SET data = '" + modification + "' WHERE categoryId = " + newsId);
        // Free the connection
        SQLProvider.getSingleton().dispose(connection);
        // Render the changes
        return defaultRender(newsId);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"nombre"})
    public ModelAndView defaultRender(@RequestParam(value = "nombre") String name) {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Find the category id
        long id = (Long) SQLUtils.getDataFromQuery(connection, "SELECT id FROM ContentCategory WHERE name='" + name + "'", "id").get(0)[0];
        // Free the connection
        SQLProvider.getSingleton().dispose(connection);
        // Render the changes
        return defaultRender(id);
    }

    private ModelAndView defaultRender(final long id) {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Poll the content
        String content = SQLUtils.pollContent((int) id, connection);
        // Display all the content with the corresponding new line of html
        content = content.replaceAll("\n", "<br/>");
        // Free the connection
        SQLProvider.getSingleton().dispose(connection);
        // Render the changes
        return new ModelAndView("news").addObject("content", content).addObject(BUTTON_TYPE, EDIT_STATE).addObject(ID_ATTRIBUTE, id);
    }
}
