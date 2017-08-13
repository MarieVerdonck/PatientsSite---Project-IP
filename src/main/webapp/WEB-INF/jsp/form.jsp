<%-- 
    Document   : form
    Created on : Jul 19, 2017, 2:04:29 AM
    Author     : Marie
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <jsp:include page="patientForm.jsp">
                        <jsp:param name="action" value="createPatient.htm" />
                    </jsp:include> 
                </div>
            </div>
            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>
