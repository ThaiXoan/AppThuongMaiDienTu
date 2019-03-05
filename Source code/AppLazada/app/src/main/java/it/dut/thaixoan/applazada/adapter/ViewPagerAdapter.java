package it.dut.thaixoan.applazada.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import it.dut.thaixoan.applazada.views.trangChu.fragment.FragmentChuongTrinhKhuyenMai;
import it.dut.thaixoan.applazada.views.trangChu.fragment.FragmentDienTu;
import it.dut.thaixoan.applazada.views.trangChu.fragment.FragmentLamDep;
import it.dut.thaixoan.applazada.views.trangChu.fragment.FragmentMeVaBe;
import it.dut.thaixoan.applazada.views.trangChu.fragment.FragmentNhaCuaVaDoiSong;
import it.dut.thaixoan.applazada.views.trangChu.fragment.FragmentNoiBat;
import it.dut.thaixoan.applazada.views.trangChu.fragment.FragmentTheThaoVaDuLich;
import it.dut.thaixoan.applazada.views.trangChu.fragment.FragmentThoiTrang;
import it.dut.thaixoan.applazada.views.trangChu.fragment.FragmentThuongHieu;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();
    private String mListTabsTitle[] = {
            "Nổi bật", "Chương trình khuyến mại", "Điện tử", "Nhà cửa & đời sống", "Mẹ và bé",
            "Làm đẹp", "Thời trang", "Thể thao & du lịch", "Thương hiệu"
    };

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList.add(new FragmentNoiBat());
        mFragmentList.add(new FragmentChuongTrinhKhuyenMai());
        mFragmentList.add(new FragmentDienTu());
        mFragmentList.add(new FragmentNhaCuaVaDoiSong());
        mFragmentList.add(new FragmentMeVaBe());
        mFragmentList.add(new FragmentLamDep());
        mFragmentList.add(new FragmentThoiTrang());
        mFragmentList.add(new FragmentTheThaoVaDuLich());
        mFragmentList.add(new FragmentThuongHieu());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mListTabsTitle[position];
    }
}
