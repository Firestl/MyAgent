package com.sangfor.sdk.common.ui.Sangfor_b;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import supwisdom.b91;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class Sangfor_i extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3918a;

    public Sangfor_i(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(View.MeasureSpec.getSize(i), this.f3918a);
    }

    public Sangfor_i(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (Build.VERSION.SDK_INT >= 19) {
            this.f3918a = b91.a(context);
        }
    }
}
