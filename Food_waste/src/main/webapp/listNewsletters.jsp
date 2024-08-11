<%-- 
    Document   : listNewsletters
    Created on : Aug. 7, 2024, 10:54:07 a.m.
    Author     : ggreg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="DataAccess.NewsletterDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Newsletter List</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }
        h1 {
            text-align: center;
            padding: 20px;
            background-color: #333;
            color: #fff;
            margin: 0;
        }
        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
        .details-link {
            color: #007BFF;
            text-decoration: none;
        }
        .details-link:hover {
            text-decoration: underline;
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
    <h1>Newsletter List</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Article</th>
                <th>Item ID</th>
                <th>Sale Price</th>
                <th>Upload Date</th>
                <th>Details</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<NewsletterDTO> newsletters = (List<NewsletterDTO>) request.getAttribute("newsletters");
                if (newsletters != null && !newsletters.isEmpty()) {
                    for (NewsletterDTO newsletter : newsletters) {
            %>
                    <tr>
                        <td><%= newsletter.getNewsletterId() %></td>
                        <td><%= newsletter.getNewsletterName() %></td>
                        <td><%= newsletter.getNewsletterArticle() %></td>
                        <td><%= newsletter.getItemId() %></td>
                        <td><%= newsletter.getSale() %></td>
                        <td><%= newsletter.getUploadDate() %></td>
                        <td><a href="newsletterDetails.jsp?id=<%= newsletter.getNewsletterId() %>" class="details-link">View Details</a></td>
                    </tr>
            <% 
                    }
                } else {
            %>
                    <tr>
                        <td colspan="7">No newsletters found.</td>
                    </tr>
            <% 
                }
            %>
        </tbody>
    </table>
    <a class="back-link" href="Home.jsp">Back to Home</a>
</body>
</html>