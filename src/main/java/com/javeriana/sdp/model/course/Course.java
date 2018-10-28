package com.javeriana.sdp.model.course;

import com.javeriana.sdp.model.xhtml.serializer.XHTMLSerializer;

import java.util.LinkedList;

/**
 * Created by Sebastian on 28/10/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class Course {

    /**
     * Represents the course id
     */
    private final int id;

    /**
     * Represents the name associated to this course
     */
    private final String name;

    /**
     * Represents a list of topics taught in this course
     */
    private final LinkedList<Topic> topics = new LinkedList();

    /**
     * Constructs a given course
     * @param id    the course id
     * @param name  the course name
     */
    public Course(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the name of the course
     * @return  the name of the course
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the representation of the topics taught
     * @return  the topics
     */
    public LinkedList<Topic> getTopics() {
        return topics;
    }
}
