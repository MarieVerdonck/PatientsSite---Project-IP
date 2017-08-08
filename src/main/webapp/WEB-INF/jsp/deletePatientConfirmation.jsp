<%-- 
    Document   : deletePatientConfirmation
    Created on : Jul 21, 2017, 4:38:45 AM
    Author     : Marie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="domain.model.Patient"%>

<html>
    
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Delete Patient - Patient DB" />
    </jsp:include>

    <body>
        <div id="container">
            <jsp:include page="header.jsp">
                <jsp:param name="path" value="../" />
            </jsp:include>
            <div id="screen">
                <div id="content">
                    <% Patient patient = (Patient) request.getAttribute("patient");%>
                    <h1>Delete patient</h1>
                    <p><%= patient %></p>
                    <p>Are you certain you wish to delete this patient?</p>
                    <form action="/ip-project/deletePatient/<%= patient.getId() %>.htm" method="POST">
                        <input type="submit" name="submit" value="Delete" />
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
