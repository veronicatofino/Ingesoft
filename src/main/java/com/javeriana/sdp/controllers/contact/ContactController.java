package com.javeriana.sdp.controllers.contact;

import com.javeriana.sdp.model.Email;
import com.javeriana.sdp.sql.SQLProvider;
import com.javeriana.sdp.utils.EmailHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Sebastian on 25/11/18
 * Email: Juan.2114@hotmail.com
 * Email: Juan2114@javerianacali.edu.co
 *
 * This class is used to provide a fully functional controller to the uri 'contacto'.
 * This class takes care of the email service that communicates with the admin
 */
@Controller
@RequestMapping("/contacto")
public class ContactController {

    /**
     * This request is used to provide an email to the email handler to execute
     * the email sending action
     * @param name  the name of the person
     * @param message   the message of the person
     * @param emailTxt  the email of the person
     * @return  the view of the index
     */
    @RequestMapping(method = RequestMethod.POST, params = {"send"})
    public String postMessage(@RequestParam(value = "name") String name,
                                      @RequestParam(value = "message") String message,
                                      @RequestParam(value = "email") String emailTxt) {
        /** Requesting to persist the content **/
        if (!SQLProvider.getSingleton().hasFreeConnections()) {
            // Notice this shouldn't happen because we won't have too many requests
            return "redirect:/";
        }
        final String adminMail = "juanrico2114@gmail.com";
        String body = "Hola administrador!\n\nEl usuario " + name + " te hizo la siguiente pregunta:\n";
        body += message + "\n\n";
        body += "El correo del usuario es: " + emailTxt + ". Animate a responder!" + "\n";
        body += "Att: El equipo de respuesta de SDP Javeriana!";
        final Email email = new Email("!nueva pregunta de: " + name + "!", emailTxt, adminMail, body);
        EmailHandler.send(email);
        // Render the changes
        return "redirect:/";
    }

}
