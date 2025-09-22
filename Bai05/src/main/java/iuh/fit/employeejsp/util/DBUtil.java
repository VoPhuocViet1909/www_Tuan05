package iuh.fit.employeejsp.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class DBUtil {

    private DataSource dataSource;

    // Constructor nhận vào DataSource để thiết lập kết nối
    public DBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Phương thức trả về một kết nối đến cơ sở dữ liệu
    public Connection getConnection() {
        Connection conn = null;
        try {
            // Lấy kết nối từ DataSource
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            // Xử lý ngoại lệ khi không thể lấy kết nối
            throw new RuntimeException("Error while getting connection", e);
        }
        return conn;
    }
}
