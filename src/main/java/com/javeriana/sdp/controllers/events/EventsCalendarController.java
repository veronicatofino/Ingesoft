package com.javeriana.sdp.controllers.events;

import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.sql.SQLUtils;
import com.javeriana.sdp.utils.Pair;
import com.javeriana.sdp.utils.Triplet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Sebastian on 25/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
@Controller
@RequestMapping("/eventoscalendario")
public class EventsCalendarController {

    private static final String [] MONTHS = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
            "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get() {
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Poll the 'news' content
        String query = "SELECT name, date, id from ContentCategory INNER JOIN Eventos USING (id) WHERE date >= now() ORDER BY date ASC";
        final LinkedList<Object []> poll = SQLUtils.getDataFromQuery(connection, query, "name", "date", "id");
        final HashMap<String, LinkedList<Triplet<String, String, Long>>> mapping = new HashMap<String, LinkedList<Triplet<String, String, Long>>>();
        for (int i = 0; i < poll.size(); ++ i) {
            String date = ((Timestamp) poll.get(i)[1]).toString().split(" ")[0];
            String month = MONTHS[Integer.parseInt(date.split("-")[1]) - 1];
            String name = (String) poll.get(i)[0];
            long id = ((Long) poll.get(i)[2]);
            name = name.substring(7);
            name = name.replaceAll("_", " ");
            name = name.substring(0, 1).toUpperCase() + name.substring(1);

            System.out.println("MES: " + month + " evento: " + name);

            if (!mapping.containsKey(month)) {
                mapping.put(month, new LinkedList<Triplet<String, String, Long>>());
            }

            // Limit it to 3 events
            if (mapping.get(month).size() < 3) {
                mapping.get(month).add(new Triplet<String, String, Long>(name, date, id));
            }
        }
        // Free the connection
        SQLProvider.getSingleton().dispose(connection);
        return new ModelAndView("eventsCalendar").addObject("eventsMapping", mapping);
    }
}
