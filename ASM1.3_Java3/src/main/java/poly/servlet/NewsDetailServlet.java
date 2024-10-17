package poly.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.dao.CATEGORIESDao;
import poly.dao.NEWSDao;
import poly.entity.CATEGORIES;
import poly.entity.NEWS;

@WebServlet("/news/detail")
public class NewsDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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

                // Cập nhật danh sách bài báo vừa đọc vào session
                HttpSession session = request.getSession();
                List<NEWS> recentArticles = (List<NEWS>) session.getAttribute("recentArticles");

                if (recentArticles == null) {
                    recentArticles = new ArrayList<>();
                }

                // Kiểm tra nếu bài báo đã tồn tại trong danh sách
                boolean isDuplicate = false;
                for (NEWS a : recentArticles) {
                    if (a.getId().equals(article.getId())) {
                        isDuplicate = true;
                        break;
                    }
                }

                // Nếu không trùng lặp thì thêm bài báo vào danh sách
                if (!isDuplicate) {
                    recentArticles.add(0, article); // Thêm bài báo vào đầu danh sách
                    if (recentArticles.size() > 5) {
                        recentArticles.remove(5);  // Giới hạn danh sách không quá 5 bài
                    }
                }

                session.setAttribute("recentArticles", recentArticles);  // Lưu lại danh sách vào session

                // Lấy danh sách loại bài báo
                CATEGORIESDao categoryDao = new CATEGORIESDao();
                List<CATEGORIES> categoryList = categoryDao.selectAll();
                request.setAttribute("listloai", categoryList);

                // Lấy các bài báo cùng thể loại
                List<NEWS> sameCategoryArticles = newsService.getNewsByCategory(article.getCategoryId());
                request.setAttribute("sameCategoryArticles", sameCategoryArticles);

                // Chuyển đến trang chi tiết bài báo
                request.getRequestDispatcher("/Views/news-detail.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID không hợp lệ");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID bài báo không được cung cấp");
        }
    }
}
