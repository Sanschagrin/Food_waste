<%-- 
    Document   : AddItem
    Created on : Aug. 6, 2024, 5:24:41 p.m.
    Author     : mylen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="DataAccess.InventoryDTO" %>
<%@ page import="DataAccess.RetailerDTO" %>
<%
    InventoryDTO inventory = null;
    RetailerDTO retailer = null;
    
    
    if (session != null) {
        inventory = (InventoryDTO) session.getAttribute("item");
        retailer = (RetailerDTO) session.getAttribute("user");
    }
    if (inventory == null && retailer == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Item - Taste-Not-Waste</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 40px 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 600px;
        }
        h1 {
            color: #4CAF50;
            margin-bottom: 20px;
            text-align: center;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 5px;
            font-weight: bold;
        }
        input, textarea {
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            width: 100%;
        }
        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Add New Item</h1>
        <form action="AddItemServlet" method="post">
            
            <label for="retailer_id">Retailer ID:</label>
            <input type="number" id="retailer_id" name="retailer_id" required>
            
            <label for="item_name">Item Name:</label>
            <input type="text" id="item_name" name="item_name" required>
            
            <label for="item_description">Item Description:</label>
            <textarea id="item_description" name="item_description" required></textarea>
            
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" required>
            
            <label for="expiry_date">Expiry Date:</label>
            <input type="date" id="expiry_date" name="expiry_date" required>
            
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" required>
            
            <input type="submit" value ="Add Item">
        </form>
    </div>
</body>
</html>

