package iuh.fit.bai06.dao;

import iuh.fit.bai06.model.TinTuc;
import java.sql.*;
import java.util.*;

public class DanhSachTinTucQuanLy {
    private Connection conn;

    public DanhSachTinTucQuanLy(Connection conn) {
        this.conn = conn;
    }

    // Lấy danh sách tin tức theo danh mục
    public List<TinTuc> getTinTucByDanhMuc(int maDM) {
        List<TinTuc> list = new ArrayList<>();
        String sql = "SELECT * FROM TINTUC WHERE MADM=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maDM);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TinTuc(
                        rs.getInt("MATT"),
                        rs.getString("TIEUDE"),
                        rs.getString("NOIDUNGTT"),
                        rs.getString("LIENKET"),
                        rs.getInt("MADM")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // Thêm mới tin tức
    public void addTinTuc(TinTuc tin) {
        String sql = "INSERT INTO TINTUC(TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tin.getTieuDe());
            ps.setString(2, tin.getNoiDungTT());
            ps.setString(3, tin.getLienKet());
            ps.setInt(4, tin.getMaDM());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Xóa tin tức
    public void deleteTinTuc(int maTT) {
        String sql = "DELETE FROM TINTUC WHERE MATT=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maTT);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // Lấy tất cả danh mục (phục vụ form)
    public List<Map<String, Object>> getAllDanhMuc() {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT * FROM DANHMUC";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> m = new HashMap<>();
                m.put("maDM", rs.getInt("MADM"));
                m.put("tenDM", rs.getString("TENDANHMUC"));
                list.add(m);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}