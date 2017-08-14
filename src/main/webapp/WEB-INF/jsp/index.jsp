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
                    <h2>DB Statistics</h2>
                    <% HashMap<String, Double> stats = (HashMap<String, Double>) request.getAttribute("stats");%>
                    <div class="table-responsive">
                        <table class="table">
                            <tbody>
                                <% for (String stat : stats.keySet()) {%>
                                <tr>
                                    <td><%= stat%></td>
                                    <td><%= stats.get(stat)%></td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div> 
                    <h1>External API: healthcare.gov article about Patient Protection and ACA</h1>
                    <p>API Info: (https://www.healthcare.gov/developers/)</p>
                    <h2 id="article-title"></h2>
                    <p>URL: healthcare.gov<span id="article-url"></span></p>
                    <p id="article-content"></p>
                    <script type="text/javascript">
                        $.getJSON('https://www.healthcare.gov/glossary/patient-protection-and-affordable-care-act.json', function (d) {
                            $('#article-title').html(d.title);
                            $('#article-content').html(d.content);
                            $('#article-url').html(d.url);
                        });
                    </script>
                </div>
            </div>
            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>
