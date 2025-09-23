package iuh.fit.bai06.servlet;

import iuh.fit.bai06.model.*;
import iuh.fit.bai06.dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/danh-sach-tin-tuc")
public class DanhSachTinTucServlet extends HttpServlet {
    private DanhSachTinTucQuanLy dsDAO;

    @Override
    public void init() throws ServletException {
        Connection conn = (Connection) getServletContext().getAttribute("DB_CONN");
        dsDAO = new DanhSachTinTucQuanLy(conn);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String madmStr = req.getParameter("madm");
        int madm = madmStr != null ? Integer.parseInt(madmStr) : 1;
        List<TinTuc> list = dsDAO.getTinTucByDanhMuc(madm);
        req.setAttribute("danhSachTinTuc", list);
        req.setAttribute("danhMucList", dsDAO.getAllDanhMuc());
        req.getRequestDispatcher("DanhSachTinTuc.jsp").forward(req, resp);
    }
}