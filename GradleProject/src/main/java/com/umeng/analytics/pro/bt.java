package com.umeng.analytics.pro;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: ShortStack.java */
/* JADX INFO: loaded from: classes2.dex */
public class bt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public short[] f5215a;
    public int b = -1;

    public bt(int i) {
        this.f5215a = new short[i];
    }

    private void d() {
        short[] sArr = this.f5215a;
        short[] sArr2 = new short[sArr.length * 2];
        System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
        this.f5215a = sArr2;
    }

    public short a() {
        short[] sArr = this.f5215a;
        int i = this.b;
        this.b = i - 1;
        return sArr[i];
    }

    public short b() {
        return this.f5215a[this.b];
    }

    public void c() {
        this.b = -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ShortStack vector:[");
        for (int i = 0; i < this.f5215a.length; i++) {
            if (i != 0) {
                sb.append(Operators.SPACE_STR);
            }
            if (i == this.b) {
                sb.append(">>");
            }
            sb.append((int) this.f5215a[i]);
            if (i == this.b) {
                sb.append("<<");
            }
        }
        sb.append("]>");
        return sb.toString();
    }

    public void a(short s) {
        if (this.f5215a.length == this.b + 1) {
            d();
        }
        short[] sArr = this.f5215a;
        int i = this.b + 1;
        this.b = i;
        sArr[i] = s;
    }
}
