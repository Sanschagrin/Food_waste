<%-- 
    Document   : editConsumerProfile
    Created on : Aug. 6, 2024, 12:22:06 a.m.
    Author     : ggreg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Edit Profile - Taste-Not-Waste</title>
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
        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .form-group input[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        .form-group input[type="submit"]:hover {
            background-color: #45a049;
        }
        .form-group .error {
            color: red;
            font-size: 0.9em;
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
            <h1>Edit Profile</h1>
            <h:form>
                <div class="form-group">
                    <h:outputLabel for="consumerName" value="Name:"/>
                    <h:inputText id="consumerName" value="${consumerBean.consumerName}" required="true"/>
                    <h:message for="consumerName" class="error"/>
                </div>

                <div class="form-group">
                    <h:outputLabel for="consumerEmail" value="Email:"/>
                    <h:inputText id="consumerEmail" value="${consumerBean.consumerEmail}" required="true"/>
                    <h:message for="consumerEmail" class="error"/>
                </div>

                <div class="form-group">
                    <h:outputLabel for="consumerPassword" value="Password:"/>
                    <h:inputSecret id="consumerPassword" value="${consumerBean.consumerPassword}" required="true"/>
                    <h:message for="consumerPassword" class="error"/>
                </div>

                <div class="form-group">
                    <h:outputLabel for="newsletterSubscriber" value="Subscribe to Newsletter:"/>
                    <h:selectBooleanCheckbox id="newsletterSubscriber" value="${consumerBean.newsletterSubscriber}"/>
                </div>

                <div class="form-group">
                    <h:commandButton value="Save Changes" action="${consumerBean.updateProfile}"/>
                </div>
            </h:form>
        </div>
    </f:view>
</body>
</html>