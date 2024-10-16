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

@WebServlet("/news")
public class NEWListSeverlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        CATEGORIESDao loaidao=new CATEGORIESDao();
        // Lấy danh sách bài viết theo loại
        List<NEWS> filteredNews = getNewsByCategory(categoryId);
System.out.println(filteredNews);
        // Gửi danh sách bài viết vào JSP
        
     
        	request.setAttribute("thongbao","BÀI BÁO "+ loaidao.selectByid(categoryId).getName());
        request.setAttribute("filteredNews", filteredNews);
        // Chuyển tiếp sang JSP để hiển thị bài viết
        
        List<CATEGORIES> listloai = loaidao.selectAll();
        if(filteredNews.size()<1) {
        	request.setAttribute("thongbao","CHƯA CÓ BÀI BÁO NÀO VỀ "+loaidao.selectByid(categoryId).getName());
        }
        request.setAttribute("listloai", listloai);
        request.getRequestDispatcher("Views/news-list.jsp").forward(request, response);
    }

    private List<NEWS> getNewsByCategory(String categoryId) {
        // Code để lấy danh sách bài viết từ cơ sở dữ liệu theo categoryId
        // Giả sử bạn có một phương thức truy vấn cơ sở dữ liệu để lấy bài viết theo loại
        return new NEWSDao().getNewsByCategory(categoryId);
        
    }
}
