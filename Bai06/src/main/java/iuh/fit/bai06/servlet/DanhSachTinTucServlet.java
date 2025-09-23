package iuh.fit.bai06.servlet;

import iuh.fit.bai06.model.*;
import iuh.fit.bai06.dao.*;
import iuh.fit.bai06.util.DBUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/danh-sach-tin-tuc")
public class DanhSachTinTucServlet extends HttpServlet {


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
        String madmStr = req.getParameter("madm");
        int madm = madmStr != null ? Integer.parseInt(madmStr) : 1;
        List<TinTuc> list = dsDAO.getTinTucByDanhMuc(madm);
        req.setAttribute("danhSachTinTuc", list);
        req.setAttribute("danhMucList", dsDAO.getAllDanhMuc());
        req.getRequestDispatcher("DanhSachTinTuc.jsp").forward(req, resp);
    }
}