<%@ page import="iuh.fit.employeejsp.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Employees</title>
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

    <!-- Tiêu đề -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="text-primary">Employees List</h2>
        <a href="${pageContext.request.contextPath}/employees?action=new"
           class="btn btn-success">+ Add Employee</a>
    </div>

    <!-- Bảng danh sách nhân viên -->
    <table class="table table-bordered table-hover table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name Employee</th>
            <th>Salary</th>
            <th>Department</th>
            <th class="text-center">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emp" items="${employees}">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.name}</td>
                <td>${emp.salary}</td>
                <td>${emp.departmentId}</td>
                <td class="text-center">
                    <a href="${pageContext.request.contextPath}/employees?action=edit&id=${emp.id}"
                       class="btn btn-warning btn-sm">Edit</a>
                    <a href="${pageContext.request.contextPath}/employees?action=delete&id=${emp.id}"
                       class="btn btn-danger btn-sm"
                       onclick="return confirm('Are you sure you want to delete this employee?');">
                        Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Link sang Department -->
    <div class="mt-3">
        <a href="${pageContext.request.contextPath}/departments" class="btn btn-info">Manage Departments</a>
    </div>
</div>
</body>
</html>
