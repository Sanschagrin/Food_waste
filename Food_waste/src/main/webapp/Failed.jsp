<%@page import="java.util.Arrays"%>
<%@ page isErrorPage="true" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - Taste-Not-Waste</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da;
            color: #721c24;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            background-color: #fff;
            border: 1px solid #f5c6cb;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 800px;
        }
        h2 {
            color: #721c24;
        }
        p {
            margin: 10px 0;
        }
        pre {
            background-color: #f5c6cb;
            padding: 10px;
            border: 1px solid #f5c6cb;
            border-radius: 4px;
            overflow: auto;
        }
        a {
            color: #004085;
            text-decoration: none;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Oops! Something went wrong.</h2>
        <p>We encountered an unexpected error while processing your request. Please try again later or contact support if the problem persists.</p>
        <p><strong>Error Details:</strong></p>
        <p><%= (exception != null) ? exception.getMessage() : "No error message available." %></p>
        <p><strong>Stack Trace:</strong></p>
        <pre><%= (exception != null) ? Arrays.toString(exception.getStackTrace()) : "No stack trace available." %></pre>
        <p><a href="Home.jsp">Go back to home page</a></p>
    </div>
</body>
</html>
