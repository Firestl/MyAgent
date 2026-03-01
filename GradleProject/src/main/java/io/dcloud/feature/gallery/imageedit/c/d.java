package io.dcloud.feature.gallery.imageedit.c;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6534a;
    public int b;
    public int c;

    public d(String str, int i, int i2) {
        this.b = -1;
        this.c = 0;
        this.f6534a = str;
        this.b = i;
        this.c = i2;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public String c() {
        return this.f6534a;
    }

    public boolean d() {
        return TextUtils.isEmpty(this.f6534a);
    }

    public String toString() {
        return "IMGText{text='" + this.f6534a + Operators.SINGLE_QUOTE + ", color=" + this.b + Operators.BLOCK_END;
    }
}
