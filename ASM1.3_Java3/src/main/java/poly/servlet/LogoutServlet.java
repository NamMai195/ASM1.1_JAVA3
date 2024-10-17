package poly.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy session hiện tại
        HttpSession session = request.getSession(false); // false để không tạo session mới

        if (session != null) {
            // Xóa thông tin bài báo đã xem
            session.removeAttribute("recentArticles");

            // Xóa thông tin người dùng đăng nhập
            session.removeAttribute("user");

            // Hủy session
            session.invalidate();
        }

        // Chuyển hướng về trang login sau khi đăng xuất
        request.getRequestDispatcher("/Views/index.jsp").forward(request, response); 
    }
}

