<%-- 
    Document   : head
    Created on : Jul 17, 2017, 4:24:49 AM
    Author     : Marie
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String title = request.getParameter("title");%>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= title %></title>
        <!--  jQuery -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
        <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
        
        <link rel="stylesheet" media="all" href="<c:url value="/resources/css/bootstrap.min.css" />">
        <link rel="stylesheet" media="all" href="<c:url value="/resources/css/style.css" />">
        
        <script src="js/bootstrap.min.js"></script>
</head>
