<%-- 
    Document   : form
    Created on : Jul 19, 2017, 2:04:29 AM
    Author     : Marie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page import="domain.model.Patient"%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Add Patient - Patient DB" />
    </jsp:include>
    <body>
        <div id="container">
            <%@include file="header.jsp" %>
            <div id="screen">
                <div id="content">
                    <h1>Add Patient</h1>
                    <%Patient patient = (Patient) request.getAttribute("newPatient");%>
                    <p>ID: <%= patient.getId() %></p>
                    <form id="addPatientForm" role="form" method="POST" action="createPatient.htm" class="form-horizontal" novalidate>
                        <input name="id" type="text" value="<%= patient.getId() %>" />
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="firstName">First Name*:</label>
                          <div class="col-sm-10">
                              
                            <input type="text" class="form-control" id="firstName" placeholder="First Name" name="firstName" required>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="lastName">Last Name*:</label>
                          <div class="col-sm-10">
                            <input type="text" class="form-control" id="lastName" placeholder="Last Name" name="lastName" required>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="bday">Date of Birth*:</label>
                          <div class="col-sm-10">
                            <input type="text" class="form-control" required id="bdate" name="bdate" pattern="MM/DD/YYY"/>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="address.street">Street*:</label>
                          <div class="col-sm-10">
                            <input type="text" class="form-control" id="address.street" name="address.street" required>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="address.houseNumber">Number*:</label>
                          <div class="col-sm-10">
                            <input type="number" class="form-control" id="address.houseNumber" name="address.houseNumber" required>
                          </div>
                        </div>                        
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="address.addOn">Bus:</label>
                          <div class="col-sm-10">
                            <input type="text" class="form-control" id="address.addOn" name="address.addOn">
                          </div>
                        </div>            
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="address.zipCode">Zip code*:</label>
                          <div class="col-sm-10">
                            <input type="text" class="form-control" id="address.zipCode" name="address.zipCode" required>
                          </div>
                        </div>            
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="address.city">City*:</label>
                          <div class="col-sm-10">
                            <input type="text" class="form-control" id="address.city" name="address.city" required>
                          </div>
                        </div>                     
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="address.state">State:</label>
                          <div class="col-sm-10">
                            <input type="text" class="form-control" id="address.state" name="address.state">
                          </div>
                        </div>                      
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="address.country">Country*:</label>
                          <div class="col-sm-10">
                            <input type="text" class="form-control" id="address.country" name="address.country" required>
                          </div>
                        </div> 
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="weightInKg">Weight(in kg):</label>
                          <div class="col-sm-10">
                              <input type="number" class="form-control" id="weightInKg" name="weightInKg" min="0">
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="heightInCm">Height(in cm):</label>
                          <div class="col-sm-10">
                            <input type="number" class="form-control" id="heightInCm" name="heightInCm" min="0">
                          </div>
                        </div>
                        <div class="form-group">        
                          <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-primary">Add Patient</button>
                          </div>
                        </div>
                      </form>
                </div>
            </div>
        </div>
    </body>
</html>
