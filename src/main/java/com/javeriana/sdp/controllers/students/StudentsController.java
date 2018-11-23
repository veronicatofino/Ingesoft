package com.javeriana.sdp.controllers.students;

import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.sql.SQLUtils;
import com.javeriana.sdp.utils.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Sebastian on 18/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
@Controller
@RequestMapping("/estudiantes")
public class StudentsController {

    private static final String SPECIAL_TOKEN = "@";
    private static final String BUTTON_TYPE = "buttonType";
    private static final int EDIT_STATE = 1;
    private static final int SAVE_STATE = 0;
    private static final String STUDENTS_INFO_TABLE = "STUDENTSINFO";

    @RequestMapping(method = RequestMethod.POST, params = {"editFlag"})
    public ModelAndView editAction(@RequestParam(value = "editFlag") boolean editFlagEnabled) {
        /** Requesting to edit the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Poll the resources
        HashMap<String, LinkedList<Pair<String, String>>> composition = SQLUtils.pollResourcesComposition(connection, SQLUtils.STUDENTS_SECTION);
        // Disposes the current connection
        SQLProvider.getSingleton().dispose(connection);
        return new ModelAndView("students").addObject("editComposition", composition).addObject(BUTTON_TYPE, SAVE_STATE);
    }

    @RequestMapping(method = RequestMethod.POST, params = {"storeFlag"})
    public ModelAndView storeAction(HttpServletRequest request, HttpServletResponse response) {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // mapping of the modifications done
        final HashMap<String, StudentInfoModifierDescriptor> modifications = new HashMap<String, StudentInfoModifierDescriptor>();
        // Parse al modifications
        for (Map.Entry<String, String []> map : request.getParameterMap().entrySet()) {
            String key = map.getKey();
            String value = map.getValue()[0];
            // Not an important key
            if (!key.contains(SPECIAL_TOKEN)) {
                continue;
            }
            // This key is useful, tokenize it
            String [] parts = key.split(SPECIAL_TOKEN);
            String root = parts[0];
            String topic = parts[1];
            String type = parts[2];

            // Don't process empty stuff
            if (value.length() == 0) {
                continue;
            }

            /** Construct the descriptor **/
            if (!modifications.containsKey(topic)) {
                StudentInfoModifierDescriptor descriptor = new StudentInfoModifierDescriptor();
                descriptor.setMainTopic(root);
                descriptor.setPreviousTopicName(topic);
                modifications.put(topic, descriptor);
            }
            // Alter descriptor, note that order here doesn't matter because hashmaps don't preserve order
            // So you have to be careful
            final StudentInfoModifierDescriptor descriptor = modifications.get(topic);
            if (type.equalsIgnoreCase("URL")) {
                descriptor.setNewUrl(value);
            } else {
                descriptor.setNewTopicName(value);
            }
        }
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Clear the table
        SQLUtils.clearTable(STUDENTS_INFO_TABLE, connection);
        // Poll the resource mapping
        final HashMap<String, Integer> resourceMapping = SQLUtils.pollResourcesSections(connection);
        // Create the queue of queries
        final String [] queries = new String[modifications.size()];
        // Start the queue of the updates
        int offset = 0;
        for (Map.Entry<String, StudentInfoModifierDescriptor> entry : modifications.entrySet()) {
            final StudentInfoModifierDescriptor descriptor = entry.getValue();
            // Set the resource section as integer (used for the query)
            descriptor.setResourceSection(resourceMapping.get(descriptor.getMainTopic()));
            // Queue the update
            queries[offset ++] = descriptor.toSql();
        }
        // Execute all the queries
        SQLUtils.executeQueries(connection, queries);
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
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Poll the resources
        HashMap<String, LinkedList<Pair<String, String>>> composition = SQLUtils.pollResourcesComposition(connection, SQLUtils.STUDENTS_SECTION);
        // Disposes the current connection
        SQLProvider.getSingleton().dispose(connection);
        // Assign the attribute
        // Notice any changes on the button name 'BUTTON_TYPE' will imply modifications on the respective JSP page
        return new ModelAndView("students").addObject("composition", composition).addObject(BUTTON_TYPE, EDIT_STATE);
    }
}
