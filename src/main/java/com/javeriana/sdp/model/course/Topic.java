package com.javeriana.sdp.model.course;

import com.javeriana.sdp.model.xhtml.serializer.XHTMLSerializer;

/**
 * Created by Sebastian on 28/10/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class Topic extends XHTMLSerializer {

    /**
     * Represents the name of the topic
     */
    private final String name;

    /**
     * Constructs a topic based on the name
     * @param name  the name of the topic
     */
    public Topic(final String name) {
        this.name = name;
        /** Generate the builder at instance time because this wont change dinamically **/
        this.getBuilder().addParagraph(name, 2);
    }

    /**
     * @see XHTMLSerializer#toString()
     */
    public String toString() {
        return getBuilder().toString();
    }
}
