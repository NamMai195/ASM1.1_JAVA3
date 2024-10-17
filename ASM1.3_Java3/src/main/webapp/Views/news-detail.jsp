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

<style type="text/css">
.sidebar-right {
	background-color: #f4f4f4;
	padding: 20px;
	border-radius: 8px;
	margin-top: 20px;
}

/* Tạo kiểu cho các danh sách trong sidebar */
.sidebar-right h3 {
	font-size: 1.5em;
	color: #333;
	font-weight: bold;
	margin-bottom: 10px;
}

/* Định dạng các danh sách */
.sidebar-right ul {
	list-style-type: none;
	padding: 0;
}

.sidebar-right ul li {
	margin: 8px 0;
	padding: 5px;
	background-color: #fff;
	border-radius: 4px;
}

.sidebar-right ul li a {
	color: black;
	font-size: 1.2em;
	text-decoration: none;
}

.sidebar-right ul li a:hover {
	color: #007bff; /* Màu hover khi di chuột vào */
	text-decoration: underline;
}

/* Tạo một kiểu giống như thanh điều hướng cho dropdown */
.sidebar-right .dropdown {
	position: relative;
	display: inline-block;
}

.sidebar-right .dropdown-menu {
	display: none;
	position: absolute;
	background-color: #fff;
	min-width: 160px;
	box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.sidebar-right .dropdown:hover .dropdown-menu {
	display: block;
}

/* Button đăng ký Newsletter */
.sidebar-right button {
	padding: 10px;
	font-size: 1.1em;
	color: white;
	background-color: #007bff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	width: 100%;
}

.sidebar-right button:hover {
	background-color: #0056b3;
}

.sidebar-right input[type="email"] {
	padding: 10px;
	font-size: 1.1em;
	width: calc(100% - 22px);
	border-radius: 5px;
	border: 1px solid #ccc;
	margin-bottom: 10px;
}
/* Tạo kiểu cho các liên kết trong danh sách */
.sidebar-right ul li a {
	color: black; /* Màu chữ đen */
	font-size: 1.5em; /* Làm chữ lớn hơn */
	text-decoration: none; /* Loại bỏ gạch chân */
}

/* Tạo hiệu ứng hover cho các liên kết trong danh sách */
.sidebar-right ul li a:hover {
	color: #007bff; /* Màu chữ khi hover */
	text-decoration: none; /* Đảm bảo không có gạch chân khi hover */
}
/* Tạo kiểu cho các liên kết trong danh sách */
.sidebar-right ul li a {
	color: black; /* Màu chữ đen */
	font-size: 1.5em; /* Làm chữ lớn hơn */
	text-decoration: none; /* Loại bỏ gạch chân */
	text-transform: uppercase; /* Chữ in hoa */
}

/* Tạo hiệu ứng hover cho các liên kết trong danh sách */
.sidebar-right ul li a:hover {
	color: #007bff; /* Màu chữ khi hover */
	text-decoration: none; /* Đảm bảo không có gạch chân khi hover */
}
</style>
</head>
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
			<div class="dropdown">
				<a href="#">Thể loại</a>
				<ul class="dropdown-menu">
					<c:forEach var="loai" items="${listloai}">
						<li><a
							href="${pageContext.request.contextPath}/news?categoryId=${loai.id}">${loai.name}</a></li>
					</c:forEach>


				</ul>
			</div>
			<c:if test="${empty dangnhap}">
				<!-- Hiển thị đăng ký và đăng nhập nếu người dùng chưa đăng nhập -->
				<a href="${pageContext.request.contextPath}/Views/login.jsp">Đăng
					nhập</a>
				<a href="${pageContext.request.contextPath}/Views/register.jsp">Đăng
					ký</a>
			</c:if>

			<c:if test="${not empty dangnhap}">
				<!-- Hiển thị tên người dùng khi đã đăng nhập -->
				<a href="${pageContext.request.contextPath}/Views/QLTinTuc.jsp">Quản
					Lí Tin</a>
				<div class="dropdown">
					<a href="#">chào, ${dangnhap.fullname}</a>
					<ul class="dropdown-menu">
						<li> <a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a></li>
					</ul>
				</div>

			</c:if>
		</nav>
		<!-- Hàng chữ chạy -->
		<marquee behavior="scroll" direction="left" class="scrolling-text">
			Nhà Cái Đến Từ Châu Âu </marquee>
		<!-- Nội dung chính -->
		<div class="main">
			<div class="content">
				<h1>${article.title}</h1>
				<p>
					<strong>Ngày đăng:</strong> ${article.postedDate}
				</p>
				<p>
					<strong>Tác giả:</strong> ${article.author}
				</p>
				<div>
					<img
						src="${pageContext.request.contextPath}/img_asm/${article.image}"
						alt="Bài báo" />
					<p>${article.content}</p>
					<!-- Giả sử bạn có trường content trong article -->
				</div>
				<!-- Nút quay lại sử dụng JavaScript -->
				<a href="javascript:void(0)"
					onclick="window.location.href = document.referrer;">Quay lại</a>



			</div>
			<!-- Phần bên phải -->
			<div class="sidebar-right">
				
				<h3>5 bài báo cùng loại</h3>
				<ul>
					<c:forEach var="sameArticle" items="${sameCategoryArticles}">
						<li><a
							href="${pageContext.request.contextPath}/news/detail?id=${sameArticle.id}">
								${sameArticle.title} </a></li>
					</c:forEach>
				</ul>




				<h3>Newsletter</h3>
				<input type="email" placeholder="Nhập email của bạn" />
				<button>Đăng ký</button>
			</div>
		</div>
		<footer>
			<nav>
				<a href="#">Email</a> <a href="#">SDT</a> <a href="#">FB</a> <a
					href="#">Zalo</a> <a href="#">Mes</a>
			</nav>
		</footer>
	</div>
</body>
</html>
