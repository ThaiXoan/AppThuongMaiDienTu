package it.dut.thaixoan.applazada.views.trangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import it.dut.thaixoan.applazada.R;
import it.dut.thaixoan.applazada.adapter.ExpanableAdapter;
import it.dut.thaixoan.applazada.adapter.ViewPagerAdapter;
import it.dut.thaixoan.applazada.models.dangnhap_dangky.ModelDangNhap;
import it.dut.thaixoan.applazada.models.object_class.LoaiSanPham;
import it.dut.thaixoan.applazada.presenters.trangchu.xulymenu.PresenterLogicXuLyMenu;
import it.dut.thaixoan.applazada.views.dangnhap_dangky.DangNhapActivity;

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu {

    // public static final String SERVER_NAME = "http://192.168.0.110:8080/";
    public static final String SERVER_NAME = "http://10.0.3.2:8080/";
    private Toolbar mToolbarTrangchu;
    private TabLayout mTabLayoutTrangChu;
    private ViewPager mViewPagerTrangChu;
    private DrawerLayout mDrawerLayoutTrangChu;
    private ExpandableListView mExpandableListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private PresenterLogicXuLyMenu logicXuLyMenu;
    private String tenNguoiDung = "";
    private AccessToken accessToken;
    private Menu menu;
    private MenuItem itemDangNhap;
    private ModelDangNhap modelDangNhap;
    private GoogleSignInAccount result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_trangchu);
        addControls();
        addEvents();
    }

    private void addControls() {

        mToolbarTrangchu = findViewById(R.id.toolbar_trangchu);
        mTabLayoutTrangChu = findViewById(R.id.tab_layout_trangchu);
        mViewPagerTrangChu = findViewById(R.id.view_pager_trangchu);
        mDrawerLayoutTrangChu = findViewById(R.id.drawer_layout_trangchu);
        mExpandableListView = findViewById(R.id.expanable_menu);

    }

    private void addEvents() {
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


        logicXuLyMenu = new PresenterLogicXuLyMenu(this);

        modelDangNhap = new ModelDangNhap();

        logicXuLyMenu.getTokenFacebook();
        logicXuLyMenu.layDanhSachMenu();

    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        this.menu = menu;

        itemDangNhap = menu.findItem(R.id.item_login);
        MenuItem itemDangXuat = menu.findItem(R.id.item_logout);

        accessToken = logicXuLyMenu.getTokenFacebook();
        result = modelDangNhap.getInfoLoginGoogle(getApplicationContext());

        if (accessToken != null) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tenNguoiDung = object.getString("name");
                        itemDangNhap.setTitle(tenNguoiDung);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            Bundle parameter = new Bundle();
            parameter.putString("fields", "name");
            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();
        }

        if (result != null) {
            tenNguoiDung = result.getDisplayName();
            itemDangNhap.setTitle(tenNguoiDung);
        }

        if (accessToken != null || result != null) {
            itemDangXuat.setVisible(true);
        }

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
                if (accessToken == null && result == null) {
                    Intent intentDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(intentDangNhap);
                }
                break;
            }
            case R.id.item_logout: {
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                if (result != null) {
                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
                    GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);
                    mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            menu.clear();
                            onCreateOptionsMenu(menu);
                        }
                    });
                }
                break;
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
