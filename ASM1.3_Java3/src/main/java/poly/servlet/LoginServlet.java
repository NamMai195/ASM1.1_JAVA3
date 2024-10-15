package poly.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.dao.USERSDao;
import poly.entity.USERS;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private USERSDao dao = new USERSDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        USERS user = dao.checkLogin(username, password);
        if (user != null) {
            // Đăng nhập thành công
            request.getSession().setAttribute("user", user); // Lưu thông tin người dùng vào session
            response.sendRedirect(request.getContextPath() + "/NEWS/index"); // Chuyển hướng đến trang chủ
        } else {
            // Đăng nhập thất bại
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
            request.getRequestDispatcher("/Views/login.jsp").forward(request, response); // Quay lại trang đăng nhập
        }
    }
}

