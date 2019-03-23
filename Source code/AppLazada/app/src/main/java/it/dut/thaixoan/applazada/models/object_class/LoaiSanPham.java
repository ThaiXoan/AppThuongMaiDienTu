package it.dut.thaixoan.applazada.models.object_class;

import java.util.List;

public class LoaiSanPham {
    private int maLoaiSanPham;
    private int maLoaiCha;
    private String tenLoaiSanPham;
    List<LoaiSanPham> listCon;

    public int getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public int getMaLoaiCha() {
        return maLoaiCha;
    }

    public void setMaLoaiCha(int maLoaiCha) {
        this.maLoaiCha = maLoaiCha;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public List<LoaiSanPham> getListCon() {
        return listCon;
    }

    public void setListCon(List<LoaiSanPham> listCon) {
        this.listCon = listCon;
    }
}
