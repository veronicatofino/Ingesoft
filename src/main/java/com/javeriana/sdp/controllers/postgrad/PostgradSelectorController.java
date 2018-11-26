package com.javeriana.sdp.controllers.postgrad;

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
@RequestMapping("/postgrados")
public class PostgradSelectorController {

    private static final String BUTTON_TYPE = "buttonType";
    private static final int CATEGORY_ID = 3;
    private static final int EDIT_STATE = 1;
    private static final int SAVE_STATE = 0;

    /** Search engine provider **/
    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ModelAndView renderSearchProvider(@RequestParam(value = "id") int id) {
        return defaultRender();
    }

    @RequestMapping(method = RequestMethod.POST, params = {"editFlag"})
    public ModelAndView editAction() {
        /** Requesting to edit the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Poll the content of the web page
        String content = SQLUtils.pollContent(CATEGORY_ID, connection);
        // Dispose the connection
        SQLProvider.getSingleton().dispose(connection);
        return new ModelAndView("postgrad").addObject("editableContent", content).addObject(BUTTON_TYPE, SAVE_STATE);
    }

    @RequestMapping(method = RequestMethod.POST, params = {"storeFlag"})
    public ModelAndView storeAction(@RequestParam(value = "modifiedContent") String modification) {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        /** dynamically allocating postgrads programs **/
        SQLUtils.allocateDynamicAttributes(connection, modification.toLowerCase(), "programa?nombre", "programa");
        // Execute all the queries
        SQLUtils.executeQueries(connection, "UPDATE Content SET data = '" + modification + "' WHERE categoryId = " + CATEGORY_ID);
        // Free the connection
        SQLProvider.getSingleton().dispose(connection);
        // Render the changes
        return defaultRender();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView defaultRender() {
        /** Requesting to display the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        final Connection connection = SQLProvider.getSingleton().take();
        // Poll the content of the web page
        String content = SQLUtils.pollContent(CATEGORY_ID, connection);
        // Display all the content with the corresponding new line of html
        content = content.replaceAll("\n", "<br/>");
        // Disposes the current connection
        SQLProvider.getSingleton().dispose(connection);
        // Assign the attribute
        // Notice any changes on the button name 'BUTTON_TYPE' will imply modifications on the respective JSP page
        return new ModelAndView("postgrad").addObject("content", content).addObject(BUTTON_TYPE, EDIT_STATE);
    }
}
