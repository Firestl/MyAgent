package com.umeng.analytics.pro;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: TField.java */
/* JADX INFO: loaded from: classes2.dex */
public class cp {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f5241a;
    public final byte b;
    public final short c;

    public cp() {
        this("", (byte) 0, (short) 0);
    }

    public boolean a(cp cpVar) {
        return this.b == cpVar.b && this.c == cpVar.c;
    }

    public String toString() {
        return "<TField name:'" + this.f5241a + "' type:" + ((int) this.b) + " field-id:" + ((int) this.c) + Operators.G;
    }

    public cp(String str, byte b, short s) {
        this.f5241a = str;
        this.b = b;
        this.c = s;
    }
}
