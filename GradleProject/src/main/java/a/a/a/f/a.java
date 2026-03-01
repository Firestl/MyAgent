package a.a.a.f;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends LinearLayout {
    public a(Context context) {
        super(context);
        LayoutInflater.from(getContext()).inflate(getLayoutResId(), this);
        b();
    }

    public void a() {
    }

    public abstract void b();

    public abstract int getLayoutResId();

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(getContext()).inflate(getLayoutResId(), this);
        b();
        a();
    }
}
