<%-- 
    Document   : updatePatientForm
    Created on : Aug 8, 2017, 10:50:27 AM
    Author     : Marie
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="domain.model.Patient"%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Update Patient - Patient DB" />
    </jsp:include>
    <body>
        <div id="container">
            <jsp:include page="header.jsp">
                <jsp:param name="path" value="../" />
            </jsp:include> 
            <div id="screen">
                <div id="content">
                    <h1>Add Patient</h1>
                    <jsp:include page="patientForm.jsp">
                        <jsp:param name="action" value="/ip-project/updatePatient.htm" />
                    </jsp:include> 
                </div>
            </div>
        </div>
    </body>
</html>