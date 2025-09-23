package iuh.fit.bai06.servlet;

import iuh.fit.bai06.dao.DanhSachTinTucQuanLy;
import iuh.fit.bai06.model.TinTuc;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/TinTucFormServlet")
public class TinTucFormServlet extends HttpServlet {
    private DanhSachTinTucQuanLy dsDAO;

    @Override
    public void init() throws ServletException {
        Connection conn = (Connection) getServletContext().getAttribute("DB_CONN");
        dsDAO = new DanhSachTinTucQuanLy(conn);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String tieuDe = req.getParameter("tieuDe");
        String noiDungTT = req.getParameter("noiDungTT");
        String lienKet = req.getParameter("lienKet");
        int madm = Integer.parseInt(req.getParameter("madm"));
        TinTuc tin = new TinTuc(0, tieuDe, noiDungTT, lienKet, madm);
        dsDAO.addTinTuc(tin);
        resp.sendRedirect("danh-sach-tin-tuc?madm=" + madm);
    }
}