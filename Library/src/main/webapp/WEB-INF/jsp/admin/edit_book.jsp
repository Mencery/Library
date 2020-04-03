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
            width: 100%;
            padding: 6px 10px;
            margin: 4px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        .addBook {
            width: auto;
            padding: 10px 18px;
            background-color: #f4ea16;
        }

        button:hover {
            opacity: 0.8;
        }


    </style>
</head>
<body>

<div>
    <form action="new_book" method="post">
        <label for="name"><b><fmt:message key="admin.td.Name"/></b></label>
        <input type="text" name="name" id="name" value="${sessionScope.editNameBook}"></p>
        <input type="hidden" name="findname" value="${sessionScope.editNameBook}">
        <label for="author"><b><fmt:message key="admin.td.Author"/></b></label>
        <input type="text" name="author" id="author" value="${sessionScope.editAuthorBook}"></p>
        <label for="publishingOffice"><b><fmt:message key="admin.td.PublishingOffice"/></b></label>
        <input type="text" name="publishingOffice" id="publishingOffice" value="${sessionScope.editOfficeBook}"></p>
        <label for="date"><b><fmt:message key="admin.td.PublishingYear"/></b></label>
        <input type="text" name="date" id="date" value="${sessionScope.editDateBook}"></p>
        <label for="amount"><b><fmt:message key="admin.td.Amount"/></b></label>
        <input type="text" name="amount" id="amount" value="${sessionScope.editAmountBook}"></p>
        <input type="hidden" name="page" value="edit">

        <p align="center">
            <button type="submit" class="addBook"><fmt:message key="edit.Apply"/></button>
        </p>

    </form>
</div>

<c:if test="${sessionScope.nameException != null}">
    <script>
        alert("Missed name. Cannot add book");
    </script>
</c:if>
<c:if test="${sessionScope.authorException != null}">
    <script>
        alert("Missed author. Cannot add book");
    </script>
</c:if>
<c:if test="${sessionScope.officeException != null}">
    <script>
        alert("Missed publishing office. Cannot add book");
    </script>
</c:if>
<c:if test="${sessionScope.dateException != null}">
    <script>
        alert("Wrong year. Cannot add book");
    </script>
</c:if>
<c:if test="${sessionScope.amountException != null}">
    <script>
        alert("Wrong amount. Cannot add book");
    </script>
</c:if>

</body>
</html>
