<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Trang Chủ</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/cssindex.css" />
<script>
        // Tự động chuyển đến servlet khi trang được tải
        window.onload = function() {
            fetch("${pageContext.request.contextPath}/QLNEWS/index")
                .then(response => {
                    if (response.ok) {
                        return response.text();
                    }
                    throw new Error('Network response was not ok.');
                })
                .then(html => {
                    document.body.innerHTML = html; // Thay đổi nội dung trang với phản hồi từ servlet
                })
                .catch(error => {
                    console.error('There was a problem with the fetch operation:', error);
                });
        };
    </script>
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
</style>
<body>
	<div class="container">
		<header>
			<img src="${pageContext.request.contextPath}/Views/img/image.png"
				alt="Bị lỗi" />
			<div class="text-overlay">
				<h1>Chân Trời Công Nghệ FPT</h1>
				<h3>Tin Tức Dẫn Đầu Giới Công Nghệ</h3>
			</div>
		</header>
		<!-- Thanh điều hướng -->
		<nav>
			<a href="${pageContext.request.contextPath}/NEWS/index">Trang chủ</a>
			<c:if test="${empty dangnhap}">
				<!-- Hiển thị đăng ký và đăng nhập nếu người dùng chưa đăng nhập -->
				<a href="${pageContext.request.contextPath}/Views/login.jsp">Đăng
					nhập</a>
				<a href="${pageContext.request.contextPath}/Views/register.jsp">Đăng
					ký</a>
			</c:if>

			<c:if test="${not empty dangnhap}">
				<!-- Hiển thị tên người dùng khi đã đăng nhập -->

				<div class="dropdown">
					<a href="#">chào, ${dangnhap.fullname}</a>
					<ul class="dropdown-menu">
						<li> <a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a></li>
					</ul>
				</div>
				
			</c:if>
		</nav>
		<!-- Hàng chữ chạy -->
		<marquee behavior="scroll" direction="left" class="scrolling-text">Nhà
			Cái Đến Từ Châu Âu</marquee>
		<!-- Nội dung chính -->
		<div class="main">
			<div class="content">
				<h1 class="mt-5">QUẢN LÝ TIN TỨC</h1>
				<!-- Thông báo (nếu có) -->
				<c:if test="${not empty message}">
					<div class="alert alert-info">${message}</div>
				</c:if>

				<!-- Form thêm/chỉnh sửa người dùng -->
				<div class="user-form-container">
					<form id="actionForm" method="post" class="mb-4"
						enctype="multipart/form-data">
						<input type="hidden" name="action"
							value="${user != null ? 'update' : 'add'}">

						<div class="user-form-group">
							<label for="id">ID:</label> <input type="text" name="id"
								class="user-form-control" value="${user != null ? user.id : ''}"
								${user != null ? 'readonly' : ''}>
						</div>

						<div class="user-form-group">
							<label for="title">Title:</label> <input type="text" name="title"
								class="user-form-control"
								value="${user != null ? user.title : ''}" required>
						</div>

						<div class="user-form-group">
							<label for="content">Content:</label>
							<textarea rows="5" name="content" class="user-form-control"
								required>${user != null ? user.content : ''}</textarea>
						</div>

						<div class="user-form-group">
							<label for="postedDate">PostedDate:</label> <input type="date"
								name="postedDate" class="user-form-control"
								value="${user != null ? user.postedDate : ''}" required>
						</div>

						<div class="user-form-group">
							<label for="author">Author:</label> <input type="text"
								name="author" class="user-form-control"
								value="${user != null ? user.author : ''}" required>
						</div>

						<div class="user-form-group">
							<label for="viewCount">ViewCount:</label> <input type="text"
								name="viewCount" class="user-form-control"
								value="${user != null ? user.viewCount : ''}" required>
						</div>

						<div class="user-form-group">
							<label for="categoryId">Category:</label> <select
								name="categoryId" class="user-form-control" required>
								<option value="">Chọn thể loại</option>
								<!-- Thêm một option mặc định -->
								<c:forEach var="category" items="${categories}">
									<option value="${category.id}"
										${user != null && user.categoryId == category.id ? 'selected' : ''}>
										${category.name}</option>
								</c:forEach>
							</select>
						</div>


						<div class="user-form-group"
							style="display: flex; align-items: center;">
							<div style="flex: 1;">
								<label for="image">Image:</label> <input name="urlimage"
									type="file">
							</div>
							<div style="flex: 1; text-align: right;">
								<img alt="chưa có" src=""
									style="max-width: 200px; height: 200px" name="image">
							</div>
						</div>



						<div class="user-form-group">
							<label for="home">Home:</label>
							<div style="flex: 1;">
								<label>Yes</label> <input class="form-check-input" type="radio"
									name="home" id="true" value="true"
									${user != null && user.home == true ? 'checked' : ''}>
								<label>No</label> <input class="form-check-input" type="radio"
									name="home" id="false" value="false"
									${user != null && user.home == false ? 'checked' : ''}>
							</div>
						</div>

						<div class="text-center">
							<!-- Nút Thêm -->
							<button type="button" onclick="submitForm('/QLNEWS/create')"
								class="user-form-button">Thêm</button>

							<!-- Nút Xóa -->
							<button type="button" onclick="submitForm('/QLNEWS/delete')"
								class="user-form-button">Xóa</button>

							<!-- Nút Cập Nhật -->
							<button type="button" onclick="submitForm('/QLNEWS/update')"
								class="user-form-button">Cập Nhật</button>

							<!-- Nút Làm mới -->
							<button type="button" onclick="resetForm()"
								class="user-form-button">Làm mới</button>
						</div>
					</form>
				</div>
				<br> <br> <br>



				<!-- Danh sách người dùng -->
				<table class="table table-bordered" style="text-align: center;">
					<thead>
						<tr style="background-color: gray;">
							<th>ID</th>
							<th>Title</th>
							<th>Image</th>
							<th>PostedDate</th>
							<th>Author</th>
							<th>ViewCount</th>
							<th>CategoryId</th>
							<th>Home</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${list}">
							<tr
								onclick="selectUser('${user.id}', '${user.title}', '${user.content}', '${user.image}', '${user.postedDate}', '${user.author}', '${user.viewCount}', '${user.categoryId}', '${user.home}')">
								<td>${user.id}</td>
								<td>${user.title}</td>
								<td><img
									src="${pageContext.request.contextPath}/img_asm/${user.image}"
									alt="User Image" style="width: 50px"></td>

								<td>${user.postedDate}</td>
								<td>${user.author}</td>
								<td>${user.viewCount}</td>
								<td>${user.categoryId}</td>
								<td>${user.home}</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- Sidebar -->
			<div class="sidebar">
				<a href="#">Trang chủ</a> <a href="#">Quản lý Tin Tức</a> <a
					href="#">Quản lý Người Dùng</a> <a href="#">Tin Yêu
					thích(coming soon)</a>
			</div>
		</div>
		<footer>
			<nav>
				<a href="#">Email</a> <a href="#">SDT</a> <a href="#">FB</a> <a
					href="#">Zalo</a> <a href="#">Mes</a>
			</nav>
		</footer>
	</div>
	<script>
		function selectUser(id, title, content, image, postedDate, author,
				viewCount, categoryId, home) {
			document.getElementsByName('id')[0].value = id;
			document.getElementsByName('title')[0].value = title;
			document.getElementsByName('content')[0].value = content;
			document.getElementsByName('postedDate')[0].value = postedDate; // Thêm postedDate
			document.getElementsByName('author')[0].value = author;
			document.getElementsByName('viewCount')[0].value = viewCount;
			document.getElementsByName('categoryId')[0].value = categoryId;

			const yes = document.getElementById('true');
			const no = document.getElementById('false');

			// Kiểm tra xem 'home' là true hay false để chọn đúng radio button
			// Chuyển đổi giá trị 'home' sang kiểu boolean
			const isHome = (home === 'true'); // 'true' chuỗi sẽ trở thành true, còn lại sẽ là false
			yes.checked = isHome;
			no.checked = !isHome;
			document.getElementsByName('image')[0].src = "${pageContext.request.contextPath}/img_asm/"
					+ image;

		}
		// Hàm thay đổi action của form và gửi đi
		function submitForm(actionPath) {
    var form = document.getElementById("actionForm");
    
    // Kiểm tra nếu các trường bắt buộc không trống
    var title = document.getElementsByName("title")[0].value;
    var content = document.getElementsByName("content")[0].value;
    var author = document.getElementsByName("author")[0].value;
    var viewCount = document.getElementsByName("viewCount")[0].value;
    var categoryId = document.getElementsByName("categoryId")[0].value;

    // Nếu có bất kỳ trường nào trống, hiển thị thông báo lỗi và không gửi form
    if (!title || !content || !author || !viewCount || !categoryId) {
        alert("Vui lòng điền đầy đủ thông tin!");
        return; // Ngừng hành động gửi form nếu có trường trống
    }

    // Nếu không có trường nào trống, tiếp tục gửi form
    form.action = '/ASM_Java3/' + actionPath;
	form.submit();
}

	
		function resetForm() {
		    document.getElementById("actionForm").reset(); // Reset tất cả các trường trong form
		}
	</script>
</body>
</html>
