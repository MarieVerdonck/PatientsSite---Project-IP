/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Patient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.PatientService;

/**
 *
 * @author Marie
 */
@Controller
@RequestMapping(value = "/rest")
@EnableWebMvc
public class PatientRESTController {

    private final PatientService service;

    @Autowired
    public PatientRESTController(@Autowired PatientService service) {
        this.service = service;
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void getPatients(HttpServletResponse response) throws JsonProcessingException, JSONException, IOException {
        Collection coll = service.read();
        List<Patient> list;
        if (coll instanceof List) {
            list = (List) coll;
        } else {
            list = new ArrayList(coll);
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(list);

        JSONArray array = new JSONArray();
        for (Patient pat : list) {
            try {
                ObjectMapper jackmapper = new ObjectMapper();
                String json = jackmapper.writeValueAsString(pat);
                JSONObject jsonObj = new JSONObject(json);
                array.put(jsonObj);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // setting the response type to json
        response.setContentType("application/json");
        // setting the CORS request, CrossOriginResourceSharing
        // so that this url/response is accessible in another domain (angular application for example) 
        response.setHeader("Access-Control-Allow-Origin", "*");

        response.getWriter().write(array.toString());
    }

}
