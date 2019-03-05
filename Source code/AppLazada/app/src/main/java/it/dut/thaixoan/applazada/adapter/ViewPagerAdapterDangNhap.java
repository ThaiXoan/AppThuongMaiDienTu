package it.dut.thaixoan.applazada.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import it.dut.thaixoan.applazada.views.DangNhap.fragment.FragmentDangKy;
import it.dut.thaixoan.applazada.views.DangNhap.fragment.FragmentDangNhap;

public class ViewPagerAdapterDangNhap extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();
    private String mListTabsTitle[] = {"Đăng nhập", "Đăng ký"};

    public ViewPagerAdapterDangNhap(FragmentManager fm) {
        super(fm);
        mFragmentList.add(new FragmentDangNhap());
        mFragmentList.add(new FragmentDangKy());
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
