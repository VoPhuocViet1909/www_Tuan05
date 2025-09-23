package iuh.fit.bai06.servlet;

import iuh.fit.bai06.dao.DanhSachTinTucQuanLy;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/QuanLyFormServlet")
public class QuanLyFormServlet extends HttpServlet {
    private DanhSachTinTucQuanLy dsDAO;

    @Override
    public void init() throws ServletException {
        Connection conn = (Connection) getServletContext().getAttribute("DB_CONN");
        dsDAO = new DanhSachTinTucQuanLy(conn);
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