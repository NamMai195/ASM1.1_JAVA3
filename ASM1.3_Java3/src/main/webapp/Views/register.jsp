<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Trang Chủ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/cssindex.css" />
  </head>
  <style>
 .content {
	flex: 1;
	background-color: white;
	padding: 20px;
	margin-right: 20px;
	border: 1px solid #ddd;
	border-radius: 8px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}
/* Sidebar */
.sidebar {
	width: 220px;
	background-color: #D8D8D8;
	padding: 15px;
	border-radius: 8px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.sidebar a {
	display: block;
	padding: 10px 15px;
	color: white;
	background-color: #909090;
	margin-bottom: 10px;
	text-align: center;
	border-radius: 4px;
	text-decoration: none;
	transition: background-color 0.3s ease;
}

.sidebar a:hover {
	background-color: #686868;
	color: white;
}

/* Table */
.table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	font-size: 15px;
}

.table th, .table td {
	padding: 12px 15px;
	text-align: center;
	border: 1px solid #ddd;
}

.table th {
	color: white;
	font-size: 16px;
}

.table tbody tr:hover {
	background-color: #f1f1f1;
}

/* Buttons */
.btn-warning, .btn-danger, .btn-secondary {
	text-decoration: none;
	padding: 6px 10px;
	font-size: 14px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	margin: 5px;
}

.btn-warning {
	background-color: #008B8B;
	color: white;
}

.btn-warning:hover {
	background-color: #20B2AA;
}

.btn-danger {
	background-color: #A52A2A;
	color: white;
}

.btn-danger:hover {
	background-color: #dc3545;
}

.btn-secondary {
	background-color: #6c757d;
	color: white;
}

.btn-secondary:hover {
	background-color: #0056b3;
}

/* Form Container */
.user-form-container {
	background-color: #ffffff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	margin-top: 20px;
	max-width: 600px;
	margin: auto;
	border: 1px solid #ddd;
}

.user-form-group {
	display: flex;
	justify-content: space-between;
	margin-bottom: 15px;
}

.user-form-group label {
	width: 25%;
	margin-right: 10px;
	font-weight: bold;
	color: #343a40;
}

.user-form-control {
	flex: 1;
	height: 40px;
	padding: 8px;
	border: 1px solid #ddd;
	border-radius: 4px;
	width: 100%;
	margin-right: 10px;
	padding: 8px;
}

/* Form Radio Buttons */
.form-check-label {
	margin-left: 5px;
}

.form-check-input {
	margin-right: 5px;
}

/* Submit Button */
.user-form-button {
	background-color: #008B8B;
	color: white;
	border: none;
	padding: 10px 20px;
	border-radius: 4px;
	cursor: pointer;
	margin: 0 auto;
	display: block;
	transition: background-color 0.3s ease;
}

.user-form-button:hover {
	background-color: #20B2AA;
}

.mt-5 {
	text-align: center;
	margin-bottom: 20px;
}
.text-center {
	display: flex;
	justify-content: center; /* Căn giữa */
	margin: 30px 0; /* Khoảng cách trên và dưới */
}

.form-inline {
	display: flex; /* Sử dụng flex để căn chỉnh */
	align-items: center; /* Căn chỉnh giữa theo chiều dọc */
}

/* Tăng kích thước cho input */
.form-control {
	width: 300px; /* Kích thước rộng cho input */
	height: 50px; /* Tăng chiều cao cho input */
	padding: 10px; /* Padding cho input */
	font-size: 16px; /* Tăng kích thước chữ */
	border: 1px solid #C0C0C0; /* Viền màu xanh */
	border-radius: 4px; /* Bo góc */
	margin-right: 10px; /* Khoảng cách giữa input và button */
}

/* Tăng kích thước cho button */
.btn-secondary {
	background-color: #FF8C00;
	height: 50px; /* Đảm bảo button cao bằng input */
	padding: 0 20px; /* Padding cho button */
	font-size: 16px; /* Tăng kích thước chữ cho button */
	transition: background-color 0.3s ease;
	display: flex; /* Căn giữa nội dung của button */
	align-items: center; /* Căn giữa theo chiều dọc */
	justify-content: center; /* Căn giữa theo chiều ngang */
}

.btn-secondary:hover {
	background-color: #F4A460; /* Màu nền khi rê chuột */
}

/* Khi khung nhập liệu được focus */
input[type="text"]:focus, input[type="email"]:focus, input[type="password"]:focus
	{
	border-color: #F0F8FF; /* Đổi màu viền thành xanh */
	background-color: #f8f9fa; /* Đổi màu nền thành màu sáng hơn */
	outline: none; /* Bỏ viền mặc định */
}

.toggle-password {
	position: absolute;
	right: 40px;
	top: 50%;
	transform: translateY(-50%);
	cursor: pointer;
	font-size: 16px; /* Kích thước của biểu tượng con mắt */
}
.search-container {
    width: 100%; /* Đặt chiều rộng 100% */
    display: flex; /* Để căn chỉnh nội dung */
    justify-content: center; /* Căn giữa nội dung */
    margin-bottom: 20px; /* Khoảng cách dưới thanh tìm kiếm */
}

</style>
  <body>
    <div class="container">
      <header>
        <img src="${pageContext.request.contextPath}/Views/img/image.png" alt="Bị lỗi" />
        <div class="text-overlay">
          <h1>Chân Trời Công Nghệ FPT</h1>
          <h3>Tin Tức Dẫn Đầu Giới Công Nghệ</h3>
        </div>
      </header>
      <!-- Thanh điều hướng -->
      <nav>
        <a href="${pageContext.request.contextPath}/NEWS/index">Trang chủ</a>
       <a href="${pageContext.request.contextPath}/Views/login.jsp">Đăng nhập</a> 
        <a onclick="window.history.back()">Quay lại</a>
      </nav>
      <!-- Hàng chữ chạy -->
      <marquee behavior="scroll" direction="left" class="scrolling-text">
        Nhà Cái Đến Từ Châu Âu
      </marquee>
      <!-- Nội dung chính -->
      <div class="main">
        <div class="content">
          <h1 class="mt-5">ĐĂNG KÝ NGƯỜI DÙNG</h1>
			<!-- Thông báo (nếu có) -->
			<c:if test="${not empty message}">
				<div class="alert alert-info">${message}</div>
			</c:if>

		<!-- Form thêm/chỉnh sửa người dùng -->
				<div class="user-form-container">
					<form id="actionForm"
						method="post" class="mb-4">
						<div class="user-form-group">
							<label for="id">Login Name:</label> <input type="text" name="id"
								class="user-form-control" value="${user != null ? user.id : ''}"
								required>
						</div>

						<div class="user-form-group">
							<label for="fullname">FullName:</label> <input type="text"
								name="fullname" class="user-form-control"
								value="${user != null ? user.fullname : ''}" required>
						</div>

						<div class="user-form-group">
							<label for="birthday">Birthday:</label> <input type="date"
								name="birthday" class="user-form-control"
								value="${user != null ? user.birthday : ''}" required>
						</div>

						<div class="user-form-group">
							<label for="gender">Gender:</label>
							<div style="flex: 1;">
								<input class="form-check-input" type="radio" name="gender"
									id="male" value="1"
									${user != null && user.gender ? 'checked' : ''}> <label
									class="form-check-label" for="male">Male</label> <input
									class="form-check-input" type="radio" name="gender" id="female"
									value="0"
									${user != null && user.gender == false ? 'checked' : ''}>
								<label class="form-check-label" for="female">Female</label>
							</div>
						</div>

						<div class="user-form-group">
							<label for="mobile">Mobile:</label> <input type="text"
								name="mobile" class="user-form-control"
								value="${user != null ? user.mobile : ''}" required>
						</div>

						<div class="user-form-group">
							<label for="email">Email:</label> <input type="email"
								name="email" class="user-form-control"
								value="${user != null ? user.email : ''}" required>
						</div>

						<div class="user-form-group">
							<label for="password">Password:</label>
							<div style="flex: 1; position: relative;">
								<input type="password" id="password" name="password"
									class="user-form-control"
									value="${user != null ? user.password : ''}" required
									style="width: calc(100% - 30px);"> <span
									class="toggle-password" onclick="togglePassword()">👁️‍🗨️</span>
							</div>
						</div>

						<div class="user-form-group">
							<label for="role">Role:</label>
							<div style="flex: 1;">
								 <input
									class="form-check-input" type="radio" name="role" id="reporter"
									value="0"
									${user != null && user.role == false ? 'checked' : ''}>
								<label class="form-check-label" for="reporter">Reporter</label>
							</div>
						</div>

						<div class="text-center">
							<!-- Nút Thêm -->
							<button type="button" onclick="submitForm('/USERS/dangky')"
								class="user-form-button">Đăng ký </button>
						</div>
					</form>
				</div>
        </div>
      </div>
      <footer>
        <nav>
          <a href="#">Email</a>
          <a href="#">SDT</a>
          <a href="#">FB</a>
          <a href="#">Zalo</a>
          <a href="#">Mes</a>
        </nav>
      </footer>
    </div>
    <script>
        function togglePassword() {
            const passwordField = document.getElementById('password');
            const toggleEye = document.querySelector('.toggle-password');

            if (passwordField.type === 'password') {
                passwordField.type = 'text'; // Hiển thị mật khẩu
                toggleEye.innerText = '👁️'; // Biểu tượng con mắt mở
            } else {
                passwordField.type = 'password'; // Ẩn mật khẩu
                toggleEye.innerText = '👁️‍🗨️'; // Biểu tượng con mắt đóng
            }
        }
     // Hàm thay đổi action của form và gửi đi
		function submitForm(actionPath) {
			var form = document.getElementById("actionForm");
			// Thêm đường dẫn hành động vào phần sau của URL
			form.action = '/ASM_Java3/' + actionPath;
			form.submit();
		}
    </script>
  </body>
</html>
