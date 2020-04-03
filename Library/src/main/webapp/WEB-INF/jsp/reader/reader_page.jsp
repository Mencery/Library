<%--
  Created by IntelliJ IDEA.
  User: plekh
  Date: 7/4/2019
  Time: 8:27 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>

<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="box.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <style type="text/css">


        table {
            width: 100%;
            border-collapse: collapse;
        }

        td, th {
            padding: 3px;
            border: 1px solid #000;
        }

        th {
            background: #afd792;
        /
        }

        tbody tr:hover {
            background: #f3bd48;
            color: #fff;
        }

        th:hover {
            background: #35aaf4;

        }

        .catalogButton {
            width: auto;
            padding: 10px 18px;
            color: white;
            border: none;


            background-color: #f48a72;
        }

        .sortingButton {

            background: #afd792;
            border: none;
            width: 100%;
            height: 100%;
            font-family: 'Times New Roman';
            font-size: 16px;

        }

        .orderButton {

            background: #ffffff;
            border: 3px;
            width: 100%;
            height: 100%;
            font-family: 'Times New Roman';
            font-size: 16px;

        }

        button:hover {
            background: #35aaf4;
            opacity: 0.8;
        }


        .input {
            border: 0px;
            background: black;
            border-bottom: 2px solid rgba(255, 255, 255, 0.97);
            background: transparent;

            padding: 5px 0;
            outline: none;
            color: #fff;
            font-weight: bold;
            transition: all 0.3s ease;
        }

        input::placeholder {
            color: #fff;
        }





    </style>

</head>

<body>
<%@ include file="/WEB-INF/jspf/private_office.jspf" %>



<form action="search" method="post">

    <input name="s" placeholder="Find here..." class="input" type="search">

    <button type="submit" class="fa fa-search" style="background:0%; border: 0px">

        <!--fmt:message key="reader.button.search"/--></button>

</form>
<form action="catalog" method="post">

    <button type="submit" class="catalogButton">
        <fmt:message key="reader.button.catalog"/>
    </button>

</form>

<c:if test="${searchedBooks != null}">

    <table>


        <thead>


        <tr>
            <th><fmt:message key="admin.td.Name"/></th>
            <th><fmt:message key="admin.td.Author"/></th>
            <th><fmt:message key="admin.td.PublishingOffice"/></th>
            <th><fmt:message key="admin.td.PublishingYear"/></th>
            <th><fmt:message key="admin.td.Amount"/></th>
            <th><fmt:message key="reader.th.Order"/></th>

        </tr>


        </thead>

        <tbody>
        <c:forEach var="book" items="${searchedBooks}">
        <tr>
            <c:if test="${book.name!= null}">
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.publishingOffice}</td>
            <td>${book.date}</td>
            <td>${book.amount}</td>
            <c:if test="${book.amount>0}">
                <form action="order" method="post">
                    <td>
                        <button type="submit" class="orderButton" name="bookId" value="${book.id}">
                            <fmt:message key="reader.button.addOrder"/>
                        </button>
                    </td>
                </form>
            </c:if>
        </tr>
        </c:if>
        </c:forEach>

    </table>

</c:if>

<c:if test="${catalog != null}">
    <table>


        <thead>

        <form action="sorting" method="post">
            <tr>
                <th>
                    <button type="submit" class="sortingButton" name="sort" value="name">
                        <b> <fmt:message key="admin.td.Name"/></b>
                    </button>
                </th>
                <th>
                    <button type="submit" class="sortingButton" name="sort" value="author">
                        <b><fmt:message key="admin.td.Author"/></b>
                    </button>
                </th>
                <th>
                    <button type="submit" class="sortingButton" name="sort" value="office">
                        <b><fmt:message key="admin.td.PublishingOffice"/></b>
                    </button>

                </th>
                <th>
                    <button type="submit" class="sortingButton" name="sort" value="year">
                        <b><fmt:message key="admin.td.PublishingYear"/></b>
                    </button>

                </th>
                <th><fmt:message key="admin.td.Amount"/></th>
                <th><fmt:message key="reader.th.Order"/></th>
            </tr>
        </form>

        </thead>

        <tbody>
        <c:forEach var="book" items="${catalog}">
        <tr>
            <c:if test="${book.name!= null}">
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.publishingOffice}</td>
            <td>${book.date}</td>
            <td>${book.amount}</td>
            <c:if test="${book.amount>0 and sessionScope.currentUser.hasForfeits == false}">
                <form action="order" method="post">
                    <td>
                        <button type="submit" class="orderButton" name="bookId" value="${book.id}">
                            <fmt:message key="reader.button.addOrder"/>
                        </button>
                    </td>
                </form>
            </c:if>
        </tr>
        </c:if>
        </c:forEach>

    </table>


</c:if>
<c:if test="${myOrders != null}">
    <p align="center">
    <table style="width: 50%">


        <thead>


        <tr>
            <th>
                <fmt:message key="admin.td.Name"/>
            </th>
            <th>
                <fmt:message key="admin.td.Author"/>
            </th>
            <th>
                <fmt:message key="librarian.th.returnDate"/><br>
                <fmt:message key="librarian.th.dateFormat"/>
            </th>
            <th>
                <fmt:message key="librarian.th.daysLeft"/>
            </th>
            <th>

                <fmt:message key="reader.th.confirmedByLibrarian"/>


            </th>
            <th>

                <fmt:message key="reader.th.forfeits"/>


            </th>

        </tr>
        </form>

        </thead>

        <tbody>
        <c:forEach var="order" items="${myOrders}">
        <tr>
            <c:if test="${order.bookName!= null}">
            <td>${order.bookName}</td>
            <td>${order.bookAuthor}</td>
            <td>${order.returnDate}</td>
            <td>${order.daysLeft}</td>
            <c:if test="${order.confirm == 0}">
                <td>
                    <fmt:message key="reader.td.no"/>
                </td>
            </c:if>
            <c:if test="${order.confirm == 1}">
                <td>
                    <fmt:message key="reader.td.yes"/>
                </td>

            </c:if>
            <c:if test="${order.forfeits != null}">
                <td>${order.forfeits}</td>

            </c:if>
            <c:if test="${order.forfeits == null}">
                <td><fmt:message key="reader.td.noForfeits"/></td>

            </c:if>
        </tr>
        </c:if>
        </c:forEach>

    </table>
    </p>

</c:if>
<c:if test="${sessionScope.doubleOrderException != null}">
    <script>
        alert("You cannot order same book twice.");
    </script>
</c:if>
<c:if test="${sessionScope.cannotFindOrders != null}">
    <script>
        alert("Cannot find orders. Call to support");
    </script>
</c:if>
<c:if test="${sessionScope.cannotFindBooks != null}">
    <script>
        alert("Cannot find books. Call to support");
    </script>
</c:if>
</body>
</html>
