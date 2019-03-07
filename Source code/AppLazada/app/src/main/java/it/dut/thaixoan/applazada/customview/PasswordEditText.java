package it.dut.thaixoan.applazada.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;

import it.dut.thaixoan.applazada.R;


public class PasswordEditText extends android.support.v7.widget.AppCompatEditText {

    Drawable eye;
    Drawable eyeHint;
    Boolean visible = false;
    Boolean useHint = false;
    Drawable myDrawable;
    int ALPHA = (int) (255 * .70f);

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
        if (attrs != null) {
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.passwordEditText, 0, 0);
            this.useHint = typedArray.getBoolean(R.styleable.passwordEditText_useHint, false);
        }
        eye = ContextCompat.getDrawable(getContext(), R.drawable.eye_show);
        eyeHint = ContextCompat.getDrawable(getContext(), R.drawable.eye_hide);
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
