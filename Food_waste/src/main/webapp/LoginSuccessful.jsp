<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Login Successful - Taste-Not-Waste</title>
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
                .button {
                    display: inline-block;
                    margin: 10px 0;
                    padding: 10px 20px;
                    color: #fff;
                    background-color: #4CAF50;
                    border: none;
                    border-radius: 5px;
                    text-decoration: none;
                    font-size: 16px;
                    font-weight: bold;
                    cursor: pointer;
                }
                .button:hover {
                    background-color: #45a049;
                }
                @media (max-width: 600px) {
                    .container {
                        padding: 20px 10px;
                    }
                }
            </style>
        </head>
        <body onload="toggleButton()">
            <div class="container">
                <h1>Welcome to Taste-Not-Waste</h1>
                <p>You have successfully logged in.</p>
                <input type="hidden" id="user_type" value="<%= session.getAttribute("userType") %>">
                <a href="consumerHome.jsp" class="button" id="consumer_button">Profile</a>
                <a href="RetailerHome.jsp" class="button" id="retailer_button">Profile</a>
                <a href="OrgHome.jsp" class="button" id="charity_button">Profile</a>
                <a href="Home.jsp" class="button">Home</a>
            </div>
            
        </body>
        <script>
        function toggleButton() {
            var userType = document.getElementById("user_type").value;
            var consumerButton = document.getElementById("consumer_button");
            var retailerButton = document.getElementById("retailer_button");
            var charityButton = document.getElementById("charity_button");
            
            consumerButton.style.display = (userType === "consumer") ? "inline-block" : "none";
            retailerButton.style.display = (userType === "retailer") ? "inline-block" : "none";
            charityButton.style.display = (userType === "charitable_organization") ? "inline-block" : "none";
        }
        </script>
    </html>
</f:view>
