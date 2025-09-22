<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Department Information</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" class="img-fluid rounded mb-3" alt="HR Banner">

    <h2 class="text-primary mb-3">
        <c:choose>
            <c:when test="${department != null}">Edit Department</c:when>
            <c:otherwise>Add Department</c:otherwise>
        </c:choose>
    </h2>

    <form action="${pageContext.request.contextPath}/departments" method="post" class="card p-4 shadow-sm">
        <input type="hidden" name="id" value="${department.department_id}"/>

        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control" value="${department.department_name}" required/>
        </div>

        <button type="submit" class="btn btn-success">Save</button>
        <a href="${pageContext.request.contextPath}/departments" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
