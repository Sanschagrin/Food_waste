<%-- 
    Document   : orgHome
    Created on : Aug. 4, 2024, 11:59:41 p.m.
    Author     : ggreg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="your.package.CharitableOrganizationDTO" %>
<%
    HttpSession session = request.getSession(false);
    CharitableOrganizationDTO org = null;
    if (session != null) {
        org = (CharitableOrganizationDTO) session.getAttribute("user");
    }
    if (org == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
    <h1>Welcome, <%= org.getUsername() %>!</h1>
    <a href="logout">Logout</a>
</body>
</html>
</f:view>
