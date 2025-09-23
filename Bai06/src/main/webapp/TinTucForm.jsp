<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
  <title>Thêm Tin Tức</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"/>
  <script>
    function validateForm() {
      var tieuDe = document.forms["ttForm"]["tieuDe"].value;
      var noiDungTT = document.forms["ttForm"]["noiDungTT"].value;
      var lienKet = document.forms["ttForm"]["lienKet"].value;
      var madm = document.forms["ttForm"]["madm"].value;
      var urlRegex = /^http:\/\/.+/;
      var ndRegex = /^.{1,255}$/;
      if (!tieuDe || !noiDungTT || !lienKet || !madm) {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return false;
      }
      if (!urlRegex.test(lienKet)) {
        alert("Liên kết phải bắt đầu bằng http://");
        return false;
      }
      if (!ndRegex.test(noiDungTT)) {
        alert("Nội dung không quá 255 ký tự!");
        return false;
      }
      return true;
    }
  </script>
</head>
<body>
<div class="container mt-4">
  <h2 class="text-primary">Thêm tin tức mới</h2>
  <form name="ttForm" action="TinTucFormServlet" method="post" class="card p-4 shadow" onsubmit="return validateForm();">
    <div class="mb-3">
      <label>Tiêu đề:</label>
      <input type="text" name="tieuDe" class="form-control" required/>
    </div>
    <div class="mb-3">
      <label>Nội dung:</label>
      <textarea name="noiDungTT" class="form-control" maxlength="255" required></textarea>
    </div>
    <div class="mb-3">
      <label>Liên kết:</label>
      <input type="text" name="lienKet" class="form-control" required/>
    </div>
    <div class="mb-3">
      <label>Danh mục:</label>
      <select name="madm" class="form-select" required>
        <c:forEach var="dm" items="${danhMucList}">
          <option value="${dm.maDM}">${dm.tenDM}</option>
        </c:forEach>
      </select>
    </div>
    <button type="submit" class="btn btn-success">Thêm</button>
    <a href="danh-sach-tin-tuc" class="btn btn-secondary">Quay lại</a>
  </form>
</div>
</body>
</html>