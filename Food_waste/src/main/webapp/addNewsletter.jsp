<%-- 
    Document   : addNewsletter
    Created on : Aug. 7, 2024, 11:02:21 a.m.
    Author     : ggreg
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Newsletter</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to a CSS file for styling -->
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
        .container {
            width: 60%;
            margin: auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin: 10px 0 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="date"], textarea {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        textarea {
            resize: vertical;
            height: 100px;
        }
        input[type="submit"] {
            background-color: #333;
            color: #fff;
            border: none;
            padding: 15px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #555;
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
    <h1>Add Newsletter</h1>
    <div class="container">
        <form action="addNewsletter" method="post">
            <label for="newsletter_name">Name:</label>
            <input type="text" id="newsletter_name" name="newsletter_name" required>

            <label for="newsletter_article">Article:</label>
            <textarea id="newsletter_article" name="newsletter_article" required></textarea>

            <label for="item_id">Item ID:</label>
            <input type="text" id="item_id" name="item_id" required>

            <label for="sale">Sale Price:</label>
            <input type="text" id="sale" name="sale" required>

            <label for="uploadDate">Upload Date:</label>
            <input type="date" id="uploadDate" name="uploadDate" required>

            <input type="submit" value="Add Newsletter">
        </form>
        <a class="back-link" href="listNewsletters">Back to Newsletter List</a>
    </div>
</body>
</html>