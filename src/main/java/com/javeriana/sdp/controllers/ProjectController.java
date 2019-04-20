package com.javeriana.sdp.controllers;

import com.javeriana.sdp.misc.validation.UserValidation;
import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.misc.util.SessionUtils;
import com.javeriana.sdp.misc.util.SpringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

/**
 * Created by Sebastian on 20/04/19
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
@Controller
@RequestMapping("project")
public class ProjectController {

    /**
     * Represents this controller instance alias constant
     */
    public static final String ALIAS = "project";

    /**
     * Represents the project id attribute
     */
    private static final String PROJECT_ID_ATTRIBUTE = "id";

    /**
     * Represents the project name attribute
     */
    private static final String PROJECT_NAME_ATTRIBUTE = "name";

    /**
     * Represents the project description attribute
     */
    private static final String PROJECT_DESCRIPTION_ATTRIBUTE = "description";

    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ModelAndView get(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id) throws SQLException, IOException {
        /** If the user hasn't logged in redirect to the login screen **/
        if (!SessionUtils.isUserLogged(request)) {
            return new ModelAndView(SpringUtils.redirect(LoginController.ALIAS));
        }
        /** Validate that that project id is part of the user projects (avoid hacking projects that aren't yours) **/
        final int userId = SessionUtils.getUserLogged(request);
        if (!UserValidation.validateProjectBelongsToUser(userId, id)) {
            return new ModelAndView(SpringUtils.redirect(IndexController.ALIAS));
        }
        /** Query data about this project **/
        final Connection connection = SQLProvider.getSingleton().take();
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery("SELECT * FROM proyectos WHERE id = '" + id + "'");
        /** Parse the data **/
        String name = "None";
        String description = "None";
        int projectId = -1;
        if (resultSet.next()) {
            name = resultSet.getString("name");
            description = resultSet.getString("description");
            projectId = resultSet.getInt("id");
        }
        /** Dispose the connection **/
        SQLProvider.getSingleton().dispose(connection);
        return new ModelAndView(ALIAS).addObject(PROJECT_NAME_ATTRIBUTE, name).
                addObject(PROJECT_DESCRIPTION_ATTRIBUTE, description).
                addObject(PROJECT_ID_ATTRIBUTE, projectId);
    }
}
