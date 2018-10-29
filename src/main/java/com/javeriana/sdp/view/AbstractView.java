package com.javeriana.sdp.view;

import com.javeriana.sdp.model.xhtml.serializer.XHTMLSerializer;

/**
 * Created by Sebastian on 28/10/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
public abstract class AbstractView extends XHTMLSerializer {

    /**
     * Constructs the abstract view pre initializing data
     */
    public AbstractView() {
        initializeView();
    }

    /**
     * Pre initializes data before the toString method is called
     */
    public abstract void initializeView();

}
