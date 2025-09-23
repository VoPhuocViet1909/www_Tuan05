<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Quản lý tin tức</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-4">
    <h2 class="text-primary">Quản lý tin tức</h2>
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
            <th>Liên kết</th>
            <th>Xóa</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tt" items="${danhSachTinTuc}">
            <tr>
                <td>${tt.maTT}</td>
                <td>${tt.tieuDe}</td>
                <td>${tt.lienKet}</td>
                <td>
                    <form action="QuanLyFormServlet" method="post" style="display:inline;">
                        <input type="hidden" name="matt" value="${tt.maTT}"/>
                        <input type="hidden" name="madm" value="${tt.maDM}"/>
                        <button type="submit" class="btn btn-danger btn-sm"
                                onclick="return confirm('Bạn có chắc muốn xóa?');">Xóa</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="danh-sach-tin-tuc" class="btn btn-info">Quay lại danh sách</a>
</div>
</body>
</html>