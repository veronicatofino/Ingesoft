package com.javeriana.sdp.controllers.events;

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
import java.text.SimpleDateFormat;
import java.util.LinkedList;

/**
 * Created by Sebastian on 24/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 *
 * This class is used to provide a fully functional controller to the uri 'eventosgeneral'.
 * This class takes care of the persistence of the data in his own website along with
 * displaying it's data.
 */
@Controller
@RequestMapping("/eventosgeneral")
public class EventsController {

    /**
     * Represents the mysql row acceptance format for dates
     */
    private static final SimpleDateFormat MYSQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Represents the event prefix for the db
     */
    private static final String EVENT_PREFIX = "evento_%";

    /**
     * Represents the button type tag used for the front end to identify whether or not
     * the admin is editing
     */
    private static final String BUTTON_TYPE = "buttonType";

    /**
     * Represents a flag indicating that the website is rendering the edit state of the page
     */
    private static final int EDIT_STATE = 1;

    /**
     * Processes the create event view
     * @return  the default view
     */
    @RequestMapping(method = RequestMethod.POST, params = {"editFlag"})
    public ModelAndView createEvent() {
        /** Requesting to edit the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        return new ModelAndView("eventsGeneral").addObject(BUTTON_TYPE, EDIT_STATE);
    }

    /**
     * Process the store action
     * @param date  the date of the event
     * @param name  the name of the event
     * @return  the default view of this controller
     */
    @RequestMapping(method = RequestMethod.POST, params = {"storeFlag", "date", "name"})
    public ModelAndView storeAction(@RequestParam(value = "date") String date, @RequestParam(value = "name") String name) {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Compare the date size i.e if its valid
        if (date.length() == 0) {
            return defaultRender();
        }
        // Parametrize the event
        name = name.replaceAll(" ", "_").toLowerCase();
        name = "evento_" + name;
        // Represents the time to insert into the DB
        final String time = MYSQL_DATE_FORMAT.format(Date.valueOf(date));
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Execute all the queries
        SQLUtils.executeQueries(connection, "INSERT INTO ContentCategory (id, name) VALUES (null, '" +  name + "')");
        // Get the id assigned
        long id = (Long) SQLUtils.getDataFromQuery(connection, "SELECT id FROM ContentCategory WHERE name='" + name + "'", "id").get(0)[0];
        // Insert into the remaining tables
        SQLUtils.executeQueries(connection, "INSERT INTO Content (categoryId, data, redirect) VALUES ('" + id + "', 'Evento', 'eventos')");
        SQLUtils.executeQueries(connection, "INSERT INTO Eventos (id, date) VALUES ('" + id + "', '" + time + "')");
        // Free the connection
        SQLProvider.getSingleton().dispose(connection);
        // Render the changes
        return defaultRender();
    }

    /**
     * Processes the default view of this controller
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
        final LinkedList<Pair<Integer, String>> events = SQLUtils.pollCategoryContent(connection, EVENT_PREFIX);
        // Generate the dynamic html
        final StringBuilder builder = new StringBuilder();
        for (Pair<Integer, String> element : events) {
            /** Normalize the string **/
            String name = element.getRight().substring(EVENT_PREFIX.length() - 1, element.getRight().length());
            name = name.replaceAll("_", " ");
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            /** Generate the dynamic html **/
            builder.append("<a href=\"eventos?id=").append(element.getLeft()).append("\"><h2>").
                    append(name).
                    append("</h2></a>");
        }
        // Free the connection
        SQLProvider.getSingleton().dispose(connection);
        // Render the changes
        return new ModelAndView("eventsGeneral").addObject("content", builder.toString()).addObject(BUTTON_TYPE, 0);
    }

}


