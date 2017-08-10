<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@ page import="domain.model.Patient"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Index - Patient DB" />
    </jsp:include>

    <body>
        <div id="container">
            <%@include file="header.jsp" %>
            <div id="screen">
                <div id="content">
                    <h1>Patient Database</h1>
                    <p>Welcome to the patient database. Feel free to add, delete, update or look up patients.</p>
                    <p>Login: User - admin, PW - admin</p>
                    <h2>Statistic</h2>
                    <% HashMap<String, Double> stats = (HashMap<String, Double>) request.getAttribute("stats");%>
                    <div class="table-responsive">
                        <table class="table">
                            <tbody>
                                <% for (String stat: stats.keySet()) { %>
                                <tr>
                                    <td><%= stat %></td>
                                    <td><%= stats.get(stat) %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>  
                </div>
            </div>
        </div>
    </body>
</html>
