package iuh.fit.bai06.servlet;

import iuh.fit.bai06.dao.DanhSachTinTucQuanLy;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/QuanLyFormServlet")
public class QuanLyFormServlet extends HttpServlet {
    @Resource(name = "jdbc/quanlydanhmuc")
    private DataSource dataSource;
    private DanhSachTinTucQuanLy dsDAO;


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            dsDAO = new DanhSachTinTucQuanLy(dataSource);

        } catch (Exception e) {
            throw new RuntimeException("Error initializing DAOs", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int madm = Integer.parseInt(req.getParameter("madm"));
        req.setAttribute("danhSachTinTuc", dsDAO.getTinTucByDanhMuc(madm));
        req.setAttribute("danhMucList", dsDAO.getAllDanhMuc());
        req.getRequestDispatcher("QuanLyForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int matt = Integer.parseInt(req.getParameter("matt"));
        int madm = Integer.parseInt(req.getParameter("madm"));
        dsDAO.deleteTinTuc(matt);
        resp.sendRedirect("QuanLyFormServlet?madm=" + madm);
    }
}