<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>All teachers</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/open-iconic-bootstrap.css" />" />
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">

</head>
<body>

<!-- NAVBAR START -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample08" aria-controls="navbarsExample08" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample08">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${request.contextPath}/teachers/all">Teachers<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${request.contextPath}/teachers/new">New teacher</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="${request.contextPath}/logs">Logs</a>
            </li>
        </ul>
    </div>
</nav>
<!-- NAVBAR END -->


<!-- TABLE START -->

<div class="container-fluid">
    <div class="row">

        <main class="col-sm-8 offset-sm-2 col-md-8 offset-md-2 pt-3">

            <div class="table-responsive">

                <table class="table table-hover">

                    <thead>
                    <th>Id</th>
                    <th>Imię</th>
                    <th>Nazwisko</th>
                    <th>Email</th>
                    <th>Wiek</th>
                    <th>Telefon</th>
                    <th>Edit</th>
                    <th>Remove</th>

                    </thead>

                    <tbody>
                        <c:forEach var="teacher" items="${teachers}">
                            <c:url var="editLink" value="/teachers/edit">
                                <c:param name="id" value="${teacher.id}"></c:param>
                            </c:url>

                            <c:url var="deleteLink" value="/teachers/delete">
                                <c:param name="id" value="${teacher.id}"></c:param>
                            </c:url>

                            <tr>
                                <td>
                                    ${teacher.id}
                                </td>

                                <td>
                                    <strong>${teacher.firstName}</strong>
                                </td>

                                <td>
                                    <strong>${teacher.lastName}</strong>
                                </td>

                                <td>
                                    ${teacher.email}
                                </td>

                                <td>
                                    ${teacher.phoneNumber}
                                </td>

                                <td>
                                    <a href="${editLink}">Update</a>
                                </td>
                                <td>
                                    <a href="${deleteLink}">Delete</a>
                                </td>

                            </tr>

                        </c:forEach>
                    </tbody>

                </table>
            </div>

        </main>

    </div>
</div>


<!-- TABLE END -->

<script src="<c:url value="/resources/js/jquery-3.3.1.slim.min.js" />"></script>
<script src="<c:url value="/resources/js/tether.min.js" />"></script>
<script src="<c:url value="/resources/js/popper.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>
