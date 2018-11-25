package com.javeriana.sdp.controllers.index;

import com.javeriana.sdp.controllers.news.NewsController;
import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.sql.SQLUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;

/**
 * Created by Sebastian on 18/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get() {
        String [] news = new String[4];
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Poll the content
        for (int i = 1; i < 5; ++ i) {
            long id = (Long) SQLUtils.getDataFromQuery(connection, "SELECT id FROM ContentCategory WHERE name='noticias_" + i + "'", "id").get(0)[0];
            String content = SQLUtils.pollContent((int) id, connection);
            news[i - 1] = content.replaceAll("\n", "<br/>");
        }
        // Free the connection
        SQLProvider.getSingleton().dispose(connection);
        return new ModelAndView("index").addObject("newsArr", news);
    }
}
