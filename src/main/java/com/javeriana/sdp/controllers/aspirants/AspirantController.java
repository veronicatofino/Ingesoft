package com.javeriana.sdp.controllers.aspirants;

import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.sql.SQLUtils;
import com.javeriana.sdp.utils.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Sebastian on 18/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
@Controller
@RequestMapping("/aspirantes")
public class AspirantController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get(Model model) {
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return new ModelAndView("index");
        }
        // Take a free connection
        final Connection connection = SQLProvider.getSingleton().take();
        // Poll the resources
        HashMap<String, LinkedList<Pair<String, String>>> composition = SQLUtils.pollResourcesComposition(connection, SQLUtils.ASPIRANT_SECTION);
        // Disposes the current connection
        SQLProvider.getSingleton().dispose(connection);
        // Assign the attribute
        return new ModelAndView("aspirant").addObject("composition", composition);
    }
}
