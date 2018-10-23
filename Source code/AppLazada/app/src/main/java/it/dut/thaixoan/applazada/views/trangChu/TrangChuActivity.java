package it.dut.thaixoan.applazada.views.trangChu;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import it.dut.thaixoan.applazada.R;
import it.dut.thaixoan.applazada.adapter.ViewPagerAdapter;

public class TrangChuActivity extends AppCompatActivity {

    private Toolbar mToolbarTrangchu;
    private TabLayout mTabLayoutTrangChu;
    private ViewPager mViewPagerTrangChu;
    private ViewPagerAdapter mAdapter;
    private DrawerLayout mDrawerLayoutTrangChu;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        addControls();
    }

    private void addControls() {

        mToolbarTrangchu = findViewById(R.id.toolbar_trangchu);
        mTabLayoutTrangChu = findViewById(R.id.tablayout_trangchu);
        mViewPagerTrangChu = findViewById(R.id.viewpager_trangchu);
        mDrawerLayoutTrangChu = findViewById(R.id.drawerlayout_trangchu);

        mToolbarTrangchu.setTitle(R.string.title_empty);
        setSupportActionBar(mToolbarTrangchu);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayoutTrangChu, R.string.open, R.string.close);
        mDrawerLayoutTrangChu.addDrawerListener(mDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle.syncState();

        mAdapter= new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerTrangChu.setAdapter(mAdapter);
        mTabLayoutTrangChu.setupWithViewPager(mViewPagerTrangChu);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }
}
