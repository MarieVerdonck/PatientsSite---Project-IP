/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.model.Patient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.PatientService;

/**
 *
 * @author Marie
 */
@Controller
@RequestMapping(value = "/rest/countries")
public class PatientRESTController {
    
    private final PatientService service;
    
    public PatientRESTController(@Autowired PatientService service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    @ResponseBody
    public List<Patient> getPatients() {
        Collection coll = service.read();
        List list;
        if(coll instanceof List) {
            list = (List) coll;
        } else {
            list = new ArrayList(coll);
        }
        return list;
    }
    
}
