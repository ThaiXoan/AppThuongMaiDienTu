package it.dut.thaixoan.applazada.views.dangnhap_dangky.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import it.dut.thaixoan.applazada.R;
import it.dut.thaixoan.applazada.models.object_class.NhanVien;
import it.dut.thaixoan.applazada.presenters.dangky.PresenterLogicDangKy;
import it.dut.thaixoan.applazada.until.Validates;
import it.dut.thaixoan.applazada.views.dangnhap_dangky.ViewDangKy;

public class FragmentDangKy extends Fragment implements ViewDangKy, View.OnClickListener, View.OnFocusChangeListener {

    private View view;
    PresenterLogicDangKy presenterLogicDangKy;

    private Button buttonDangKy;

    private TextInputLayout inputHoTen;
    private TextInputLayout inputEmail;
    private TextInputLayout inputMatKhau;
    private TextInputLayout inputConfirmMatKhau;

    private EditText editTextHoTen;
    private EditText editTextEmail;
    private EditText editTextMatKhau;
    private EditText editTextConfirmMatKhau;

    private SwitchCompat switchCompatNhanKhuyenMai;

    private String isNhanKhuyenMai = "Yes";
    private boolean isValidate = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dangky, container, false);


        addControll();
        addEvents();

        presenterLogicDangKy = new PresenterLogicDangKy(this);
        return view;

    }

    private void addControll() {

        inputHoTen = view.findViewById(R.id.input_full_name);
        inputEmail = view.findViewById(R.id.input_email);
        inputMatKhau = view.findViewById(R.id.input_password);
        inputConfirmMatKhau = view.findViewById(R.id.input_confirm_password);

        buttonDangKy = view.findViewById(R.id.button_dang_ky);

        editTextHoTen = view.findViewById(R.id.edit_full_name);
        editTextEmail = view.findViewById(R.id.edit_email);
        editTextMatKhau = view.findViewById(R.id.edit_password);
        editTextConfirmMatKhau = view.findViewById(R.id.edit_confirm_password);
        switchCompatNhanKhuyenMai = view.findViewById(R.id.switch_nhan_khuyen_mai);

    }

    private void addEvents() {
        buttonDangKy.setOnClickListener(this);
        editTextHoTen.setOnFocusChangeListener(this);
        editTextEmail.setOnFocusChangeListener(this);
        editTextConfirmMatKhau.setOnFocusChangeListener(this);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_dang_ky:
                xuLyDangKyThanhVien();
                break;
        }
    }

    private void xuLyDangKyThanhVien() {
        String hoTen = editTextHoTen.getText().toString();
        String email = editTextEmail.getText().toString();
        String matKhau = editTextMatKhau.getText().toString();
        String confirmMatKhau = editTextConfirmMatKhau.getText().toString();
        switchCompatNhanKhuyenMai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isNhanKhuyenMai = getString(R.string.yes);
                } else {
                    isNhanKhuyenMai = getString(R.string.no);
                }
            }
        });

        checkValidate(hoTen, email, matKhau, confirmMatKhau);

        if (isValidate) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setTenNhanVien(hoTen);
            nhanVien.setTenDangNhap(email);
            nhanVien.setMatKhau(matKhau);
            nhanVien.setNhanKhuyenMai(isNhanKhuyenMai);
            nhanVien.setMaLoaiNhanVien(2);

            presenterLogicDangKy.thucHienDangKy(nhanVien);
        } else {
            Log.d("ISVALIDATE", "loi roi: ");
        }

    }

    private void checkValidate(String hoTen, String email, String matKhau, String confirmMatKhau) {
        Validates validates = new Validates();
        if (!validates.isEmpty(hoTen)) {
            inputHoTen.setErrorEnabled(true);
            inputHoTen.setError(getString(R.string.input_ho_ten_empty_error));
        } else {
            inputHoTen.setErrorEnabled(false);
            inputHoTen.setError(null);
        }

        if (!validates.isEmpty(email)) {
            inputEmail.setErrorEnabled(true);
            inputEmail.setError(getString(R.string.input_email_empty_error));
        } else {
            if (!validates.isEmailMatcher(email)) {
                inputEmail.setErrorEnabled(true);
                inputEmail.setError(getString(R.string.input_email_matcher_error));
            } else {
                inputEmail.setErrorEnabled(false);
                inputEmail.setError(null);
            }
        }

        if (!validates.isEmpty(matKhau)) {
            inputMatKhau.setErrorEnabled(true);
            inputMatKhau.setError(getString(R.string.input_password_empty_error));
        } else {
            if (!validates.isPasswordMathcher(matKhau)) {
                inputMatKhau.setErrorEnabled(true);
                inputMatKhau.setError(getString(R.string.input_password_mathcher_error));
            } else {
                inputMatKhau.setErrorEnabled(false);
                inputMatKhau.setError(null);
            }
        }

        if (!validates.isEmpty(confirmMatKhau)) {
            inputConfirmMatKhau.setErrorEnabled(true);
            inputConfirmMatKhau.setError(getString(R.string.input_confirmpass_empty_error));
        } else {
            if (!validates.isEqualsPassword(matKhau, confirmMatKhau)) {
                inputConfirmMatKhau.setErrorEnabled(true);
                inputConfirmMatKhau.setError(getString(R.string.input_confirmpass_enter_not_maping_error));
            } else {
                inputConfirmMatKhau.setErrorEnabled(false);
                inputConfirmMatKhau.setError(null);
            }
        }

        isValidate = inputHoTen.getError() == null && inputEmail.getError() == null && inputMatKhau.getError() == null && inputConfirmMatKhau.getError() == null;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Validates validates = new Validates();
        int id = v.getId();
        switch (id) {
            case R.id.edit_full_name:
                if (!hasFocus) {
                    String hoTen = editTextHoTen.getText().toString();
                    if (!validates.isEmpty(hoTen)) {
                        inputHoTen.setErrorEnabled(true);
                        inputHoTen.setError(getString(R.string.input_ho_ten_empty_error));
                    } else {
                        inputHoTen.setErrorEnabled(false);
                        inputHoTen.setError(null);
                    }
                }
            case R.id.edit_email:
                if (!hasFocus) {
                    String email = editTextEmail.getText().toString();
                    if (!validates.isEmpty(email)) {
                        inputEmail.setErrorEnabled(true);
                        inputEmail.setError(getString(R.string.input_email_empty_error));
                    } else {
                        if (!validates.isEmailMatcher(email)) {
                            inputEmail.setErrorEnabled(true);
                            inputEmail.setError(getString(R.string.input_email_matcher_error));
                        } else {
                            inputEmail.setErrorEnabled(false);
                            inputEmail.setError(null);
                        }
                    }
                }
            case R.id.edit_confirm_password:
                if (!hasFocus) {
                    String confirmPassword = editTextConfirmMatKhau.getText().toString();
                    String password = editTextMatKhau.getText().toString();

                    if (!validates.isEmpty(confirmPassword)) {
                        inputConfirmMatKhau.setErrorEnabled(true);
                        inputConfirmMatKhau.setError(getString(R.string.input_confirmpass_empty_error));
                    } else {
                        if (!validates.isEqualsPassword(password, confirmPassword)) {
                            inputConfirmMatKhau.setErrorEnabled(true);
                            inputConfirmMatKhau.setError(getString(R.string.input_confirmpass_enter_not_maping_error));
                        } else {
                            inputConfirmMatKhau.setErrorEnabled(false);
                            inputConfirmMatKhau.setError(null);
                        }
                    }
                }
        }
        isValidate = inputHoTen.getError() == null && inputEmail.getError() == null && inputMatKhau.getError() == null && inputConfirmMatKhau.getError() == null;
    }
}
