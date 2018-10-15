package com.javeriana.sdp.controllers.invariant;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Sebastian on 13/10/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 */
@ManagedBean(name = "DateController")
@ViewScoped
public class DateController implements Serializable {

    @ManagedProperty(value = "#{date}")
    private Date date;

    @PostConstruct
    public void initialize() {
        this.date = GregorianCalendar.getInstance().getTime();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }


}
