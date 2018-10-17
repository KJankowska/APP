<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Last logs</title>
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
                    <th>Czas</th>
                    <th>Action type</th>
                    <th>Performed on</th>
                    <th>Client IP</th>
                    <th>Url</th>

                    </thead>

                    <tbody>
                        <c:forEach var="event" items="${logs}">

                            <c:if test='${event.action.type.name().equals("DELETE")}'>
                                <c:set value="#bd2130" var="color"></c:set>
                            </c:if>
                            <c:if test='${event.action.type.name().equals("EDIT")}'>
                                <c:set value="burlywood" var="color"></c:set>
                            </c:if>
                            <c:if test='${event.action.type.name().equals("ADD")}'>
                                <c:set value="#1c7430" var="color"></c:set>
                            </c:if>
                            <c:if test='${event.action.type.name().equals("VISIT")}'>
                                <c:set value="#4e555b" var="color"></c:set>
                            </c:if>

                            <tr>
                                <td>
                                    <fmt:formatDate value="${event.eventTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>

                                <td>
                                    <span style="color: ${color};">
                                            ${event.action.type}
                                    </span>
                                </td>

                                <td>
                                    <c:if test="${event.action.subjectId!=null}">
                                        Teacher id:
                                    </c:if>
                                    ${event.action.subjectId}
                                </td>

                                <td>
                                    ${event.ip}
                                </td>

                                <td>
                                    <a href="${event.link}">${event.link}</a>
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
