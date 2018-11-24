package com.javeriana.sdp.controllers.students;

import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.sql.SQLUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 * Created by Sebastian on 18/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
@Controller
@RequestMapping("/estudiantes")
public class StudentsController {

    private static final String BUTTON_TYPE = "buttonType";
    private static final int CATEGORY_ID = 1;
    private static final int EDIT_STATE = 1;
    private static final int SAVE_STATE = 0;

    @RequestMapping(method = RequestMethod.POST, params = {"editFlag"})
    public ModelAndView editAction(@RequestParam(value = "editFlag") boolean editFlagEnabled) {
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
        return new ModelAndView("students").addObject("editableContent", content).addObject(BUTTON_TYPE, SAVE_STATE);
    }

    @RequestMapping(method = RequestMethod.POST, params = {"storeFlag"})
    public ModelAndView storeAction(HttpServletRequest request, HttpServletResponse response) {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        String modification = request.getParameter("modifiedContent");
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // todo: Notice for other courses you have to check if the data exists in the table before updating
        // todo: if not then create it and then alter
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
        return new ModelAndView("students").addObject("content", content).addObject(BUTTON_TYPE, EDIT_STATE);
    }
}
