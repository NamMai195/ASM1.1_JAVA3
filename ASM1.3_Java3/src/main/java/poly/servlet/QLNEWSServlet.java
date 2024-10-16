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
import poly.dao.NEWSDao;
import poly.entity.NEWS;
import poly.entity.USERS;

@WebServlet({ "/QLNEWS/index", "/QLNEWS/edit/*", "/QLNEWS/create", "/QLNEWS/update", "/QLNEWS/delete",
		"/QLNEWS/reset" })
public class QLNEWSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_QLTT = "/Views/QLTinTuc.jsp";

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NEWS form = new NEWS();
		NEWSDao dao = new NEWSDao();
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPattern("yyyy-MM-dd");
		ConvertUtils.register(dateConverter, Date.class);
		System.out.println("Date converter registered.");
		// Populate the NEWS object with form data
		try {
			BeanUtils.populate(form, req.getParameterMap());

			// Chuyển đổi ngày tháng thủ công sau khi populate
			String postedDateStr = req.getParameter("postedDate"); // Lấy tham số ngày tháng từ request
			if (postedDateStr != null && !postedDateStr.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày tháng
				try {
					Date postedDate = sdf.parse(postedDateStr); // Chuyển chuỗi thành Date
					form.setPostedDate(postedDate); // Gán vào đối tượng NEWS
				} catch (Exception e) {
					e.printStackTrace();
					req.setAttribute("error", "Invalid date format.");
				}
			}

		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			req.setAttribute("error", "Unable to populate form data.");
		}

		String path = req.getServletPath();
		try {
			if (path.contains("edit")) {
				String id = req.getPathInfo() != null ? req.getPathInfo().substring(1) : null;
				if (id != null) {
					form = dao.selectByid(id);
					if (form == null) {
						req.setAttribute("error", "User not found.");
					}
				} else {
					req.setAttribute("error", "Invalid user ID.");
				}
			} else if (path.contains("create")) {
				dao.insert(form);
				form = new NEWS(); // Reset form
				req.setAttribute("message", "User added successfully.");
			} else if (path.contains("update")) {
				// Lấy dữ liệu từ request
				String id = req.getParameter("id");
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				String image = req.getParameter("image");

				// Chuyển đổi chuỗi thành Date cho trường PostedDate
				Date postedDate = (Date) ConvertUtils.convert(req.getParameter("postedDate"), Date.class);

				String author = req.getParameter("author");

				// Chuyển đổi chuỗi thành số nguyên cho ViewCount
				int viewCount = Integer.parseInt(req.getParameter("viewCount"));

				String categoryId = req.getParameter("categoryId");

				// Chuyển đổi chuỗi thành boolean cho Home
				boolean home = Boolean.parseBoolean(req.getParameter("home"));

				// Cập nhật vào cơ sở dữ liệu
				NEWS NEW = new NEWS(id, title, content, image, postedDate, author, viewCount, categoryId, home);
				dao.update(NEW); // Gọi phương thức update trong dao

			} else if (path.contains("delete")) {
				String id = req.getParameter("id");
				if (id != null) {
					dao.delete(id);

				} else {
					req.setAttribute("error", "Invalid user ID.");
				}
				form = new NEWS(); // Reset form
			} else if ("search".equals(req.getParameter("action"))) {
				String searchId = req.getParameter("searchId");
				form = dao.selectByid(searchId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "An error occurred during the operation.");
		}

		// Truyền dữ liệu đến view
		req.setAttribute("user", form);
		List<NEWS> list = dao.selectAll();
		List<NEWS> listdc = new ArrayList<>();
		for (NEWS user : list) {
			user.setTitle(user.getTitle().replace("\n", "\\n"));
			user.setContent(user.getContent().replace("\n", "\\n"));
			listdc.add(user);
		}
		req.setAttribute("list", listdc);
		req.getRequestDispatcher(VIEW_QLTT).forward(req, resp);
	}
}
