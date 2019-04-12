package it.dut.thaixoan.applazada.models.dangnhap_dangky;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import it.dut.thaixoan.applazada.connectInternet.DownloadJSON;
import it.dut.thaixoan.applazada.models.object_class.NhanVien;
import it.dut.thaixoan.applazada.views.trangChu.TrangChuActivity;

public class ModelDangKy {
    public Boolean dangKyThanhVien(NhanVien nhanVien){

        String duongDan = TrangChuActivity.SERVER_NAME + "LazadaWebServer/loaisanpham.php";

        List<HashMap<String, String>> attribute = new ArrayList<>();

        HashMap<String, String> hsMethod = new HashMap<>();
        hsMethod.put("medthod", "dangKyThanhVien");

        HashMap<String, String> hsTenNhanVien = new HashMap<>();
        hsTenNhanVien.put("tenNhanVien", nhanVien.getTenNhanVien());

        HashMap<String, String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("tenDangNhap", nhanVien.getTenDangNhap());

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matKhau", nhanVien.getMatKhau());

        HashMap<String, String> hsMaLoaiNhanVien = new HashMap<>();
        hsMaLoaiNhanVien.put("maLoaiNhanVien", String.valueOf(nhanVien.getMaLoaiNhanVien()));

        HashMap<String, String> hsNhanKhuyenMai = new HashMap<>();
        hsNhanKhuyenMai.put("nhanKhuyenMai", nhanVien.getNhanKhuyenMai());

        attribute.add(hsMethod);
        attribute.add(hsTenNhanVien);
        attribute.add(hsTenDangNhap);
        attribute.add(hsMatKhau);
        attribute.add(hsMaLoaiNhanVien);
        attribute.add(hsNhanKhuyenMai);

        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attribute);
        downloadJSON.execute();

        try {
            String duLieu = downloadJSON.get();
            Log.d("SSSSSSSSSSSS", duLieu);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }
}
