<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <style type="text/css">
        form {
            border: 3px solid #f1f1f1;
            padding: 50px;

            position: fixed;
            top: 50%;
            left: 50%;
            -webkit-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
        }

        input[type=text] {
            width: 30%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        button {
            border: none;
            background: #f400f4;
        }

        select {

        }
    </style>
</head>
<body>
<c:if test="${sessionScope.confirmType == null}">
    <form action="confirmation" method="post">
        <select name="issue">

            <option><fmt:message key="confirmation.option.toTheHall"/></option>
            <option><fmt:message key="confirmation.option.subscription"/></option>

        </select>
        <button type="submit">OK</button>
    </form>
</c:if>
<c:if test="${sessionScope.confirmType == 'Subscription'}">
    <form action="confirmation" method="post">
        <p align="center"><label><b><fmt:message key="librarian.th.returnDate"/></b></label></p>

        <p align="center"><input type="text" name="year" placeholder="yyyy">

            <input type="text" name="month" placeholder="mm">
            <input type="text" name="day" placeholder="dd"></p>

        <p align="center">
            <button type="submit" name="confirm" value="confirm">
                <fmt:message key="librarian.button.confirm"/>
            </button>
        </p>

    </form>
</c:if>
<c:if test="${sessionScope.confirmType == 'hall'}">
    <form action="confirmation" method="post">
        <p align="center">
            <button type="submit" name="confirm" value="confirm">
                <fmt:message key="librarian.button.confirm"/>
            </button>
    </form>
</c:if>

<c:if test="${sessionScope.yearException != null}">
    <script>
        alert("Wrong year. Input value from 2019 to 2050");
    </script>
</c:if>
<c:if test="${sessionScope.monthException != null}">
    <script>
        alert("Wrong month. Input value from 1 to 12");
    </script>
</c:if>
<c:if test="${sessionScope.dayException != null}">
    <script>
        alert("Wrong day. Input value from 1 to 31");
    </script>
</c:if>
</body>
</html>
