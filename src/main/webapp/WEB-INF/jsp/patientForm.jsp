<%-- 
    Document   : patientForm
    Created on : Aug 8, 2017, 11:08:07 AM
    Author     : Marie
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="domain.model.Patient"%>
<%String action = request.getParameter("action");%>
<%Patient patient = (Patient) request.getAttribute("patient");%>
<p>ID: <%= patient.getId()%></p>
<form:form action="<%= action%>" method="post" commandName='patient' cssClass="form-horizontal">
    <form:hidden  path="id" />
    <div class="form-group">
        <label class="control-label col-sm-2" for="firstName">First Name*:</label>
        <div class="col-sm-10">
            <form:input  path="firstName" cssClass="form-control"/>
            <font color="red"> <form:errors path="firstName"></form:errors></font><br/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="lastName">Last Name*:</label>
        <div class="col-sm-10">
            <form:input  path="lastName" cssClass="form-control"/>
            <font color="red"> <form:errors path="lastName"></form:errors></font><br/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="bdate">Birth Date*:</label>
        <div class="col-sm-10">
            <form:input  path="bdate" cssClass="form-control"/>
            <font color="red"> <form:errors path="bdate"></form:errors></font><br/>
        </div>
    </div>
    <h4>Address</h4>
    <div class="form-group">
        <label class="control-label col-sm-2" for="address.street">Street*:</label>
        <div class="col-sm-10">
            <form:input  path="address.street" cssClass="form-control"/>
            <font color="red"> <form:errors path="address.street"></form:errors></font><br/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="address.houseNumber">House Number*:</label>
        <div class="col-sm-10">
            <form:input  path="address.houseNumber" cssClass="form-control"/>
            <font color="red"> <form:errors path="address.houseNumber"></form:errors></font><br/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="address.addOn">Bus:</label>
        <div class="col-sm-10">
            <form:input  path="address.addOn" cssClass="form-control"/>
            <font color="red"> <form:errors path="address.addOn"></form:errors></font><br/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="address.zipCode">Zip Code*:</label>
        <div class="col-sm-10">
            <form:input  path="address.zipCode" cssClass="form-control"/>
            <font color="red"> <form:errors path="address.zipCode"></form:errors></font><br/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="address.city">City*:</label>
        <div class="col-sm-10">
            <form:input  path="address.city" cssClass="form-control"/>
            <font color="red"> <form:errors path="address.city"></form:errors></font><br/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="address.state">State:</label>
        <div class="col-sm-10">
            <form:input  path="address.state" cssClass="form-control"/>
            <font color="red"> <form:errors path="address.state"></form:errors></font><br/>
        </div>
    </div> 
    <div class="form-group">
        <label class="control-label col-sm-2" for="address.country">Country*:</label>
        <div class="col-sm-10">
            <form:input  path="address.country" cssClass="form-control"/>
            <font color="red"> <form:errors path="address.country"></form:errors></font><br/>
        </div>
    </div>
    <h4>Medical</h4>
    <div class="form-group">
        <label class="control-label col-sm-2" for="weightInKg">Weight (in kg)*:</label>
        <div class="col-sm-10">
            <form:input  path="weightInKg" cssClass="form-control"/>
            <font color="red"> <form:errors path="weightInKg"></form:errors></font><br/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="heightInCm">Height (in cm)*:</label>
        <div class="col-sm-10">
            <form:input  path="heightInCm" cssClass="form-control"/>
            <font color="red"> <form:errors path="heightInCm"></form:errors></font><br/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10">
            <input type="submit" value="add patient" class="btn btn-primary">
        </div>
    </div>
</form:form>