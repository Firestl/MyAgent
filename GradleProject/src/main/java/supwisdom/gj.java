package supwisdom;

import android.text.TextUtils;

/* JADX INFO: compiled from: Euler.java */
/* JADX INFO: loaded from: classes.dex */
public class gj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f7738a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f7739e = true;
    public double b = 0.0d;
    public double c = 0.0d;
    public double d = 0.0d;

    public void a(double d, double d2, double d3, String str) {
        this.b = d;
        this.c = d2;
        this.d = d3;
        if (TextUtils.isEmpty(str)) {
            str = "XYZ";
        }
        this.f7738a = str;
    }
}
