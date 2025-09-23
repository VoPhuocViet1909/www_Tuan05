package iuh.fit.bai06.model;

public class DanhMuc {
    private int maDM;
    private String tenDM;
    private String nguoiQuanLy;
    private String ghiChu;

    public DanhMuc(int maDM, String tenDM, String nguoiQuanLy, String ghiChu) {
        this.maDM = maDM;
        this.tenDM = tenDM;
        this.nguoiQuanLy = nguoiQuanLy;
        this.ghiChu = ghiChu;
    }

    public int getMaDM() {
        return maDM;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }
    public String getNguoiQuanLy() {
        return nguoiQuanLy;
    }
    public void setNguoiQuanLy(String nguoiQuanLy) {
        this.nguoiQuanLy = nguoiQuanLy;
    }
    public String getGhiChu() {
        return ghiChu;
    }
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}