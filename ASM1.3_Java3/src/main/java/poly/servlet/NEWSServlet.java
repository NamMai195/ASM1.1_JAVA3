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
import poly.entity.CATEGORIES;
import poly.entity.NEWS;
import poly.dao.CATEGORIESDao;
import poly.dao.NEWSDao;

@WebServlet( "/NEWS/index")

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

        req.setAttribute("item", form);
        List<NEWS> list = dao.selectAll();
        req.setAttribute("list", list);
        req.setAttribute("loai", form);
        CATEGORIESDao loaidao=new CATEGORIESDao();
        List<CATEGORIES> listloai = loaidao.selectAll();
        req.setAttribute("listloai", listloai);
        req.getRequestDispatcher(VIEW_INDEX).forward(req, resp);
    }
}
