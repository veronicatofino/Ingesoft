package com.javeriana.sdp.controllers.teachers;

import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.sql.SQLUtils;
import com.javeriana.sdp.utils.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.Date;
import java.util.LinkedList;

/**
 * Created by Sebastian on 25/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 *
 * This controller manages all the general names of the professors
 * along with persistence and retrieval of the information
 */
@Controller
@RequestMapping("/profesoresGeneral")
public class TeachersController {

    /**
     * Represents the teacher prefix for the DB queries
     */
    private static final String TEACHER_PREFIX = "profesor_";

    /**
     * Represents the parameter for the button
     */
    private static final String BUTTON_TYPE = "buttonType";

    /**
     * Represents the edit state of the controller
     */
    private static final int EDIT_STATE = 1;

    /**
     * Represents the creation view for the teacher controller
     * @return  the creation view
     */
    @RequestMapping(method = RequestMethod.POST, params = {"editFlag"})
    public ModelAndView createTeacher() {
        /** Requesting to edit the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        return new ModelAndView("teachersGeneral").addObject(BUTTON_TYPE, EDIT_STATE);
    }

    /**
     * Represents the store action for the teacher controller
     * @param name  the name of the teacher
     * @return  the default view controller
     */
    @RequestMapping(method = RequestMethod.POST, params = {"storeFlag", "name"})
    public ModelAndView storeAction(@RequestParam(value = "name") String name) {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Take the date
        if (name.length() == 0) {
            return defaultRender();
        }
        // Parametrize the event
        name = name.replaceAll(" ", "_").toLowerCase();
        name = TEACHER_PREFIX + name;
        // Represents the time to insert into the DB
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Execute all the queries
        SQLUtils.executeQueries(connection, "INSERT INTO ContentCategory (id, name) VALUES (null, '" +  name + "')");
        // Get the id assigned
        long id = (Long) SQLUtils.getDataFromQuery(connection, "SELECT id FROM ContentCategory WHERE name='" + name + "'", "id").get(0)[0];
        // Insert into the remaining tables
        SQLUtils.executeQueries(connection, "INSERT INTO Content (categoryId, data, redirect) VALUES ('" + id + "', 'Profesor: " + name + "', 'profesor')");
        // Free the connection
        SQLProvider.getSingleton().dispose(connection);
        // Render the changes
        return defaultRender();
    }

    /**
     * Represents the default view of the controller
     * @return  the default view
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
        final LinkedList<Pair<Integer, String>> events = SQLUtils.pollCategoryContent(connection, TEACHER_PREFIX + "%");
        // Generate the dynamic html
        final StringBuilder builder = new StringBuilder();
        for (Pair<Integer, String> element : events) {
            /** Normalize the string **/
            String name = element.getRight().substring(TEACHER_PREFIX.length(), element.getRight().length());
            name = name.replaceAll("_", " ");
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            /** Generate the dynamic html **/
            builder.append("<a href=\"profesor?id=").append(element.getLeft()).append("\"><h2>").
                    append(name).
                    append("</h2></a>");
        }
        // Free the connection
        SQLProvider.getSingleton().dispose(connection);
        // Render the changes
        return new ModelAndView("teachersGeneral").addObject("content", builder.toString()).addObject(BUTTON_TYPE, 0);
    }

}
