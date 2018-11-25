package com.javeriana.sdp.controllers.events;

import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.sql.SQLUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Sebastian on 25/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
@Controller
@RequestMapping("/eventos")
public class EventsSelectorController {

    private static final SimpleDateFormat MYSQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String BUTTON_TYPE = "buttonType";
    private static final int EDIT_STATE = 1;
    private static final int SAVE_STATE = 0;

    @RequestMapping(method = RequestMethod.POST, params = {"editFlag", "id"})
    public ModelAndView editAction(@RequestParam(value = "id") int id) {
        /** Requesting to edit the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Poll the content of the web page
        String content = SQLUtils.pollContent(id, connection);
        // Dispose the connection
        SQLProvider.getSingleton().dispose(connection);
        return new ModelAndView("events").addObject("editableContent", content).addObject(BUTTON_TYPE, SAVE_STATE).addObject("id", id);
    }

    @RequestMapping(method = RequestMethod.POST, params = {"deleteFlag", "id"})
    public String deleteAction(@RequestParam(value = "id") int id) {
        /** Requesting to edit the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return "redirect:/index";
        }
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Delete a desired event
        String delete1 = "DELETE FROM Eventos WHERE id = '" + id + "'";
        String delete2 = "DELETE FROM ContentCategory WHERE id = '" + id + "'";
        String delete3 = "DELETE FROM Content WHERE categoryId = '" + id + "'";
        SQLUtils.executeQueries(connection, delete1, delete2, delete3);
        // Dispose the connection
        SQLProvider.getSingleton().dispose(connection);
        // Redirects to the main section of events
        return "redirect:/eventosgeneral";
    }

    @RequestMapping(method = RequestMethod.POST, params = {"storeFlag", "id", "date"})
    public ModelAndView storeAction(@RequestParam(value = "modifiedContent") String modification, @RequestParam(value = "id") int id, @RequestParam(value = "date") String date) {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Take the date
        if (date.length() == 0) {
            return defaultRender(id);
        }
        // Represents the time to insert into the DB
        final String time = MYSQL_DATE_FORMAT.format(Date.valueOf(date));
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Execute all the queries
        SQLUtils.executeQueries(connection, "UPDATE Content SET data = '" + modification + "' WHERE categoryId = " + id);
        SQLUtils.executeQueries(connection, "UPDATE Eventos SET date = '" + time + "' WHERE Id = " + id);
        // Free the connection
        SQLProvider.getSingleton().dispose(connection);
        // Render the changes
        return defaultRender(id);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ModelAndView defaultRender(@RequestParam(value = "id") int id) {
        /** Requesting to display the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        final Connection connection = SQLProvider.getSingleton().take();
        // Poll the content of the web page
        String content = SQLUtils.pollContent(id, connection);
        // Poll the date
        Timestamp date = (Timestamp) SQLUtils.getDataFromQuery(connection, "SELECT date FROM Eventos WHERE id = '" + id + "'", "date").get(0)[0];
        String dateString = date.toString();
        String [] parts = dateString.split(" ");
        // Put the date in the content
        content = "<B> Fecha: </B>" + parts[0] + "\n" + content;
        // Display all the content with the corresponding new line of html
        content = content.replaceAll("\n", "<br/>");
        // Disposes the current connection
        SQLProvider.getSingleton().dispose(connection);
        // Notice any changes on the button name 'BUTTON_TYPE' will imply modifications on the respective JSP page
        return new ModelAndView("events").addObject("content", content).addObject(BUTTON_TYPE, EDIT_STATE).
                addObject("id", id);
    }
}
