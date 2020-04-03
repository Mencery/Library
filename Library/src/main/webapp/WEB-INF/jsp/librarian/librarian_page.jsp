
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="box.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <style type="text/css">
        table {

            width:50%;
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

        button:hover {
            background: #35aaf4;
            opacity: 0.8;
        }

        button {

            background: #afd792;
            border: none;
            width: 100%;
            height: 100%;
            font-family: 'Times New Roman';
            font-size: 16px;

        }
        .ordersButton {
            width: auto;
            height: auto;
            padding: 10px 18px;
            color: white;
            border: none;


            background-color: #f48a72;
        }
    </style>
</head>
<body>

<%@ include file="/WEB-INF/jspf/private_office.jspf" %>
<form action="confirmedOrders" method="post">

    <button type="submit" class="ordersButton ">
        <fmt:message key="librarian.button.orders"/>
        </button>

</form>
<form action="orderPenalty" method="post">

    <button type="submit" class="ordersButton ">
        Penalty User
    </button>

</form>
<c:if test="${notConfirmedOrders != null}">
<p align="center">
<table>


    <tr>
        <td>



                <thead>
                <tr>
                    <th><fmt:message key="login.label.login"/></th>
                    <th><fmt:message key="librarian.th.bookName"/></th>
                    <th><fmt:message key="librarian.th.bookAuthor"/></th>

                </tr>
                </thead>

            <tbody>
                <c:forEach var="order" items="${notConfirmedOrders}">

                    <tr>
                        <td>${order.readerLogin}</td>
                        <td>${order.bookName}</td>
                        <td>${order.bookAuthor}</td>


                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="confirmation"/>
                            <input type="hidden" name="reader_id" value="${order.readerId}">

                            <input type="hidden" name="book_id" value="${order.bookId}">

                            <td>
                                <button type="submit">
                                    <fmt:message key="librarian.button.confirm"/>
                                </button>
                            </td>

                        </form>


                    </tr>

                </c:forEach>
            </tbody>


        </td>
    </tr>


</table>
    </p>
</c:if>

<c:if test="${confirmedOrders != null}">
<p align="center">
<table >


    <tr>
        <td>



            <thead>
            <tr>
                <th><fmt:message key="login.label.login"/></th>
                <th><fmt:message key="librarian.th.bookName"/></th>
                <th><fmt:message key="librarian.th.bookAuthor"/></th>
                <th><fmt:message key="librarian.th.returnDate"/><br>
                   <fmt:message key="librarian.th.dateFormat"/>
                </th>
                <th><fmt:message key="librarian.th.daysLeft"/></th>

            </tr>
            </thead>

            <tbody>
            <c:forEach var="order" items="${confirmedOrders}">

                <tr>
                    <td>${order.readerLogin}</td>
                    <td>${order.bookName}</td>
                    <td>${order.bookAuthor}</td>
                    <td>${order.returnDate}</td>
                    <td>${order.daysLeft}</td>
                    <form action="returned" method="post">

                        <input type="hidden" name="reader_id" value="${order.readerId}">
                        <input type="hidden" name="book_id" value="${order.bookId}">
                        <td>
                            <button type="submit">
                                <fmt:message key="librarian.button.returned"/>
                            </button>
                        </td>

                    </form>




                </tr>

            </c:forEach>
            </tbody>


        </td>
    </tr>
</table>

</p>
</c:if>
<c:if test="${penaltyOrders != null}">
    <p align="center">
    <table >


        <tr>
            <td>



                <thead>
                <tr>
                    <th><fmt:message key="login.label.login"/></th>
                    <th><fmt:message key="librarian.th.bookName"/></th>
                    <th><fmt:message key="librarian.th.bookAuthor"/></th>
                    <th><fmt:message key="librarian.th.returnDate"/><br>
                        <fmt:message key="librarian.th.dateFormat"/>
                    </th>
                    <th><fmt:message key="librarian.th.daysLeft"/></th>

                </tr>
                </thead>

                <tbody>
                <c:forEach var="order" items="${penaltyOrders}">

                    <tr>
                        <td>${order.readerLogin}</td>
                        <td>${order.bookName}</td>
                        <td>${order.bookAuthor}</td>
                        <td>${order.returnDate}</td>
                        <td>${order.daysLeft}</td>
                        <form action="returned" method="post">

                            <input type="hidden" name="reader_id" value="${order.readerId}">
                            <input type="hidden" name="book_id" value="${order.bookId}">
                            <td>
                                <button type="submit">
                                    <fmt:message key="librarian.button.returned"/>
                                </button>
                            </td>

                        </form>




                    </tr>

                </c:forEach>
                </tbody>


            </td>
        </tr>
    </table>

    </p>
</c:if>
<c:if test="${sessionScope.cannotReturnBook != null}">
    <script>
        alert("Cannot return this book. Call to support");
    </script>

</c:if>

<c:if test="${sessionScope.SQLException != null}">
    <script>
        alert("System error. Unfortunately server doesn't work correct. Enter later...");
    </script>
</c:if>

</body>
</html>
