<%--
  Created by IntelliJ IDEA.
  User: plekh
  Date: 6/28/2019
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<%@ page import="util.Path" %>


<html>
<head>

    <style type="text/css">

        form {
            border: 3px solid #f1f1f1;
        }

        /* Full-width inputs */
        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        /* Set a style for all buttons */
        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 25%;
        }

        button:disabled {
            background-color: #cccccc;


        }

        /* Add a hover effect for buttons */
        button:hover {
            opacity: 0.8;

        }

        /* Extra style for the cancel button (red) */
        .newuser {
            width: auto;
            padding: 10px 18px;
            background-color: #35aaf4;
        }

        .forgot {
            float: right;
            width: auto;
            padding: 10px 18px;


        }


        .imgcontainer {
            text-align: center;
            margin: 24px 0 12px 0;
        }


        img.avatar {
            width: 110px;
            height: 130px;
            border-radius: 50%;
        }

        /* Add padding to containers */
        .container {
            padding: 16px;
        }


        span.psw {
            float: right;
            padding-top: 16px;
        }

        /* Change styles for span and cancel button on extra small screens */
        @media screen and (max-width: 300px) {
            span.psw {
                display: block;
                float: none;
            }

            .cancelbtn {
                width: 100%;
            }
        }

    </style>
    <script type="text/javascript">
        var onloadCallback = function () {
            grecaptcha.render('html_element', {
                'sitekey': '6Ld5lK0UAAAAAIbQE7vQucrpPBbC8UyMxBl-HGpv'
            });
        };

        function enableBtn() {
            document.getElementById("loginButton").disabled = false;
        }
    </script>

</head>
<body>
<form action="controller" method="post">
    <input type="hidden"   name="command" value="loginTranslate"/>
    <p align="right">
        <select id="language" name="language" onchange="submit()">
            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>

        </select>
    </p>
</form>

<div>
    <form action="controller" method="post">
        <div class="imgcontainer">
            <img src="images.png" alt="Avatar" class="avatar">
        </div>
        <input type="hidden" name="command" value="login"/>
        <div class="container">
            <label for="login"><b><fmt:message key="login.label.login"/>:</b></label>
            <input type="text" placeholder="Enter Username" name="login" id="login" required>

            <label for="psw"><b><fmt:message key="login.label.password"/>:</b></label>
            <input type="password" placeholder="Enter Password" name="password" id="psw" required>

            <p align="center">
                <button type="submit" id="loginButton"
                         name="logIn"><fmt:message key="login.button.submit"/>
                </button>
            </p>

        </div>
        <form action="?" method="POST">
            <div id="html_element" class="g-recaptcha"
                 data-sitekey="6LeYlq0UAAAAAConFtp8J8TYmUcyd4WVAI8lj-j1"
                 data-callback="enableBtn"></div>
            <br>

        </form>
        <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
                async defer>


        </script>

    </form>
    <div class="container" style="background-color:#f1f1f1">
        <form action="controller" method="get">
            <button type="submit" class="newuser">
                <fmt:message key="login.button.newuser"/>
            </button>
            <input type="hidden" name="command" value="newUser"/>
            <a class="forgot" href="${Path.PAGE_FORGET_PASSWORD_PAGE}">
                <fmt:message key="login.a.forget"/>
            </a>
        </form>

    </div>


</div>
<c:if test="${sessionScope.NotExistsUser != null}">
    <script>
        alert("This user doesn't exist");
    </script>
    ${sessionScope.NotExistsUser = null}
</c:if>
<c:if test="${sessionScope.WrongPassword != null}">
    <script>
        alert("Wrong password");
    </script>
    ${sessionScope.WrongPassword = null}
</c:if>
</body>
</html>
