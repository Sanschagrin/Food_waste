<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%-- 
    Document   : editRetailerProfile
    Created on : Aug. 6, 2024, 4:08:38 p.m.
    Author     : mylen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="DataAccess.RetailerDTO" %>
<%
    RetailerDTO retailer = null;
    if (session != null) {
        retailer = (RetailerDTO) session.getAttribute("user");
    }
    if (retailer == null) {
        response.sendRedirect("login.jsp");
    }
%>

<f:view>
    <html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Edit Profile - Taste-Not-Waste</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 40px 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 600px;
            text-align: center;
        }
        h1 {
            color: #4CAF50;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .form-group input[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        .form-group input[type="submit"]:hover {
            background-color: #45a049;
        }
        .form-group .error {
            color: red;
            font-size: 0.9em;
        }
        @media (max-width: 600px) {
            .container {
                padding: 20px 10px;
            }
        }
    </style>
</head>
<body>
    <f:view>
        <div class="container">
            <h1>Edit Profile</h1>
            <h:form>
                <div class="form-group">
                    <outputLabel for="retailerName" value="Name:"/>
                    <input type="text" id="retailerName" value="<%=retailer.getRetailerName()%>" required="true"/>
                    <message for="retailerName" class="error"/>
                </div>

                <div class="form-group">
                    <outputLabel for="retailerEmail" value="Email:"/>
                    <input type="text" id="retailerEmail" value="<%=retailer.getRetailerEmail()%>" required="true"/>
                    <h:message for="retailerEmail" class="error"/>
                </div>

                <div class="form-group">
                    <outputLabel for="retailerPassword" value="Password:"/>
                    <input type="password" id="retailerPassword" value="<%=retailer.getRetailerPassword()%>" required="true"/>
                    <h:message for="retailerPassword" class="error"/>
                </div>

                <div class="form-group">
                    <input type="text" id="retailerDescription" value="<%=retailer.getRetailerDescription()%>" required="true"/>
                    <h:message for="retailerDescription" class="error"/>
                </div>

                <div class="form-group">
                    <h:commandButton value="Save Changes" action="${consumerBean.updateProfile}"/>
                </div>
            </h:form>
        </div>
    </f:view>
</body>
</html>