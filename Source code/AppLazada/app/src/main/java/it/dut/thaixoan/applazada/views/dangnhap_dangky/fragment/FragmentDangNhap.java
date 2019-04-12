package it.dut.thaixoan.applazada.views.dangnhap_dangky.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;

import it.dut.thaixoan.applazada.R;
import it.dut.thaixoan.applazada.models.dangnhap_dangky.ModelDangNhap;
import it.dut.thaixoan.applazada.views.trangChu.TrangChuActivity;

public class FragmentDangNhap extends Fragment implements View.OnClickListener {

    public static final String TAG = "FragmentDangNhap";
    View view;
    Button btnLogin;
    Button btnLoginFacebook;
    Button btnLoginGoogle;
    EditText editTextEmail;
    EditText editTextPassword;
    public static int RC_SIGN_IN = 0;
    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;
    private ModelDangNhap modelDangNhap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dangnhap, container, false);

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());

        modelDangNhap = new ModelDangNhap();
        mGoogleSignInClient = modelDangNhap.googleSignInClient(getContext());


        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(getContext(), TrangChuActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel: ");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "onError: " + error);
            }
        });

        btnLogin = view.findViewById(R.id.button_login);
        btnLoginFacebook = view.findViewById(R.id.button_login_facebook);
        btnLoginGoogle = view.findViewById(R.id.button_login_google);
        editTextEmail = view.findViewById(R.id.edit_email);
        editTextPassword = view.findViewById(R.id.edit_password);

        btnLogin.setOnClickListener(this);
        btnLoginFacebook.setOnClickListener(this);
        btnLoginGoogle.setOnClickListener(this);


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> result = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (result.isSuccessful()){
                Intent intent = new Intent(getContext(), TrangChuActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.button_login_facebook:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile", "email"));
                break;
            case R.id.button_login_google:
                signInGoogleFunction();
                break;

            case R.id.button_login:
                loginFunction();
                break;
        }
    }

    private void loginFunction() {
        String tenDangNhap = editTextEmail.getText().toString();
        String matKhau = editTextPassword.getText().toString();
        if (modelDangNhap.kiemTraDangNhap(tenDangNhap, matKhau)){
            Intent intent = new Intent(getContext(), TrangChuActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), R.string.login_failed, Toast.LENGTH_SHORT).show();
        }
    }

    private void signInGoogleFunction() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
