package iuh.fit.bai06.dao;

import iuh.fit.bai06.model.DanhMuc;
import iuh.fit.bai06.model.TinTuc;
import iuh.fit.bai06.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class DanhSachTinTucQuanLy {
    private final DBUtil dbUtil;

    public DanhSachTinTucQuanLy(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    // Lấy danh sách tin tức theo danh mục
    public List<TinTuc> getTinTucByDanhMuc(int maDM) {
        List<TinTuc> list = new ArrayList<>();
        String sql = "SELECT * FROM TINTUC WHERE MADM=?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDM);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new TinTuc(
                            rs.getInt("MATT"),
                            rs.getString("TIEUDE"),
                            rs.getString("NOIDUNGTT"),
                            rs.getString("LIENKET"),
                            rs.getInt("MADM")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm mới tin tức
    public void addTinTuc(TinTuc tin) {
        String sql = "INSERT INTO TINTUC(TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tin.getTieuDe());
            ps.setString(2, tin.getNoiDungTT());
            ps.setString(3, tin.getLienKet());
            ps.setInt(4, tin.getMaDM());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa tin tức
    public void deleteTinTuc(int maTT) {
        String sql = "DELETE FROM TINTUC WHERE MATT=?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTT);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy tất cả danh mục (phục vụ form)
// Lấy tất cả danh mục
    public List<DanhMuc> getAllDanhMuc() {
        List<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT * FROM DANHMUC";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new DanhMuc(
                        rs.getInt("MADM"),
                        rs.getString("TENDANHMUC")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}