package com.javeriana.sdp.controllers;

import com.javeriana.sdp.sql.SQLProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Sebastian on 20/04/19
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
@Controller
@RequestMapping("image")
public class ImageController {

    /**
     * Represents a constant type for project image requests
     */
    private static final String PROJECT_TYPE = "project";

    @RequestMapping(method = RequestMethod.GET, params = {"id", "type"})
    public void get(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id,
                            @RequestParam(value = "type") String type) throws SQLException, IOException {
        /** Query data about this project **/
        final Connection connection = SQLProvider.getSingleton().take();
        final Statement statement = connection.createStatement();
        /** Query from the BD based on the type **/
        String query = "";
        String blobImageAttribute = "image";
        if (type.equals(PROJECT_TYPE)) {
            query = "SELECT contextDiagram as " + blobImageAttribute + " FROM proyectos WHERE id = '" + id + "'";
        }
        /** Parse the blob and notify the server the data **/
        final ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            final InputStream imageStream = resultSet.getBinaryStream(blobImageAttribute);
            final byte [] data = new byte[imageStream.available()];
            imageStream.read(data);
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(data);
        }
        /** Dispose the connection **/
        SQLProvider.getSingleton().dispose(connection);
        response.getOutputStream().close();
    }
}
