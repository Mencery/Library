<%--
  Created by IntelliJ IDEA.
  User: plekh
  Date: 7/3/2019
  Time: 7:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<head>
    <style type="text/css">

        form {
            border: 3px solid #f1f1f1;
            padding: 50px;

            position: fixed; top: 50%; left: 50%;
            -webkit-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
        }
        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        .newUser {
            width: auto;
            padding: 10px 18px;
            background-color: #35aaf4;
        }
        button:hover {
            opacity: 0.8;
        }


    </style>
</head>
<body>

<div>
    <form action="new_user" method="post">
        <label for="login"><b><fmt:message key="login.label.login"/>:</b></label>
        <input  type="text" name="login" id="login" placeholder="Input Login">
        <label for="password"><b><fmt:message key="login.label.password"/>:</b></label>
        <input name="password"  type="password" id="password" placeholder="Input Password">
        <label for="phoneNumber"><b><fmt:message key="newUser.label.phoneNumber"/>:</b></label>
        <input type="text" name="phoneNumber" id="phoneNumber" placeholder="Input phone number">
        <input type="hidden" name="status" value="reader">
        <p align="center">
            <button type="submit" class="newUser" name="newUser">
                <fmt:message key="newUser.button.singUp"/>
            </button>
        </p>

    </form>
</div>
</body>
</html>