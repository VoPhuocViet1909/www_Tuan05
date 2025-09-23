package iuh.fit.employeejsp.servlet;

import iuh.fit.employeejsp.dao.DepartmentDAO;
import iuh.fit.employeejsp.model.Department;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {

    @Resource(name = "jdbc/baitap5")
    private DataSource dataSource;

    private DepartmentDAO departmentDAO;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            departmentDAO = new DepartmentDAO(dataSource);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing DepartmentDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "list": {
                    List<Department> departments = departmentDAO.getAll();
                    req.setAttribute("departments", departments);
                    req.getRequestDispatcher("department-list.jsp").forward(req, resp);
                    break;
                }
                case "new": {
                    req.getRequestDispatcher("department-form.jsp").forward(req, resp);
                    break;
                }
                case "edit": {
                    int id = Integer.parseInt(req.getParameter("id"));
                    Department dep = departmentDAO.getById(id);
                    req.setAttribute("department", dep);
                    req.getRequestDispatcher("department-form.jsp").forward(req, resp);
                    break;
                }
                case "delete": {
                    int id = Integer.parseInt(req.getParameter("id"));
                    departmentDAO.delete(id);
                    resp.sendRedirect("departments");
                    break;
                }
                case "search": {
                    String keyword = req.getParameter("keyword");
                    List<Department> departments = departmentDAO.getAll();
                    if (keyword != null && !keyword.trim().isEmpty()) {
                        String kw = keyword.trim().toLowerCase();
                        departments = departments.stream()
                                .filter(d -> d.getDepartment_name().toLowerCase().contains(kw))
                                .collect(Collectors.toList());
                    }
                    req.setAttribute("departments", departments);
                    req.setAttribute("keyword", keyword);
                    req.getRequestDispatcher("department-list.jsp").forward(req, resp);
                    break;
                }
                default: {
                    resp.sendRedirect("departments?action=list");
                }
            }
        } catch (Exception e) {
            throw new ServletException("Error in DepartmentServlet doGet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String idParam = req.getParameter("id");
            int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;
            String name = req.getParameter("name");

            Department dep = new Department(id, name);

            if (id > 0) {
                departmentDAO.update(dep);
            } else {
                departmentDAO.save(dep);
            }

            resp.sendRedirect("departments");
        } catch (Exception e) {
            throw new ServletException("Error in DepartmentServlet doPost", e);
        }
    }
}