package poly.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import poly.dao.CATEGORIESDao;
import poly.dao.NEWSDao;
import poly.entity.CATEGORIES;
import poly.entity.NEWS;

import jakarta.servlet.annotation.MultipartConfig;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 100) // 100MB
@WebServlet({ "/QLNEWS/index", "/QLNEWS/edit/*", "/QLNEWS/create", "/QLNEWS/update", "/QLNEWS/delete",
		"/QLNEWS/reset" })
public class QLNEWSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_QLTT = "/Views/QLTinTuc.jsp";
	private static final String UPLOAD_DIRECTORY = "/img_asm"; // Thêm khai báo biến UPLOAD_DIRECTORY

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NEWS form = new NEWS();
		NEWSDao dao = new NEWSDao();

		// Đăng ký DateConverter như trước
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPattern("yyyy-MM-dd");
		ConvertUtils.register(dateConverter, Date.class);

		try {
			BeanUtils.populate(form, req.getParameterMap());

			// Xử lý phần upload file
			Part filePart = req.getPart("urlimage"); // Lấy file ảnh từ form
			if (filePart != null && filePart.getSize() > 0) {
				String fileName = getSubmittedFileName(filePart); // Lấy tên file
				String uploadPath = getServletContext().getRealPath(UPLOAD_DIRECTORY);

				// Tạo thư mục nếu chưa tồn tại
				java.io.File uploadDir = new java.io.File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdir();
				}

				// Lưu file vào thư mục
				filePart.write(uploadPath + java.io.File.separator + fileName);

				// Lưu tên file vào database (thuộc tính image của đối tượng NEWS)
				form.setImage(fileName);
			} else {
				// Nếu không upload file, kiểm tra xem đang trong hành động cập nhật
				if (req.getServletPath().contains("update")) {
					// Giữ nguyên giá trị image hiện tại (không cập nhật nếu không có file mới)
					NEWS currentNews = dao.selectByid(form.getId());
					if (currentNews != null) {
						form.setImage(currentNews.getImage());
					}
				} else {
					// Nếu không có file và không phải hành động update, đặt image là null
					form.setImage(null);
				}
			}

			// Xử lý các chức năng khác như create, update...
			String path = req.getServletPath();
			if (path.contains("create")) {
			    dao.insert(form);
			    req.setAttribute("message", "News added successfully.");
			} else if (path.contains("update")) {
			    dao.update(form);
			    req.setAttribute("message", "News updated successfully.");
			} else if (path.contains("delete")) {
			    String id = req.getParameter("id");
			    if (id != null && !id.trim().isEmpty()) {
			        dao.delete(id); // Gọi phương thức delete trong DAO
			        req.setAttribute("message", "News deleted successfully.");
			    } else {
			        req.setAttribute("error", "ID of news is missing.");
			    } 
			} else if ("search".equals(req.getParameter("action"))) {
			    String searchId = req.getParameter("searchId");
			    if (searchId != null && !searchId.trim().isEmpty()) {
			        form = dao.selectByid(searchId);
			        if (form != null) {
			            req.setAttribute("news", form); // Đảm bảo bạn gửi dữ liệu tìm kiếm nếu tìm thấy
			        } else {
			            req.setAttribute("error", "News not found.");
			        }
			    } else {
			        req.setAttribute("error", "Search ID is missing.");
			    }
			} else {
			    req.setAttribute("error", "Invalid action.");
			}


			// Các logic xử lý khác
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "An error occurred during the operation.");
		}
		CATEGORIESDao loaitin=new CATEGORIESDao();
		List<CATEGORIES> categories = loaitin.selectAll();
		req.setAttribute("categories", categories);
		// Hiển thị danh sách tin tức
		req.setAttribute("news", form);
		List<NEWS> list = dao.selectAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher(VIEW_QLTT).forward(req, resp);
	}

	private String getSubmittedFileName(Part part) {
		String header = part.getHeader("content-disposition");
		String[] elements = header.split(";");
		for (String element : elements) {
			if (element.trim().startsWith("filename")) {
				return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
