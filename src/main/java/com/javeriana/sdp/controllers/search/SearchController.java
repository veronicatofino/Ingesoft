package com.javeriana.sdp.controllers.search;

import com.javeriana.sdp.GeneralConstants;
import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.sql.SQLUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.LinkedList;

/**
 * Created by Sebastian on 25/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 *
 * This controller manages all the search related stuff of the website
 * using a powerful but simple technique to categorize the data in the database
 */
@Controller
@RequestMapping("/busqueda")
public class SearchController {

    /**
     * Represents the display search button flag
     */
    private static final int SEARCH_BUTTON = 0;

    /**
     * Represents the display result button flag
     */
    private static final int DISPLAY_RESULTS = 1;

    /**
     * Performs the search over the whole website content
     * @param word  the word to search for over the whole website
     * @return  the view of the default search controller
     */
    @RequestMapping(method = RequestMethod.POST, params = {"search"})
    public ModelAndView doSearch(@RequestParam(value = "word") String word) {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Mysql pattern matching formatted
        word = "%" + word + "%";
        word = word.toLowerCase();
        String query = "SELECT * from Content WHERE LOWER(data) LIKE '" + word + "'";
        // Take the connection
        final Connection connection = SQLProvider.getSingleton().take();
        final LinkedList<Object[]> searchResult = SQLUtils.getDataFromQuery(connection, query, "categoryId", "data", "redirect");
        final StringBuilder output = new StringBuilder();
        // Generate the dynamic html
        int resultadoId = 1;
        for (Object [] object : searchResult) {
            long id = (Long) object[0];
            String data = (String) object[1];
            String redirect = (String) object[2];
            output.append("<a href=\"").append(redirect).append("?id=").append(id).append("\">").append("Resultado #").append(resultadoId).append("</a>").append("<br>");
            ++ resultadoId;
        }
        // Dispose the connection
        SQLProvider.getSingleton().dispose(connection);
        return new ModelAndView("search").addObject("result", output.toString()).addObject("buttonType", DISPLAY_RESULTS);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get() {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        return new ModelAndView("search").addObject("buttonType", SEARCH_BUTTON);
    }
}
