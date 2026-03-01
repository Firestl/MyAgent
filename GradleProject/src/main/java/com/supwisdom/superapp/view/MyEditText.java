package com.supwisdom.superapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.appcompat.widget.AppCompatEditText;

/* JADX INFO: loaded from: classes2.dex */
public class MyEditText extends AppCompatEditText {
    public long d;

    public MyEditText(Context context) {
        super(context);
        this.d = 0L;
    }

    @Override // android.widget.TextView
    public void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
        setSelection(getText().length());
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.d < 500) {
                this.d = jCurrentTimeMillis;
                return true;
            }
            this.d = jCurrentTimeMillis;
        }
        return super.onTouchEvent(motionEvent);
    }

    public MyEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 0L;
    }

    public MyEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0L;
    }
}
