<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý thực đơn</title>
<style>
body {
  font-family: sans-serif;
}

h1 {
  text-align: center;
}

div {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  opacity: 0.8;
}

ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

li {
  padding: 10px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  padding: 10px;
  text-align: left;
  border: 1px solid #ddd;
}

th {
  background-color: #f2f2f2;
}

img {
  width: 100px;
  height: 100px;
  object-fit: cover;
}

.left {
  background-color: lightblue;
  width: 200px;
  padding: 20px;
  border-radius: 10px;
}

.right {
  width: calc(100% - 220px);
  padding: 20px;
  border-radius: 10px;
}

.search-container {
  display: flex;
  align-items: center;
}

.search-container input {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  margin-right: 10px;
}

.search-container button {
  background-color: #f2f2f2;
  padding: 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.search-container button:hover {
  opacity: 0.8;
}

/* Ẩn form thêm món ăn mặc định */
#addMenuForm {
  display: none;
  margin-top: 20px;
}

/* Hiệu ứng khi form hiển thị */
#addMenuForm.show {
  display: block;
}

.menu-container {
  display: none; /* Mặc định ẩn */
  position: absolute;
  top: 50px;
  left: 10px;
  background-color: #f4f4f4;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.menu-container ul {
  list-style: none;
  padding: 0;
}

.menu-container li {
  margin: 10px 0;
}

.menu-container a {
  text-decoration: none;
  color: black;
}

.menu-toggle {
  cursor: pointer;
  font-size: 30px;
  position: absolute;
  top: 10px;
  left: 10px;
}

.menu-toggle.open {
  color: #3498db;
}

.menu-container.show {
  display: block;
}

form {
    background-color: ##c2d9e1;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 500px;
    margin: 20px auto;
}

form label {
    font-weight: bold;
    margin-bottom: 8px;
    display: block;
}

form input[type="text"], form input[type="number"], form textarea {
    width: 100%;
    padding: 12px;
    margin: 8px 0;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
}

form input[type="number"] {
    -moz-appearance: textfield; /* Tắt mũi tên ở input số */
}

form textarea {
    resize: vertical;
    min-height: 100px;
}

form button {
    background-color: #3498db;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    margin-top: 10px;
    transition: background-color 0.3s;
}

form button:hover {
    background-color: #2980b9;
}

form button:active {
    transform: scale(0.98);
}

</style>
</head>
<body>
<h1>Quản lý thực đơn</h1>

<!-- Nút thêm món ăn -->
<button id="toggleAddMenuFormBtn">Thêm món ăn mới</button>

<!-- Form thêm món ăn mới -->
<form id="addMenuForm" action="menu" method="POST">
    <input type="hidden" name="action" value="add">
    <label>Tên món:</label>
    <input type="text" name="ten_mon" required><br>

    <label>Giá:</label>
    <input type="number" name="gia" step="0.01" required><br>

    <label>Mô tả:</label>
    <textarea name="mo_ta"></textarea><br>

    <label>Nguyên liệu:</label>
    <input type="text" name="nguyen_lieu"><br>

    <label>Hình ảnh URL:</label>
    <input type="text" name="hinh_anh"><br>

    <button type="submit">Thêm món</button>
</form>

<!-- Form tìm kiếm món ăn -->
<form action="menu" method="GET">
    <input type="hidden" name="action" value="search">
    <input type="text" name="ten_mon" placeholder="Tìm kiếm món ăn">
    <button type="submit">Tìm kiếm</button>
</form>

<!-- Nút ba gạch -->
<div class="menu-toggle" onclick="toggleMenu()">&#9776;</div>
<!-- Menu ẩn -->
<div class="menu-container">
    <ul>
        <li><a href="#">Đơn hàng</a></li>
        <li><a href="#">Khách hàng</a></li>
        <li><a href="#">Nhân viên</a></li>
        <li><a href="#">Nguyên liệu</a></li>
        <li><a href="#">Hóa đơn</a></li>
        <li><a href="#">Lương</a></li>
    </ul>
</div>

<!-- Hiển thị danh sách món ăn -->
<table>
    <thead>
        <tr>
            <th>Tên món</th>
            <th>Giá</th>
            <th>Mô tả</th>
            <th>Nguyên liệu</th>
            <th>Hình ảnh</th>
            <th>Thao tác</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${menuItems}">
            <tr>
                <td>${item.tenMon}</td>
                <td>${item.gia}</td>
                <td>${item.moTa}</td>
                <td>${item.nguyenLieu}</td>
                <td><img src="${item.hinhAnh}" alt="${item.tenMon}" width="100" height="100"></td>
                <td>
                    <!-- Form xóa món -->
                    <form action="menu" method="POST" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${item.id}">
                        <button type="submit">Xóa</button>
                    </form>

                    <!-- Form sửa món -->
                    <form action="menu" method="POST" style="display:inline;">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${item.id}">
                        <button type="submit">Sửa</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<script>
    document.getElementById('toggleAddMenuFormBtn').addEventListener('click', function() {
        var form = document.getElementById('addMenuForm');
        form.classList.toggle('show'); // Thêm hoặc xóa lớp 'show' để hiển thị/ẩn form
    });

    function toggleMenu() {
        const menu = document.querySelector('.menu-container');
        const toggle = document.querySelector('.menu-toggle');

        menu.classList.toggle('show');
        toggle.classList.toggle('open');
    }
</script>

</body>
</html>
