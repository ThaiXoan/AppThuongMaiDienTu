package it.dut.thaixoan.applazada.views.DangNhap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import it.dut.thaixoan.applazada.R;
import it.dut.thaixoan.applazada.adapter.ViewPagerAdapterDangNhap;

public class DangNhapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        addControls();
    }

    private void addControls() {
        TabLayout mTabLayoutDangNhap = findViewById(R.id.tablayout_dangnhap);
        ViewPager mViewPagerDangNhap = findViewById(R.id.viewpager_dangnhap);
        Toolbar mToolbarDangNhap = findViewById(R.id.toolbar_dangnhap);

        setSupportActionBar(mToolbarDangNhap);

        ViewPagerAdapterDangNhap mViewPagerAdapterDangNhap = new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        mViewPagerDangNhap.setAdapter(mViewPagerAdapterDangNhap);
        mTabLayoutDangNhap.setupWithViewPager(mViewPagerDangNhap);
    }
}
