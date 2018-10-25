<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add new teacher</title>
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


<!-- FORM START -->

<div class="container-fluid">
    <div class="row">

        <main class="col-sm-6 offset-sm-3 col-md-6 offset-md-3 pt-3">

            <form:form action="save" modelAttribute="teacher" method="POST" id="addBookForm">
                <form:hidden path="id"/>

                <div class="form-group row">
                    <label for="firstName" class="col-3 col-form-label">First name:</label>
                    <div class="col-9">
                        <form:input path="firstName" id="firstName" class="form-control" cssErrorClass="form-control is-invalid" />
                        <form:errors path="firstName" cssClass="invalid-feedback" />
                    </div>
                </div>

                <div class="form-group row">
                    <label for="lastName" class="col-3 col-form-label">Last name:</label>
                    <div class="col-9">
                        <form:input path="lastName" id="lastName" class="form-control" cssErrorClass="form-control is-invalid" />
                        <form:errors path="lastName" cssClass="invalid-feedback" />
                    </div>
                </div>

                <div class="form-group row">
                    <label for="email" class="col-3 col-form-label">Email:</label>
                    <div class="col-9">
                        <form:input path="email" id="email" class="form-control" cssErrorClass="form-control is-invalid" />
                        <form:errors path="email" cssClass="invalid-feedback" />
                    </div>
                </div>
             <div class="form-group row">
                 <label for="age" class="col-3 col-form-label">Age:</label>
                 <div class="col-9">
                        <form:input path="age" id="age" class="form-control" cssErrorClass="form-control is-invalid" />
                        <form:errors path="age" cssClass="invalid-feedback" />
            </div>
            </div>
                <div class="form-group row">
                    <label for="phoneNumber" class="col-3 col-form-label">Phone:</label>
                    <div class="col-9">
                        <form:input path="phoneNumber" id="phoneNumber" class="form-control" cssErrorClass="form-control is-invalid" />
                        <form:errors path="phoneNumber" cssClass="invalid-feedback" />
                    </div>
                </div>

                <button type="submit" value="Save" class="btn btn-primary">Save</button>

            </form:form>

        </main>

    </div>
</div>


<!-- FORM END -->

<script src="<c:url value="/resources/js/jquery-3.3.1.slim.min.js" />"></script>
<script src="<c:url value="/resources/js/tether.min.js" />"></script>
<script src="<c:url value="/resources/js/popper.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>
