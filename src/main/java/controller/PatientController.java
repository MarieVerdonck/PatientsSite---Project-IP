package controller;

import service.PatientService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Marie
 */
@Controller
public class PatientController {
    
    @Autowired
    private final PatientService service;
    
    public PatientController(@Autowired PatientService service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
    
    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public ModelAndView getPatients() {
        return new ModelAndView("patients", "patients", service.read());
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form() {
        return "form";
    }
    
}
