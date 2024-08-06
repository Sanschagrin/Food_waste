<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
            <h1>Welcome, <h:outputText value="${consumerBean.consumerName}"/></h1>
            <table>
                <tr>
                    <th>Consumer ID</th>
                    <td><h:outputText value="${consumerBean.consumerId}"/></td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td><h:outputText value="${consumerBean.consumerName}"/></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td><h:outputText value="${consumerBean.consumerEmail}"/></td>
                </tr>
                <tr>
                    <th>Password</th>
                    <td>********</td> <!-- Masked password for security -->
                </tr>
                <tr>
                    <th>Newsletter Subscriber</th>
                    <td><h:outputText value="${consumerBean.newsletterSubscriber ? 'Yes' : 'No'}"/></td>
                </tr>
                <tr>
                    <th>Claim History</th>
                    <td><h:outputText value="${consumerBean.claimHistory}"/></td>
                </tr>
            </table>
            <p><a href="editConsumerProfile.jsp">Edit Profile</a></p>
        </div>
    </f:view>
</body>
</html>
