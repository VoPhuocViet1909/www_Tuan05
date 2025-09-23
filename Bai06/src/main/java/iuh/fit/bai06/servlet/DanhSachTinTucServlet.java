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
    private  DanhMucDAO dmDAO;


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            dsDAO = new DanhSachTinTucQuanLy(dataSource);
            dmDAO = new DanhMucDAO(dataSource);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing DAOs", e);
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
                    // Lấy danh sách tin tức theo danh mục
                    String madmStr = req.getParameter("madm");
                    int madm = madmStr != null ? Integer.parseInt(madmStr) : 1;
                    List<TinTuc> list = dsDAO.getTinTucByDanhMuc(madm);
                    req.setAttribute("danhSachTinTuc", list);
                    req.setAttribute("danhMucList", dmDAO.getAll());
                    req.getRequestDispatcher("DanhSachTinTuc.jsp").forward(req, resp);
                    break;
                }
                case "new": {
                    req.setAttribute("danhMucList", dmDAO.getAll());
                    req.getRequestDispatcher("TinTucForm.jsp").forward(req, resp);
                    break;
                }
                case "delete": {
                    try {
                        // Lấy mã tin tức từ request
                        int matt = Integer.parseInt(req.getParameter("matt"));

                        // Gọi DAO để xóa
                        dsDAO.deleteTinTuc(matt);

                        // Sau khi xóa xong, quay lại danh sách
                        resp.sendRedirect("danh-sach-tin-tuc");
                    } catch (Exception e) {
                        e.printStackTrace();
                        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Không thể xóa tin tức");
                    }
                    break;
                }
//                case "edit": {
//                    int matt = Integer.parseInt(req.getParameter("matt"));
//                    TinTuc tt = dsDAO.getTinTucById(matt); // bạn cần viết getTinTucById
//                    req.setAttribute("tinTuc", tt);
//                    req.setAttribute("danhMucList", dsDAO.getAllDanhMuc());
//                    req.getRequestDispatcher("TinTucForm.jsp").forward(req, resp);
//                    break;
//                }
                // Thêm các case khác nếu cần (delete, view, ...)
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý action");
        }
    }
}