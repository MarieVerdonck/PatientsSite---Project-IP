<%-- 
    Document   : patients
    Created on : Jul 17, 2017, 4:10:54 AM
    Author     : Marie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="domain.model.Patient"%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Overview - Patient DB" />
    </jsp:include>
    <body>
        <div id="container">
            <%@include file="header.jsp" %>
            <div id="screen">
                <div id="content">
                    <h1>Patient Overview</h1>
                    <% ArrayList<Patient> patients = (ArrayList<Patient>) request.getAttribute("patients");%>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Age</th>
                                    <th>Address</th>
                                    <th>Weight</th>
                                    <th>Height</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Patient patient: patients) { %>
                                <tr>
                                    <td><%= patient.getId() %></td>
                                    <td><%= patient.getFirstName() %> <%= patient.getLastName() %></td>
                                    <td><%= patient.getAge() %></td>
                                    <td><%= patient.getAddress().getStreet()!=null ? patient.getAddress() : "none" %></td>
                                    <td><%= patient.getWeightInKg() %></td>
                                    <td><%= patient.getHeightInCm() %></td>
                                    <td><a href="/ip-project/editPatient/<%=patient.getId()%>.htm">edit</a></td>
                                    <td><a href="/ip-project/requestDeletePatient/<%=patient.getId()%>.htm">delete</a></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>  
                    <a href="form.htm" class="btn btn-primary">Add Patient</a> 
                </div>
            </div>
        </div>
    </body>
</html>
