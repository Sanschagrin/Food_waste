<%@page import="java.util.List"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Other/html.html to edit this template
-->
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Retailer Home Page</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            position: relative; /* Added for button positioning */
        }

        h1 {
            color: #333;
            font-size: 24px;
            margin-bottom: 20px;
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: #ffffff;
            font-weight: bold;
        }

        td {
            background-color: #ffffff;
        }

        tr:nth-child(even) td {
            background-color: #f9f9f9;
        }

        tr:hover td {
            background-color: #f1f1f1;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .no-data {
            text-align: center;
            color: #999;
            font-style: italic;
        }

        .newsletter-button {
            position: absolute;
            top: 20px;
            right: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }

        .newsletter-button:hover {
            background-color: #0056b3;
        }

        @media (max-width: 768px) {
            table {
                font-size: 14px;
            }

            th, td {
                padding: 8px;
            }

            h1 {
                font-size: 20px;
            }

            .newsletter-button {
                font-size: 14px;
                padding: 8px 15px;
                top: 10px;
                right: 10px;
            }
        }
    </style>
    <%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="DataAccess.*" %>


<body>
    <div class="container">
        <h1>Retailer List</h1>
                <a href="listNewsletters.jsp" class="newsletter-button">View Newsletters</a>
        <ul>
            <% 
                List<NewsletterDTO> newsletters = (List<NewsletterDTO>) request.getAttribute("newsletters");
                if (newsletters != null) {
                    for (NewsletterDTO newsletter : newsletters) {
                        out.println("<li>" + newsletter.getNewsletterName() + "</li>");
                    }
                }
            %>
        </ul>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    RetailerDAO retailerDAO = new RetailerDAOImpl();
                    List<RetailerDTO> retailers = retailerDAO.getAllRetailers();

                    if (retailers != null && !retailers.isEmpty()) {
                        for (RetailerDTO retailer : retailers) {
                %>
                    <tr>
                        <td><%= retailer.getRetailerId() %></td>
                        <td><%= retailer.getRetailerName() %></td>
                        <td><%= retailer.getRetailerDescription() %></td>
                        <td><a href="ViewRetailerServlet?retailer_id=<%= retailer.getRetailerId() %>">View Retailer Page</a></td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="4" class="no-data">No retailers found.</td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>

