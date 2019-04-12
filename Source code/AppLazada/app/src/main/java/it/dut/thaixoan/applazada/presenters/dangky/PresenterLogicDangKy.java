package it.dut.thaixoan.applazada.presenters.dangky;

import it.dut.thaixoan.applazada.models.dangnhap_dangky.ModelDangKy;
import it.dut.thaixoan.applazada.models.object_class.NhanVien;
import it.dut.thaixoan.applazada.views.dangnhap_dangky.ViewDangKy;

public class PresenterLogicDangKy implements PresenterInterfaceDangKy {
    private ModelDangKy modelDangKy;

    public PresenterLogicDangKy(ViewDangKy viewDangKy){
        modelDangKy = new ModelDangKy();
    }
    @Override
    public void thucHienDangKy(NhanVien nhanVien) {
        modelDangKy.dangKyThanhVien(nhanVien);
    }
}
