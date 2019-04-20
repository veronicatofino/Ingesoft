package com.javeriana.sdp.controllers;

/**
 * Created by Sebastian on 19/04/19
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */

import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.misc.util.HTMLUtils;
import com.javeriana.sdp.misc.util.SpringUtils;
import com.javeriana.sdp.misc.util.SessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

@Controller
@RequestMapping("index")
public class IndexController {

    /**
     * Represents this controller instance alias constant
     */
    public static final String ALIAS = "index";

    /**
     * Represents the attribute for the dynamic urls
     */
    private static final String DYNAMIC_URLS_ATTRIBUTE = "out";

    /**
     * Process the default get of this controller
     * @param request   the request instance to provide specific session attributes
     * @return  the default view
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get(HttpServletRequest request) throws SQLException {
        /** If the user hasn't logged in redirect to the login screen **/
        if (!SessionUtils.isUserLogged(request)) {
            return new ModelAndView(SpringUtils.redirect(LoginController.ALIAS));
        }
        /** Gather the user id **/
        final int id = SessionUtils.getUserLogged(request);
        final Connection connection = SQLProvider.getSingleton().take();
        final Statement statement = connection.createStatement();
        final ResultSet set = statement.executeQuery("SELECT * FROM proyectosDeUsuario INNER JOIN proyectos ON(proyectosDeUsuario.projectId = proyectos.id) WHERE userId = '" + id + "'");
        /** Build the links to the projects dynamically **/
        StringBuilder output = new StringBuilder();
        while (set.next()) {
            output.append(HTMLUtils.generateHREF((ProjectController.ALIAS + "?id=" + set.getInt("proyectos.id")), set.getString("proyectos.name"))).append(HTMLUtils.NEW_LINE);
        }
        /** Disponse the connection **/
        SQLProvider.getSingleton().dispose(connection);
        return new ModelAndView(ALIAS).addObject(DYNAMIC_URLS_ATTRIBUTE, output.toString());
    }

}
