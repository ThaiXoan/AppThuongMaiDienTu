package it.dut.thaixoan.applazada.views.trangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.List;

import it.dut.thaixoan.applazada.R;
import it.dut.thaixoan.applazada.adapter.ExpanableAdapter;
import it.dut.thaixoan.applazada.adapter.ViewPagerAdapter;
import it.dut.thaixoan.applazada.models.object_class.LoaiSanPham;
import it.dut.thaixoan.applazada.presenters.trangchu.xulymenu.PresenterLogicXuLyMenu;
import it.dut.thaixoan.applazada.views.DangNhap.DangNhapActivity;

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu{

    private ExpandableListView mExpandableListView;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        addControls();
    }

    private void addControls() {

        Toolbar mToolbarTrangchu = findViewById(R.id.toolbar_trangchu);
        TabLayout mTabLayoutTrangChu = findViewById(R.id.tab_layout_trangchu);
        ViewPager mViewPagerTrangChu = findViewById(R.id.view_pager_trangchu);
        DrawerLayout mDrawerLayoutTrangChu = findViewById(R.id.drawer_layout_trangchu);
        mExpandableListView = findViewById(R.id.expanable_menu);

        mToolbarTrangchu.setTitle(R.string.title_empty);
        setSupportActionBar(mToolbarTrangchu);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayoutTrangChu, R.string.open, R.string.close);
        mDrawerLayoutTrangChu.addDrawerListener(mDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle.syncState();

        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerTrangChu.setAdapter(mAdapter);
        mTabLayoutTrangChu.setupWithViewPager(mViewPagerTrangChu);

        PresenterLogicXuLyMenu logicXuLyMenu = new PresenterLogicXuLyMenu(this);
        logicXuLyMenu.layDanhSachMenu();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        switch (id) {
            case R.id.item_login: {
                Intent intentDangNhap = new Intent(this, DangNhapActivity.class);
                startActivity(intentDangNhap);
            }
        }
        return true;
    }

    @Override
    public void hienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhams) {

        ExpanableAdapter adapter = new ExpanableAdapter(this, loaiSanPhams);
        mExpandableListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
