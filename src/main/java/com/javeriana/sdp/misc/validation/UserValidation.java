package com.javeriana.sdp.misc.validation;

import com.javeriana.sdp.controllers.ProjectController;
import com.javeriana.sdp.misc.util.HTMLUtils;
import com.javeriana.sdp.sql.SQLProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Sebastian on 20/04/19
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class UserValidation {

    /**
     * This class is not instantiable
     */
    private UserValidation() {}

    /**
     * Validates if whether or not a desired project id is part of an user
     * @param userId    the user id
     * @param projectId the project id
     * @return  true if the {@code projectId project} is part of the {@code userId user}
     * @throws SQLException
     */
    public static final boolean validateProjectBelongsToUser(final int userId, final int projectId) throws SQLException {
        final Connection connection = SQLProvider.getSingleton().take();
        final Statement statement = connection.createStatement();
        final ResultSet set = statement.executeQuery("SELECT * FROM proyectosDeUsuario WHERE userId = '" + userId + "' AND projectId = '" + projectId + "'");
        SQLProvider.getSingleton().dispose(connection);
        return set.next();
    }
}
