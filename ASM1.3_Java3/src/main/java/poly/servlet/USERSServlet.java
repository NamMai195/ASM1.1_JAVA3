package poly.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import poly.dao.USERSDao;
import poly.entity.USERS;

@WebServlet({ "/USERS/QLNguoiDung",
              "/USERS/edit/*", 
              "/USERS/create", 
              "/USERS/update", 
              "/USERS/delete",
              "/USERS/dangky" })
public class USERSServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW_QLND = "/Views/QLNguoiDung.jsp";
    private static final String VIEW_INDEX = "/Views/index.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        DateConverter dateConverter = new DateConverter();
        dateConverter.setPattern("yyyy-MM-dd");
        ConvertUtils.register(dateConverter, Date.class);
        System.out.println("Date converter registered.");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        USERS form = new USERS();
        USERSDao dao = new USERSDao();

        // Lấy dữ liệu từ form
        try {
            BeanUtils.populate(form, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            req.setAttribute("error", "Unable to populate form data.");
            return;
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
                req.setAttribute("message", "User added successfully.");
            } else if (path.contains("update")) {
                String id = req.getParameter("id");
                String fullname = req.getParameter("fullname");
                String birthdayStr = req.getParameter("birthday");
                Date birthday = (Date) ConvertUtils.convert(birthdayStr, Date.class); // Chuyển đổi chuỗi thành Date
                boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
                String mobile = req.getParameter("mobile");
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                boolean role = Boolean.parseBoolean(req.getParameter("role"));

                // Cập nhật vào cơ sở dữ liệu
                USERS user = new USERS(id, password, fullname, birthday, gender, mobile, email, role);
                dao.update(user);
                req.setAttribute("message", "User updated successfully.");
            } else if (path.contains("delete")) {
                String id = req.getParameter("id");
                if (id != null) {
                    dao.delete(id);
                    req.setAttribute("message", "User deleted successfully.");
                } else {
                    req.setAttribute("error", "Invalid user ID.");
                }
            }else if (path.contains("dangky")) {
                dao.insert(form);
                req.getRequestDispatcher(VIEW_INDEX).forward(req, resp);
            } else if ("search".equals(req.getParameter("action"))) {
                String searchId = req.getParameter("searchId");
                form = dao.selectByid(searchId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred during the operation: " + e.getMessage());
        }

        // Reset form sau khi thêm, xóa hoặc cập nhật
        if (path.contains("create") || path.contains("delete") || path.contains("update")) {
            form = new USERS(); // Reset form
        }

        req.setAttribute("user", form);
        List<USERS> list = dao.selectAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher(VIEW_QLND).forward(req, resp);
    }

}