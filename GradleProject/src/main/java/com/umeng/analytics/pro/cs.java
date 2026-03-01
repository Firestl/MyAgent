package com.umeng.analytics.pro;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: TMessage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cs {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f5244a;
    public final byte b;
    public final int c;

    public cs() {
        this("", (byte) 0, 0);
    }

    public boolean a(cs csVar) {
        return this.f5244a.equals(csVar.f5244a) && this.b == csVar.b && this.c == csVar.c;
    }

    public boolean equals(Object obj) {
        if (obj instanceof cs) {
            return a((cs) obj);
        }
        return false;
    }

    public String toString() {
        return "<TMessage name:'" + this.f5244a + "' type: " + ((int) this.b) + " seqid:" + this.c + Operators.G;
    }

    public cs(String str, byte b, int i) {
        this.f5244a = str;
        this.b = b;
        this.c = i;
    }
}
