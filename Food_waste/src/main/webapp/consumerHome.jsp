<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%-- 
    Document   : RetailerHome
    Created on : Aug. 6, 2024, 3:29:09 p.m.
    Author     : mylen
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="DataAccess.*" %>

<%
    ConsumerDTO consumer = null;
    if (session != null) {
        consumer = (ConsumerDTO) session.getAttribute("user");
    }
    if (consumer == null) {
        response.sendRedirect("login.jsp");
    }
%>

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
            <h1>Welcome, <%= consumer.getConsumerName() %>!</h1>
            <table>
                <tr>
                    <th>Consumer ID</th>
                    <td><%= consumer.getConsumerId() %></td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td><%= consumer.getConsumerName() %></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td><%= consumer.getConsumerEmail() %></td>
                </tr>
                <tr>
                    <th>Password</th>
                    <td>********</td> <!-- Masked password for security -->
                </tr>
                <tr>
                    <th>Newsletter Subscriber</th>
                    <td><%= consumer.getSubscriber() %></td>
                </tr>
            </table>
            <p><a href="Home.jsp">Go Home</a></p>
            <form action="DisplayPurchasedItemsServlet" method="get">
            <input type="hidden" name="consumer_id" value="<%= consumer.getConsumerId() %>">
            <button type="submit">View Items</button>
        </form>

        <!-- Display Claimed Items -->
        <div class="item-list">
            
    <h2>Purchased Items</h2>
    <table border="1">
        <tr>
            <th>Item ID</th>
            <th>Item Name</th>
            <th>Quantity</th>
        </tr>
        <%
            List<PurchaseItemDTO> purchasedItems = (List<PurchaseItemDTO>) request.getAttribute("purchasedItems");
            if (purchasedItems != null) {
                for (PurchaseItemDTO purchaseItem : purchasedItems) {
                    InventoryDTO item = (InventoryDTO) request.getAttribute("item_" + purchaseItem.getItemId());
                    if (item != null) {
        %>
        <tr>
            <td><%= purchaseItem.getItemId() %></td>
            <td><%= item.getItemName() %></td>
            <td><%= purchaseItem.getQuantity() %></td>
        </tr>
        <%
                    }
                }
            } else {
        %>
        <tr>
            <td colspan="3">No items claimed.</td>
        </tr>
        <%
            }
        %>
            </div>
        </div>
    </body>
</html>
