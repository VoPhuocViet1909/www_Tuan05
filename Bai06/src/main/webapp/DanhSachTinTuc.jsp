<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Danh sách tin tức</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-4">
    <h2 class="text-primary">Danh sách tin tức theo danh mục</h2>
    <form method="get">
        <label>Chọn danh mục:</label>
        <select name="madm" onchange="this.form.submit()">
            <c:forEach var="dm" items="${danhMucList}">
                <option value="${dm.maDM}" <c:if test="${dm.maDM == param.madm}">selected</c:if>>${dm.tenDM}</option>
            </c:forEach>
        </select>
    </form>
    <table class="table table-bordered mt-3">
        <thead>
        <tr>
            <th>MATT</th>
            <th>Tiêu đề</th>
            <th>Nội dung</th>
            <th>Liên kết</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tt" items="${danhSachTinTuc}">
            <tr>
                <td>${tt.maTT}</td>
                <td>${tt.tieuDe}</td>
                <td>${tt.noiDungTT}</td>
                <td><a href="${tt.lienKet}" target="_blank">${tt.lienKet}</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="TinTucForm.jsp" class="btn btn-success">Thêm tin tức</a>
</div>
</body>
</html>