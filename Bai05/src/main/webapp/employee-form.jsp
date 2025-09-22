<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="iuh.fit.employeejsp.model.Department" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Employee Information</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container mt-4">

    <!-- Banner -->
    <div class="mb-3">
        <img src="${pageContext.request.contextPath}/images/HRbanner.jpg"
             class="img-fluid rounded" alt="HR Banner">
    </div>

    <h2 class="text-primary mb-3">
        <c:choose>
            <c:when test="${employee != null}">Edit Employee</c:when>
            <c:otherwise>Add New Employee</c:otherwise>
        </c:choose>
    </h2>

    <!-- Form nhập thông tin nhân viên -->
    <form action="${pageContext.request.contextPath}/employees" method="post" class="card p-4 shadow-sm">

        <!-- Hidden field cho id (khi edit) -->
        <input type="hidden" name="id" value="${employee.id}"/>

        <!-- Name -->
        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control"
                   value="${employee.name}" required/>
        </div>

        <!-- Salary -->
        <div class="mb-3">
            <label class="form-label">Salary</label>
            <input type="number" step="0.01" name="salary" class="form-control"
                   value="${employee.salary}" required/>
        </div>

        <!-- Department -->
        <div class="mb-3">
            <label class="form-label">Department</label>
            <select name="departmentId" class="form-select" required>
                <c:forEach var="dep" items="${departments}">
                    <option value="${dep.department_id}"
                            <c:if test="${employee != null && dep.department_id == employee.departmentId}">
                                selected
                            </c:if>>
                            ${dep.department_name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- Nút Save -->
        <button type="submit" class="btn btn-success">Save</button>
        <a href="${pageContext.request.contextPath}/employees" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
