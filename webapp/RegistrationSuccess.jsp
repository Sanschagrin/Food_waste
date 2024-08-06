<%-- 
    Document   : RegistrationSuccess
    Created on : Aug. 4, 2024, 12:40:07 p.m.
    Author     : ggreg
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Successful - Taste-Not-Waste</title>
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
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 40px 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 90%;
            max-width: 500px;
        }
        h1 {
            color: #4CAF50;
            margin-bottom: 20px;
        }
        p {
            font-size: 16px;
            line-height: 1.5;
            margin-bottom: 20px;
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
    <div class="container">
        <h1>Registration Successful!</h1>
        <p>Welcome to Taste-Not-Waste!</p>
        <p>Thank you for registering. <a href="Login.html">Click here to login</a>.</p>
    </div>
</body>
</html>
</f:view>
