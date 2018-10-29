package com.javeriana.sdp.model.postgraduate;

import com.javeriana.sdp.GeneralConstants;
import com.javeriana.sdp.model.course.Course;
import com.javeriana.sdp.model.program.Program;
import com.javeriana.sdp.model.program.ProgramConstants;

/**
 * Created by Sebastian on 28/10/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public class PostgraduateManager {

    public static final Postgraduate ENGINEERING_PHD =
            new Postgraduate("Doctorado en Ingenieria", PostgraduateType.PHD, GeneralConstants.PHD_URI);

    public static final Program SOFTWARE_PHD =  new Program(ProgramConstants.SOFTWARE_ENGINERING, "/programaSoftwarePHD.xhtml");
    /**
     * Static initialization of the data
     */
    static {
        initialize();
    }

    /**
     * Initializes al the configurations required
     */
    public static void initialize() {
        /** Todo: Change this to query from the database. **/

        /** Software phd program **/
        int courseIdx = 0;
        SOFTWARE_PHD.addCourse(new Course(courseIdx ++, "Lineas de producto de software"));
        SOFTWARE_PHD.addCourse(new Course(courseIdx ++, "Algoritmos de programacion avanzado"));
        SOFTWARE_PHD.addCourse(new Course(courseIdx ++, "Algebra lineal avanzada"));

        ENGINEERING_PHD.addProgram(SOFTWARE_PHD);
    }
}
