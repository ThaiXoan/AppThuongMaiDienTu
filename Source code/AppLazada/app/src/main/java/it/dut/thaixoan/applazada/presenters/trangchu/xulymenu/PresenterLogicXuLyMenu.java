package it.dut.thaixoan.applazada.presenters.trangchu.xulymenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import it.dut.thaixoan.applazada.connectInternet.DownloadJSON;
import it.dut.thaixoan.applazada.models.object_class.LoaiSanPham;
import it.dut.thaixoan.applazada.models.trangchu.xulymenu.XuLyJsonMenu;
import it.dut.thaixoan.applazada.views.trangChu.ViewXuLyMenu;

public class PresenterLogicXuLyMenu implements PresenterInterfaceXuLyMenu {

    private ViewXuLyMenu viewXuLyMenu;

    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu) {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void layDanhSachMenu() {
        List<LoaiSanPham> loaiSanPhamList;
        String dataJson = "";
        List<HashMap<String, String>> attribute = new ArrayList<>();

        // lấy dữ liệu bằng phương thức GET
        // String duongDan = "http://192.168.0.104:8080/LazadaWebServer/loaisanpham.php?maloaicha=0";
        // DownloadJSON downloadJSON = new DownloadJSON(duongDan);


        // lấy dữ liệu bằng phương thức POST
        String duongDan = "http://192.168.0.104:8080/LazadaWebServer/loaisanpham.php";
        HashMap<String, String> hsMaloaiCha = new HashMap<>();
        hsMaloaiCha.put("maloaicha", "0");
        attribute.add(hsMaloaiCha);
        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attribute);
        downloadJSON.execute();

        try {
            dataJson = downloadJSON.get();
            XuLyJsonMenu xuLyJsonMenu = new XuLyJsonMenu();
            loaiSanPhamList = xuLyJsonMenu.parseJsonMenu(dataJson);
            viewXuLyMenu.hienThiDanhSachMenu(loaiSanPhamList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
