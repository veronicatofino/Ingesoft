package com.javeriana.sdp.view.impl;

import com.javeriana.sdp.GeneralConstants;
import com.javeriana.sdp.model.postgraduate.PostgraduateManager;
import com.javeriana.sdp.model.program.Program;
import com.javeriana.sdp.model.xhtml.serializer.XHTMLSerializer;
import com.javeriana.sdp.view.AbstractView;
import jdk.nashorn.internal.runtime.GlobalConstants;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by Sebastian on 28/10/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 * todo: Note that if we keep using this same format we will need to create a class for phd view, master view and specialization view
 * todo: The solution to this is keep an attribute attached to each user, whenever the user clicks master
 * todo: Change the state to master and render using one general view which finds which type he wants
 * todo: and recalculate the configurations for the user.
 */
@ManagedBean(name = "PHDView")
@ViewScoped
public class PHDView extends AbstractView {

    /**
     * @see AbstractView#initializeView()
     */
    public void initializeView() {
        getBuilder().addHeading(1, GeneralConstants.AVAILABLE_PROGRAMS_TEXT);
        getBuilder().addNewLine();
        for (Program program : PostgraduateManager.ENGINEERING_PHD.getPrograms()) {
            getBuilder().addLink(program.getUri(), program.getName(), GeneralConstants.DEFAULT_PADDING);
        }
    }

    /**
     * @see XHTMLSerializer#toString()
     * @return the xhtml representation of the program view
     */
    public String toString() {
        return getBuilder().toString();
    }
}
