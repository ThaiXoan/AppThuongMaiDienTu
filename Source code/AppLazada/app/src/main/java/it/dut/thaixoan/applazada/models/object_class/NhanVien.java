package it.dut.thaixoan.applazada.models.object_class;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private int maNhanVien;
    private String tenNhanVien;
    private String tenDangNhap;
    private String matKhau;
    private String diaChi;
    private String ngaySinh;
    private String soDienThoai;
    private int gioiTinh;
    private int maLoaiNhanVien;
    private String nhanKhuyenMai;


    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getMaLoaiNhanVien() {
        return maLoaiNhanVien;
    }

    public void setMaLoaiNhanVien(int maLoaiNhanVien) {
        this.maLoaiNhanVien = maLoaiNhanVien;
    }

    public String getNhanKhuyenMai() {
        return nhanKhuyenMai;
    }

    public void setNhanKhuyenMai(String nhanKhuyenMai) {
        this.nhanKhuyenMai = nhanKhuyenMai;
    }
}
