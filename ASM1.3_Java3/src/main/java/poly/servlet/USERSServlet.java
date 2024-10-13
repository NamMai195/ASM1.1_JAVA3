package poly.servlet;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

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
		    "/USERS/reset"})

public class USERSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String VIEW_QLND = "/Views/QLNguoiDung.jsp";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	USERS form = new USERS();
    	USERSDao dao = new USERSDao();

        // Populate the NEWS object with form data
        try {
            BeanUtils.populate(form, req.getParameterMap());
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
                } else {
                    req.setAttribute("error", "Invalid news ID.");
                }
            } else if (path.contains("create")) {
                dao.insert(form);
                form = new USERS(); // Reset form
            } else if (path.contains("update")) {
                dao.update(form);
            } else if (path.contains("delete")) {
                dao.delete(form.getId());
                form = new USERS(); // Reset form
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred during the operation.");
        }

        req.setAttribute("user", form);
        List<USERS> list = dao.selectAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher(VIEW_QLND).forward(req, resp);
    }
}
