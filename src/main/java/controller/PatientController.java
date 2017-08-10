package controller;

import domain.model.Factory;
import domain.model.Patient;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public ModelAndView getIndex() {
        return new ModelAndView("index", "stats", service.getPatientStatistics());
    }
    
    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public ModelAndView getPatientsOverview() {
        return new ModelAndView("patients", "patients", service.read());
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView getFormAddPatient() {
        return new ModelAndView("form", "patient", Factory.createPatient());
    }
    
    @RequestMapping(value = "/createPatient", method = RequestMethod.POST)
    public ModelAndView createPatient(@Valid @ModelAttribute("patient") Patient patient, 
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("form", "patient", patient);
        }
        service.create(patient);
        return new ModelAndView("patients", "patients", service.read());
    }
    
    @RequestMapping(value = "updatePatient", method = RequestMethod.POST)
    @ResponseStatus(value=HttpStatus.OK)
    public @ResponseBody ModelAndView updatePatient(@Valid @ModelAttribute("patient") Patient patient, 
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("updatePatientForm", "patient", patient);
        }
        service.update(patient);
        return new ModelAndView("patients", "patients", service.read());
    }
    
    @RequestMapping(value = "editPatient/{id}", method = RequestMethod.GET)
    public ModelAndView getEditForm(@PathVariable long id) {
        return new ModelAndView("updatePatientForm", "patient", service.find(id));
    }
    
    @RequestMapping(value = "requestDeletePatient/{id}", method = RequestMethod.GET)
    public ModelAndView requestDeletePatient(@PathVariable long id) {
        return new ModelAndView("deletePatientConfirmation", "patient", service.find(id));
    }
    
    @RequestMapping(value = "deletePatient/{id}", method = RequestMethod.POST)
    public ModelAndView deletePatient(@PathVariable int id, @RequestParam String submit) {
        if (submit.equals("Delete")) {
            service.delete(id);
        }
        return new ModelAndView("/patients", "patients", service.read());
    }
    
    @InitBinder
    public void initBinder (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class,
                                    new CustomDateEditor(dateFormatter, true));
    }
    
}
