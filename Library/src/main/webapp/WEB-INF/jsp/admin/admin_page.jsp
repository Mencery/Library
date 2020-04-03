
<%@ page import="util.Path" %>

<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>

<c:set var="title" value="ADMIN" scope="page"/>

<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="box.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <style type="text/css">

        .adminForm {

            border: 3px solid #f1f1f1;
            padding: 50px;

            position: fixed;
            top: 60%;
            left: 5%;
            -webkit-transform: translate(-5%, -60%);

            -ms-transform: translate(-5%, -60%);
        }



        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            border-collapse: collapse;
            text-align: center;
        }

        th, td:first-child {
            background: #AFCDE7;
            color: white;
            padding: 10px 20px;
        }

        th, td {
            border-style: solid;
            border-width: 0 1px 1px 0;
            border-color: white;
        }

        td {
            background: #D8E6F3;
        }

        th:first-child, td:first-child {
            text-align: left;
        }

        #users {
            display: none;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
        }

        #books {
            display: none;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
        }

        #windowUser {
            width: 300px;
            height: 50px;
            text-align: center;
            padding: 15px;

            border-radius: 10px;

            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            margin: auto;
        }

        #windowBook {
            width: 300px;
            height: 50px;
            text-align: center;
            padding: 15px;

            border-radius: 10px;

            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            margin: auto;
        }

        #users:target {
            display: block;
        }

        #books:target {
            display: block;
        }


    </style>


</head>
<body>

<%@ include file="/WEB-INF/jspf/private_office.jspf" %>

<a href="#" id="books">

    <div id="windowBook">
        <table>

            <tr>
                <td>


                    <c:choose>
                        <c:when test="${fn:length(books)== 0}">
                            <fmt:message key="admin.NoOrders"/></c:when>
                        <c:otherwise>
                            <table id="admin_listBook">
                                <thead>
                                <tr>
                                    <td>№</td>
                                    <td><fmt:message key="admin.td.Name"/></td>
                                    <td><fmt:message key="admin.td.Author"/></td>
                                    <td><fmt:message key="admin.td.PublishingOffice"/></td>
                                    <td><fmt:message key="admin.td.PublishingYear"/></td>
                                    <td><fmt:message key="admin.td.Amount"/></td>
                                </tr>
                                </thead>


                                <c:forEach var="book" items="${books}">

                                    <tr>
                                        <td>${book.id}</td>
                                        <td>${book.name}</td>
                                        <td>${book.author}</td>
                                        <td>${book.publishingOffice}</td>
                                        <td>${book.date}</td>
                                        <td>${book.amount}</td>
                                        <form action="controller" name="editform" method="post" class="adminForm">
                                            <input type="hidden" name="command" value="editBook"/>
                                            <input type="hidden" name="name" value="${book.name}">
                                            <input type="hidden" name="author" value="${book.author}">
                                            <input type="hidden" name="office" value="${book.publishingOffice}">
                                            <input type="hidden" name="date" value="${book.date}">
                                            <input type="hidden" name="amount" value="${book.amount}">

                                            <td>
                                                <button type="submit">
                                                    <fmt:message key="admin.button.Edit"/>
                                                    </button>
                                            </td>
                                        </form>
                                        <form action="delete" method="post" class="adminForm">
                                            <input type="hidden" name="name" value="${book.name}">
                                            <input type="hidden" name="item" value="book">
                                            <td>
                                                <button type="submit">
                                                    <fmt:message key="admin.button.Delete"/>
                                                </button>
                                            </td>


                                        </form>

                                    </tr>

                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>


                </td>
            </tr>


        </table>
    </div>
</a>

<a href="#" id="users">
    <div id="windowUser">

        <table>


            <tr>
                <td>


                    <c:choose>
                        <c:when test="${fn:length(users)== 0}">No such orders</c:when>
                        <c:otherwise>
                            <table id="admin_list">
                                <thead>
                                <tr>
                                    <td>№</td>
                                    <td><fmt:message key="login.label.login"/></td>
                                    <td><fmt:message key="admin.td.Status"/></td>
                                    <td><fmt:message key="newUser.label.phoneNumber"/></td>
                                </tr>
                                </thead>


                                <c:forEach var="user" items="${users}">

                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.login}</td>
                                        <td>${user.status}</td>
                                        <td>${user.phoneNumber}</td>

                                        <c:if test="${user.blocked== 0}">
                                            <form  class="adminForm" action="block" method="post">
                                                <input type="hidden" name="login" value="${user.login}">
                                                <input type="hidden" name="action" value="block">
                                                <td>
                                                    <button type="submit"><fmt:message key="admin.button.Block"/></button>
                                                </td>

                                            </form>
                                        </c:if>
                                        <c:if test="${user.blocked== 1}">
                                            <form action="block" method="post">
                                                <input type="hidden" name="login" value="${user.login}">
                                                <input type="hidden" name="action" value="unblock">
                                                <td>
                                                    <button type="submit"><fmt:message key="admin.button.Unblock"/></button>
                                                </td>

                                            </form>
                                        </c:if>
                                        <c:if test="${user.status== 'librarian'}">
                                            <form action="delete" method="post">
                                                <input type="hidden" name="name" value="${user.login}">
                                                <input type="hidden" name="item" value="librarian">
                                                <td>
                                                    <button type="submit"><fmt:message key="admin.button.Delete"/></button>
                                                </td>

                                            </form>
                                        </c:if>
                                    </tr>

                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>


                </td>
            </tr>


        </table>

    </div>
</a>


<form class="adminForm">
    <table >

        <tr>
            <td><a href="#books"><fmt:message key="admin.a.Books"/> </a></td>
            <td><a href="${Path.COMMAND_ADD_BOOK}">
                <fmt:message key="admin.a.AddBook"/>
            </a></td>
        </tr>
        <tr>
            <td><a href="#users"><fmt:message key="admin.a.Users"/> </a>
            <td><a href="${Path.COMMAND_NEW_LIBRARIAN}"><fmt:message key="admin.a.AddLibrarian"/></a></td>
        </tr>

    </table>
</form>
<c:if test="${sessionScope.cannotBlockUser!= null}">
    <script>
        alert("Cannot block this user. Call to support.");
    </script>
</c:if>
<c:if test="${sessionScope.cannotDeleteLibrarian != null}">
    <script>
        alert("Cannot delete librarian. Call to support.");
    </script>
</c:if>

</body>
</html>
