package com.javeriana.sdp.model.program;

import com.javeriana.sdp.model.course.Course;
import com.javeriana.sdp.model.xhtml.serializer.XHTMLSerializer;

import java.util.LinkedList;

/**
 * Created by Sebastian on 28/10/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class Program  {

    /**
     * Represents the program name
     */
    private final String name;

    /**
     * Represents the url associated to this program
     */
    private final String uri;

    /**
     * Represents a list of all courses associated to this program
     */
    private final LinkedList<Course> courses = new LinkedList();

    /**
     * Constructs a program
     * @param name  the name of the program
     */
    public Program(final String name, final String uri) {
        this.name = name;
        this.uri = uri;
    }

    /**
     * Adds a course to the set of courses taught in this program
     * @param course    the course
     */
    public void addCourse(final Course course) {
        courses.add(course);
    }

    /**
     * Gets the name of this program
     * @return  the name of the program
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the uri of this program
     * @return  the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * Get the courses of this program
     * @return  the courses as a Linked list
     */
    public LinkedList<Course> getCourses() {
        return courses;
    }
}
