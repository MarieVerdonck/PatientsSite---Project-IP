<%-- 
    Document   : header
    Created on : Jul 17, 2017, 4:14:36 AM
    Author     : Marie
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%  String path = "";
    if (request.getParameter("path") != null) {
        path = request.getParameter("path");
    } %>
<header>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>                        
                </button>
                <a class="navbar-brand" href="/ip-project/index.htm">Patient DB</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li><a href="/ip-project/index.htm">Index</a></li>
                    <li><a href="/ip-project/patients.htm">Overview</a></li>
                    <li><a href="/ip-project/form.htm">Add Patient</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>
