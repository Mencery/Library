<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>



<html>
<head>
    <style type="text/css">

        form  {

            border: 3px solid #f1f1f1;
            padding: 50px;


        }
        .NewUserForm{
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
<form action="controller" style="border: 0px">
    <input type="hidden" name="command" value="newUser"/>
    <p align="right">

        <select id="language" name="language" onchange="submit()">
            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>

        </select>
    </p>
</form>
<div>

    <form action="new_user"  class="NewUserForm" method="post">
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