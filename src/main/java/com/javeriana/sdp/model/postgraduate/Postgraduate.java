package com.javeriana.sdp.model.postgraduate;

import com.javeriana.sdp.model.program.Program;

import java.util.LinkedList;

/**
 * Created by Sebastian on 28/10/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class Postgraduate {

    /**
     * Represents the name of the postgraduate
     */
    private final String name;

    /**
     * Represents the type of the postgraduate
     */
    private final PostgraduateType type;

    /**
     * Represents the uri of the postgraduate
     */
    private final String uri;

    /**
     * Represents the list of this postgraduate programs
     */
    private final LinkedList<Program> programs = new LinkedList();

    /**
     * Constructs a postgraduate
     * @param name  the name of the postgraduate
     * @param type  the type
     */
    public Postgraduate(final String name, final PostgraduateType type, final String uri) {
        this.name = name;
        this.type = type;
        this.uri = uri;
    }

    /**
     * Gets the postgraduate name
     * @return  the name
     */
    public String getName() {
        return name;
    }

    /**
     * Appends a program to the postgraduate
     * @param program   the program
     */
    public void addProgram(final Program program) {
        programs.add(program);
    }

    /**
     * Gets the postgraduate type
     * @return  the type
     */
    public PostgraduateType getType() {
        return type;
    }

    /**
     * Gets the uri of the postgraduate
     * @return  the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * Gets the postgraduate programs
     * @return  the programs
     */
    public LinkedList<Program> getPrograms() {
        return programs;
    }
}
