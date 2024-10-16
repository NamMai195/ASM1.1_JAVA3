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
			<a href="#">Trang chủ</a>
			<div class="dropdown">
				<a href="#">Thể loại</a>
				<ul class="dropdown-menu">
					<c:forEach var="loai" items="${listloai}">
						<li><a href="news?categoryId=${loai.id}">${loai.name}</a></li>
					</c:forEach>


				</ul>
			</div>
			<a href="#">Yêu thích</a> <a href="#">Đăng nhập</a> <a href="#">Đăng
				ký</a>
		</nav>
		<!-- Hàng chữ chạy -->
		<marquee behavior="scroll" direction="left" class="scrolling-text">
			Nhà Cái Đến Từ Châu Âu </marquee>
		<!-- Nội dung chính -->
		<div class="main">
			<div class="content">
				<h2>${thongbao}</h2>

				<c:forEach var="tin" items="${filteredNews}" varStatus="status">
					<div class="news-article">
						<img src="${pageContext.request.contextPath}/img_asm/${tin.image}"
							alt="Bài báo" />
						<h3>${status.index + 1}</h3>
						<!-- Hiển thị số thứ tự -->
						<div>
							<h1>
								<a
									href="${pageContext.request.contextPath}/news/detail?id=${tin.id}">${tin.title}</a>
							</h1>
							<br />
							<p>${tin.postedDate}-${tin.author}</p>
						</div>

					</div>
				</c:forEach>

			</div>
			<!-- Phần bên phải -->
			<div class="sidebar-right">
				<h3>5 bài báo hot nhất</h3>
				<ul>
					<li>Bài báo 1</li>
					<li>Bài báo 2</li>
					<li>Bài báo 3</li>
					<li>Bài báo 4</li>
					<li>Bài báo 5</li>
				</ul>

				<h3>5 bài báo vừa đọc</h3>
				<ul>
					<li>Bài báo 1</li>
					<li>Bài báo 2</li>
					<li>Bài báo 3</li>
					<li>Bài báo 4</li>
					<li>Bài báo 5</li>
				</ul>

				<h3>5 bài báo được xem nhiều nhất</h3>
				<ul>
					<li>Bài báo 1</li>
					<li>Bài báo 2</li>
					<li>Bài báo 3</li>
					<li>Bài báo 4</li>
					<li>Bài báo 5</li>
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
