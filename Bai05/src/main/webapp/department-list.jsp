<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Departments List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" class="img-fluid rounded mb-3" alt="HR Banner">

    <h2 class="text-primary">Departments List</h2>
    <a href="${pageContext.request.contextPath}/departments?action=new" class="btn btn-success mb-3">+ Add Department</a>

    <!-- Form tìm kiếm -->
    <form method="get" action="${pageContext.request.contextPath}/departments" class="row g-2 mb-3">
        <input type="hidden" name="action" value="search"/>
        <div class="col-auto">
            <input type="text" name="keyword" class="form-control" placeholder="Tìm phòng ban..." value="${param.keyword}"/>
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </form>

    <table class="table table-bordered table-hover table-striped">
        <thead class="table-dark">
        <tr>
            <th>DEPT ID</th>
            <th>Name Department</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dep" items="${departments}">
            <tr>
                <td>${dep.department_id}</td>
                <td>${dep.department_name}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/departments?action=edit&id=${dep.department_id}" class="btn btn-warning btn-sm">Edit</a>
                    <a href="${pageContext.request.contextPath}/departments?action=delete&id=${dep.department_id}" class="btn btn-danger btn-sm" onclick="return confirm('Xóa phòng ban này?');">Delete</a>
                    <a href="${pageContext.request.contextPath}/employees?deptId=${dep.department_id}" class="btn btn-info btn-sm">Employees</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
