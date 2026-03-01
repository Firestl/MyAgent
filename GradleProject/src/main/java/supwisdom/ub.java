package supwisdom;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: AccessibilityClickableSpanCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class ub extends ClickableSpan {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9389a;
    public final xb b;
    public final int c;

    public ub(int i, xb xbVar, int i2) {
        this.f9389a = i;
        this.b = xbVar;
        this.c = i2;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f9389a);
        this.b.a(this.c, bundle);
    }
}
