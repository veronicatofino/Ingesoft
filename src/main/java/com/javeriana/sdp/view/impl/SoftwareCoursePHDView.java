package com.javeriana.sdp.view.impl;

import com.javeriana.sdp.GeneralConstants;
import com.javeriana.sdp.model.course.Course;
import com.javeriana.sdp.model.postgraduate.PostgraduateManager;
import com.javeriana.sdp.model.xhtml.serializer.XHTMLSerializer;
import com.javeriana.sdp.view.AbstractView;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**2
 * Created by Sebastian on 28/10/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
@ManagedBean(name = "SoftwareCoursePHDView")
@ViewScoped
public class SoftwareCoursePHDView extends AbstractView {

    /**
     * @see AbstractView#initializeView()
     */
    public void initializeView() {
        getBuilder().addHeading(1, PostgraduateManager.SOFTWARE_PHD.getName());
        getBuilder().addHeading(2, GeneralConstants.AVAILABLE_COURSES_TEXT);
        for (Course course : PostgraduateManager.SOFTWARE_PHD.getCourses()) {
            getBuilder().addParagraph(course.getName(), GeneralConstants.DEFAULT_PADDING);
        }
    }

    /**
     * @see XHTMLSerializer#toString()
     * @return the xhtml representation of the software engineering view
     */
    public String toString() {
        return getBuilder().toString();
    }


}
