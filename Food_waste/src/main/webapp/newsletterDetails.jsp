<%-- 
    Document   : newsLetterDetails
    Created on : Aug. 7, 2024, 1:07:45 p.m.
    Author     : ggreg
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DataAccess.NewsletterDAO" %>
<%@ page import="DataAccess.NewsletterDAOImpl" %>
<%@ page import="DataAccess.NewsletterDTO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Newsletter Details</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to a CSS file for styling -->
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }
        .container {
            width: 60%;
            margin: auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            padding: 20px;
            background-color: #333;
            color: #fff;
            margin: 0;
            border-radius: 8px 8px 0 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #333;
            color: #fff;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #333;
            font-size: 16px;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Newsletter Details</h1>
        <%
            String idParam = request.getParameter("id");
            if (idParam != null) {
                int id = Integer.parseInt(idParam);
                NewsletterDAO dao = new NewsletterDAOImpl();
                NewsletterDTO newsletter = null;
                try {
                    newsletter = dao.getNewsletterById(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        %>
        <table>
            <tr>
                <th>ID</th>
                <td><%= newsletter.getNewsletterId() %></td>
            </tr>
            <tr>
                <th>Name</th>
                <td><%= newsletter.getNewsletterName() %></td>
            </tr>
            <tr>
                <th>Article</th>
                <td><%= newsletter.getNewsletterArticle() %></td>
            </tr>
            <tr>
                <th>Item ID</th>
                <td><%= newsletter.getItemId() %></td>
            </tr>
            <tr>
                <th>Sale Price</th>
                <td><%= newsletter.getSale() %></td>
            </tr>
            <tr>
                <th>Upload Date</th>
                <td><%= newsletter.getUploadDate() %></td>
            </tr>
        </table>
        <%
            } else {
                out.println("<p>Invalid newsletter ID.</p>");
            }
        %>
    </div>
</body>
</html>