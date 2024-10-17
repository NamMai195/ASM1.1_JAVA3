package poly.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.dao.NEWSLETTERSDao;
import poly.entity.NEWSLETTERS;

@WebServlet("/subscribe")
public class NewsletterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        if (email != null && !email.isEmpty()) {
            NEWSLETTERS newsletter = new NEWSLETTERS(email, true);
            NEWSLETTERSDao dao = new NEWSLETTERSDao();

            try {
                dao.insert(newsletter);
                request.setAttribute("message", "Đăng ký thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Đăng ký không thành công. Vui lòng thử lại!");
            }
        } else {
            request.setAttribute("message", "Email không hợp lệ.");
        }

        // Chuyển hướng về trang đăng ký
        request.getRequestDispatcher("/Views/index.jsp").forward(request, response);
    }
}

