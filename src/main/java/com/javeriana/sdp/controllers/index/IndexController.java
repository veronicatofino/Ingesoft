package com.javeriana.sdp.controllers.index;

import com.javeriana.sdp.GeneralConstants;
import com.javeriana.sdp.controllers.news.NewsController;
import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.sql.SQLUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.LinkedList;

/**
 * Created by Sebastian on 18/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 *
 * This class manages the back end of the index of the website
 */
@Controller
@RequestMapping("/")
public class IndexController {

    /**
     * Process the default get of this controller
     * @param request   the request instance to provide specific session attributes
     * @return  the default view
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get(HttpServletRequest request) {
        String [] news = new String[4];
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Poll the 'news' content
        for (int i = 1; i < 5; ++ i) {
            long id = (Long) SQLUtils.getDataFromQuery(connection, "SELECT id FROM ContentCategory WHERE name='noticias_" + i + "'", "id").get(0)[0];
            String content = SQLUtils.pollContent((int) id, connection);
            news[i - 1] = content.replaceAll("\n", "<br/>");
        }
        // Poll the 'events' content
        int limitVals = 2;
        String query = "SELECT data, date from Content INNER JOIN Eventos ON (id = categoryId) WHERE date >= now() ORDER BY date ASC LIMIT " + limitVals;
        final LinkedList<Object []> poll = SQLUtils.getDataFromQuery(connection, query, "data", "date");
        String [][] eventArr = new String[limitVals][2];
        for (int i = 0; i < 2; ++ i) {
            eventArr[i][0] = ((Timestamp)poll.get(i)[1]).toString().split(" ")[0];
            eventArr[i][1] = (String) poll.get(i)[0];
        }
        // Free the connection
        SQLProvider.getSingleton().dispose(connection);
        // Set the admin flag only the first time
        if (request.getSession().getAttribute(GeneralConstants.ADMIN_KEY) == null) {
            request.getSession().setAttribute(GeneralConstants.ADMIN_KEY, GeneralConstants.SESSION_ATTRIBUTE_FALSE);
        }
        return new ModelAndView("index").addObject("newsArr", news).addObject("eventArr", eventArr);
    }
}
