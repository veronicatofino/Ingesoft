package com.javeriana.sdp.controllers.events;

import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.sql.SQLUtils;
import com.javeriana.sdp.utils.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by Sebastian on 24/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
@Controller
@RequestMapping("/eventosgeneral")
public class EventsController {

    private static final String EVENT_PREFIX = "evento_%";

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
        final LinkedList<Pair<Integer, String>> events = SQLUtils.pollCategory(connection, EVENT_PREFIX);
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
        return new ModelAndView("eventsGeneral").addObject("content", builder.toString());
    }

}


