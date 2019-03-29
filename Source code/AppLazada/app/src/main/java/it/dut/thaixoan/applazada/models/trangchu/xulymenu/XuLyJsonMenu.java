package it.dut.thaixoan.applazada.models.trangchu.xulymenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import it.dut.thaixoan.applazada.connectInternet.DownloadJSON;
import it.dut.thaixoan.applazada.models.object_class.LoaiSanPham;

public class XuLyJsonMenu {
    public List<LoaiSanPham> parseJsonMenu(String duLieuJson){
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(duLieuJson);
            JSONArray loaiSanPhamArray = jsonObject.getJSONArray("LOAISANPHAM");

            int count = loaiSanPhamArray.length();
            for (int i = 0; i < count; i++){
                JSONObject values = loaiSanPhamArray.getJSONObject(i);
                LoaiSanPham loaiSanPham = new LoaiSanPham();
                loaiSanPham.setMaLoaiSanPham(Integer.parseInt(values.getString("MALOAISP")));
                loaiSanPham.setMaLoaiCha(Integer.parseInt(values.getString("MALOAI_CHA")));
                loaiSanPham.setTenLoaiSanPham(values.getString("TENLOAISP"));

                loaiSanPhamList.add(loaiSanPham);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  loaiSanPhamList;
    }

    public List<LoaiSanPham> layLoaiSanPhamTheoMaLoaiSanPham(int maLoaiSanPham){

        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attribute = new ArrayList<>();
        String dataJson = "";

        String duongDan = "http://192.168.0.104:8080/LazadaWebServer/loaisanpham.php";
        HashMap<String, String> hsMaloaiCha = new HashMap<>();
        hsMaloaiCha.put("maloaicha", String.valueOf(maLoaiSanPham));
        attribute.add(hsMaloaiCha);

        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attribute);
        downloadJSON.execute();

        try {
            dataJson = downloadJSON.get();
            XuLyJsonMenu xuLyJsonMenu = new XuLyJsonMenu();
            loaiSanPhamList = xuLyJsonMenu.parseJsonMenu(dataJson);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;
    }
}
