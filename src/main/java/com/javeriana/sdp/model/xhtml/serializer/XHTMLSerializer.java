package com.javeriana.sdp.model.xhtml.serializer;

import com.javeriana.sdp.model.xhtml.builder.XHTMLBuilder;

/**
 * Created by Sebastian on 28/10/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public abstract class XHTMLSerializer {

    /**
     * Represents its own local builder
     */
    private final XHTMLBuilder builder = new XHTMLBuilder();

    /**
     * Returns the builder instance
     * @return  the builder instance
     */
    public XHTMLBuilder getBuilder() {
        return builder;
    }

    /**
     * Notice there is a need to override this
     * @return  the string representation of this serializer, usually this is what will be used
     *          for serialization among the front and backend
     */
    public abstract String toString();
}
