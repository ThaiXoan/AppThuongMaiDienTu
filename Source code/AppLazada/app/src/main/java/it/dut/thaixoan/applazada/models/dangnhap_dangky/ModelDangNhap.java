package it.dut.thaixoan.applazada.models.dangnhap_dangky;

import android.content.Context;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import it.dut.thaixoan.applazada.connectInternet.DownloadJSON;
import it.dut.thaixoan.applazada.views.trangChu.TrangChuActivity;

public class ModelDangNhap {

    private List<HashMap<String, String>> attribute = new ArrayList<>();

    public boolean kiemTraDangNhap(String tenDangNhap, String matKhau) {

        String duongDan = TrangChuActivity.SERVER_NAME + "LazadaWebServer/loaisanpham.php";

        boolean checkLogin = false;
        HashMap<String, String> hsMethod = new HashMap<>();
        hsMethod.put("medthod", "kiemTraDangNhap");

        HashMap<String, String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("tenDangNhap", tenDangNhap);

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matKhau", matKhau);

        attribute.add(hsMethod);
        attribute.add(hsTenDangNhap);
        attribute.add(hsMatKhau);

        DownloadJSON downloadJSON = new DownloadJSON(duongDan, attribute);
        downloadJSON.execute();

        try {
            String duLieu = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(duLieu);
            String ketQua = jsonObject.getString("ketQua");
            checkLogin = "true".equals(ketQua);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return checkLogin;
    }

    public AccessToken layCurrenTokenFacebook() {
        return AccessToken.getCurrentAccessToken();
    }

    public GoogleSignInClient googleSignInClient(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        return GoogleSignIn.getClient(context, gso);
    }

    public GoogleSignInAccount getInfoLoginGoogle(Context context) {
        GoogleSignInAccount result = GoogleSignIn.getLastSignedInAccount(context);
        if (result != null) {
            return result;
        } else {
            return null;
        }
    }
}
