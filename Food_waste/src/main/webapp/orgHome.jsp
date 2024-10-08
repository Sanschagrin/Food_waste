<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%-- 
    Document   : orgHome
    Created on : Aug. 4, 2024, 11:59:41 p.m.
    Author     : mylen
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="DataAccess.CharitableDTO" %>
<%
    CharitableDTO charitable = null;
    if (session != null) {
        charitable = (CharitableDTO) session.getAttribute("user");
    }
    if (charitable == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consumer Home - Taste-Not-Waste</title>
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        a {
            color: #4CAF50;
            text-decoration: none;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
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
    <h1>Welcome, <%= charitable.getCharitableName() %>!</h1>
            <table>
                <tr>
                    <th>Consumer ID</th>
                    <td><%= charitable.getCharitableId() %></td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td><%= charitable.getCharitableName() %></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td><%= charitable.getCharitableEmail() %></td>
                </tr>
                <tr>
                    <th>Password</th>
                    <td>********</td> <!-- Masked password for security -->
                </tr>
                <tr>
                    <th>Description</th>
                    <td><%= charitable.getCharitableDescription() %></td>
                </tr>
                <tr>
                    <th>Claim History</th>
                    <td>OwO</td>
                </tr>
            </table>
            <p><a href="Home.jsp">Go Home</a></p>
        </div>
    </f:view>

</body>
</html>
