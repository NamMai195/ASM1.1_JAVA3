package poly.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.dao.CATEGORIESDao;
import poly.dao.NEWSDao;
import poly.entity.CATEGORIES;
import poly.entity.NEWS;

@WebServlet("/news/detail")
public class NewsDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Đảm bảo có thể kết nối đến cơ sở dữ liệu hoặc nguồn dữ liệu của bạn
    private NEWSDao newsService = new NEWSDao(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy ID bài báo từ tham số trong URL
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                // Lấy thông tin bài báo từ service
                NEWS article = newsService.selectByid(idParam);

                // Đưa thông tin bài báo vào request để hiển thị trên JSP
                request.setAttribute("article", article);

                // Chuyển đến trang chi tiết bài báo
                CATEGORIESDao loaidao=new CATEGORIESDao();
                List<CATEGORIES> listloai = loaidao.selectAll();
                request.setAttribute("listloai", listloai);
                request.getRequestDispatcher("/Views/news-detail.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID bài báo không được cung cấp");
        }
    }
}
