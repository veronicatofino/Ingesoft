package com.javeriana.sdp.view.impl;

import com.javeriana.sdp.GeneralConstants;
import com.javeriana.sdp.model.postgraduate.PostgraduateManager;
import com.javeriana.sdp.model.xhtml.serializer.XHTMLSerializer;
import com.javeriana.sdp.view.AbstractView;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by Sebastian on 28/10/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 * todo: unused
 */
@ManagedBean(name = "PostgraduateView")
@ViewScoped
public class PostgraduateView extends AbstractView {

    /**
     * @see AbstractView#initializeView()
     */
    public void initializeView() {
        getBuilder().addHeading(1, GeneralConstants.AVAILABLE_PROGRAMS_TEXT);
        getBuilder().addNewLine();
        getBuilder().addLink(PostgraduateManager.ENGINEERING_PHD.getUri(), PostgraduateManager.ENGINEERING_PHD.getName(), GeneralConstants.DEFAULT_PADDING);
    }

    /**
     * @see XHTMLSerializer#toString()
     * @return the xhtml representation of the program view
     */
    public String toString() {
        return getBuilder().toString();
    }
}
