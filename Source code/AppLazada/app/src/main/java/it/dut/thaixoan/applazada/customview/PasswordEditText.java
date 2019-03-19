package it.dut.thaixoan.applazada.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.dut.thaixoan.applazada.R;


public class PasswordEditText extends android.support.v7.widget.AppCompatEditText {

    public static final String TITLE_ERROR = "Mật khẩu gồm ít nhất 6 ký tự và 1 chữ hoa!";
    public static final String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";
    public static int ALPHA = (int) (255 * .70f);
    private Drawable eye;
    private Drawable eyeHint;
    private Boolean visible = false;
    private Boolean useHint = false;
    private Boolean useValidate = false;
    private Drawable myDrawable;
    private Pattern pattern;
    private Matcher matcher;

    public PasswordEditText(Context context) {
        super(context);
        khoiTao(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoiTao(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoiTao(attrs);
    }

    private void khoiTao(AttributeSet attrs) {
        this.pattern = Pattern.compile(MATCHER_PATTERN);
        if (attrs != null) {
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.passwordEditText, 0, 0);
            this.useHint = typedArray.getBoolean(R.styleable.passwordEditText_useHint, false);
            this.useValidate = typedArray.getBoolean(R.styleable.passwordEditText_useValidate, false);
        }
        eye = ContextCompat.getDrawable(getContext(), R.drawable.eye_show);
        eyeHint = ContextCompat.getDrawable(getContext(), R.drawable.eye_hide);

        if (this.useValidate) {
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        String pass = getText().toString();
                        TextInputLayout textInputLayout = (TextInputLayout) v.getParentForAccessibility();
                        matcher = pattern.matcher(pass);
                        if (matcher.matches()) {
                            textInputLayout.setErrorEnabled(false);
                        } else {
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError(TITLE_ERROR);
                        }
                    }
                }
            });
        }
        cauHinh();
    }

    private void cauHinh() {
        setInputType(InputType.TYPE_CLASS_TEXT | (visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        myDrawable = (useHint && !visible) ? eyeHint : eye;
        myDrawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], myDrawable, drawables[3]);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - myDrawable.getBounds().width())) {
            visible = !visible;
            cauHinh();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
