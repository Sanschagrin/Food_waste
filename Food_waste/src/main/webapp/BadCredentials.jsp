<%-- 
    Document   : BadCredentials
    Created on : Aug. 4, 2024, 12:52:56 p.m.
    Author     : ggreg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Bad Credentials</title>
        </head>
        <body>
            <h1><h:outputText value="Login Error"/></h1>
            <p>Invalid username or password. Please <a href="login.html">try again</a>.</p>
        </body>
    </html>
</f:view>
