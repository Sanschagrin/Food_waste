<%-- 
    Document   : RegistrationSuccess
    Created on : Aug. 4, 2024, 12:40:07 p.m.
    Author     : ggreg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://xmlns.jcp.org/jsf/core" prefix="f" %>
<%@ taglib uri="http://xmlns.jcp.org/jsf/html" prefix="h" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Registration Successful</title>
        </head>
        <body>
            <h1><h:outputText value="Registration Successful, Welcome to Taste-Not-Waste!"/></h1>
            <p>Thank you for registering. <a href="login.html">Click here to login</a>.</p>
        </body>
    </html>
</f:view>
