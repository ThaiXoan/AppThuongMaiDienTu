package it.dut.thaixoan.applazada.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;


public class PasswordEditText extends android.support.v7.widget.AppCompatEditText {

    Drawable eyeShow;
    Drawable eyeHint;
    Boolean  visible = false;
    public PasswordEditText(Context context) {
        super(context);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
