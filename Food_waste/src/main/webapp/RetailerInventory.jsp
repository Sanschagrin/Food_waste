<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="DataAccess.*" %>

<%
    if (session == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Object user = session.getAttribute("user");

    RetailerDTO retailer = null;
    ConsumerDTO consumer = null;
    CharitableDTO charitable = null;

    if (user instanceof RetailerDTO) {
        retailer = (RetailerDTO) user;
    } else if (user instanceof ConsumerDTO) {
        consumer = (ConsumerDTO) user;
    } else if (user instanceof CharitableDTO){
        charitable = (CharitableDTO) user;
    }

    if (retailer == null && consumer == null && charitable == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Retailer Inventory - Taste-Not-Waste</title>
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
            max-width: 1200px;
            display: flex;
            justify-content: space-between;
        }
        .retailer-info, .item-list {
            width: 48%;
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
                flex-direction: column;
                align-items: center;
            }
            .retailer-info, .item-list {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="retailer-info">
            <p><a href="Home.jsp">Go Home</a></p>
            <p><a href="AddItem.jsp">List an Item</a></p>
        </div>
        <div class="item-list">
            <h2>Your Items</h2>
            <table>
                <tr>
                    <th>Item ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Expiry Date</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
                <%
                    List<InventoryDTO> items = (List<InventoryDTO>) request.getAttribute("items");
                    if (items != null && !items.isEmpty()) {
                        for (InventoryDTO item : items) {
                %>
                            <tr>
                                <td><%= item.getItemId() %></td>
                                <td><%= item.getItemName() %></td>
                                <td><%= item.getItemDescription() %></td>
                                <td><%= item.getPrice() %></td>
                                <td><%= item.getExpiryDate() %></td>
                                <td><%= item.getQuantity() %></td>
                                <td>
                                    <form action="ClaimOrPurchaseServlet" method="post">
                                        <input type="hidden" name="item_id" value="<%= item.getItemId() %>">
                                        <input type="number" name="quantity" min="1" max="<%= item.getQuantity() %>" required>
                                        <% if (consumer != null) { %>
                                            <button type="submit" name="action" value="purchase">Purchase</button>
                                        <% } %>
                                        <% if (charitable != null) { %>
                                            <button type="submit" name="action" value="claim">Claim</button>
                                        <% } %>
                                    </form>
                                </td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="7">No items found in the inventory.</td>
                        </tr>
                <%
                    }
                %>
            </table>
        </div>
    </div>
</body>
</html>
