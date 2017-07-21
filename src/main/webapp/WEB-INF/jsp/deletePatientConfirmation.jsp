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
            <%@include file="header.jsp" %>
            <div id="screen">
                <div id="content">
                    <% Patient patient = (Patient) request.getAttribute("patient");%>
                    <h1>Delete patient</h1>
                    <p><%= patient %></p>
                    <p>Are you certain you wish to delete this patient?</p>
                    <form method="POST" action="deletePatient/<%=patient.getId()%>.htm">
                            <input id="confirmDelete" type="submit" value="Yes" />
                    </form>
                    <form method="GET" action="patients.htm">
                            <input id="noDelete" type="submit" value="No" />
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
