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
import poly.entity.NEWS;
import poly.dao.NEWSDao;

@WebServlet({ "/NEWS/index", 
              "/NEWS/edit/*", 
              "/NEWS/create", 
              "/NEWS/update",
              "/NEWS/delete", 
              "/NEWS/reset"})

public class NEWSServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW_INDEX = "/Views/index.jsp";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NEWS form = new NEWS();
        NEWSDao dao = new NEWSDao();

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
                form = new NEWS(); // Reset form
            } else if (path.contains("update")) {
                dao.update(form);
            } else if (path.contains("delete")) {
                dao.delete(form.getId());
                form = new NEWS(); // Reset form
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred during the operation.");
        }

        req.setAttribute("item", form);
        List<NEWS> list = dao.selectAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher(VIEW_INDEX).forward(req, resp);
    }
}
