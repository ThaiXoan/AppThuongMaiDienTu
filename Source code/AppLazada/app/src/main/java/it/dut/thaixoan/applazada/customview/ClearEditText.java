package it.dut.thaixoan.applazada.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import it.dut.thaixoan.applazada.R;

public class ClearEditText extends AppCompatEditText {

    Drawable closeIcon;
    Drawable non_closeIcon;
    Boolean visible = false;
    Drawable myDrawable;
    int ALPHA = (int) (255 * .70f);
    public static final String TAG = "ClearEditText";
    public ClearEditText(Context context) {
        super(context);
        khoiTao();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoiTao();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoiTao();
    }

    private void khoiTao(){
        closeIcon = ContextCompat.getDrawable(getContext(), R.drawable.crossx);
        non_closeIcon = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent);
        cauHinh();
    }

    private void cauHinh(){
        setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        Drawable[] drawables = getCompoundDrawables();
        myDrawable = (visible) ? closeIcon : non_closeIcon;
        if (visible){
            myDrawable.setAlpha(ALPHA);
        }
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], myDrawable, drawables[3]);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - myDrawable.getBounds().width())) {
            setText("");
            cauHinh();
            invalidate();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        Log.d(TAG, "onTextChanged: " + text);
        if (text.length() > 0){
            visible = true;
            cauHinh();
        } else {
            visible = false;
            cauHinh();
        }
    }
}
