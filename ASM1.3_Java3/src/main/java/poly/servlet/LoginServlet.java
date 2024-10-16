package poly.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.dao.CATEGORIESDao;
import poly.dao.USERSDao;
import poly.entity.CATEGORIES;
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
            request.getSession().setAttribute("dangnhap", user); // Lưu thông tin người dùng vào session
            
            // Kiểm tra quyền quản trị
            if (user.isRole()) { // Nếu người dùng là quản trị viên
                // Thay đổi URL và chuyển hướng đến trang quản lý người dùng
                response.sendRedirect(request.getContextPath() + "/USERS/QLNguoiDung");
            } else {
                // Thay đổi URL và chuyển hướng đến trang đăng tin
               
                request.getRequestDispatcher("/Views/QLTinTuc.jsp").forward(request, response); 
            }

        } else {
            // Đăng nhập thất bại
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
            request.getRequestDispatcher("/Views/login.jsp").forward(request, response); // Quay lại trang đăng nhập
        }
    }
}
