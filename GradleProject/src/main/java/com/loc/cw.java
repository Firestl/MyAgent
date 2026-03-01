package com.loc;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: AmapCell.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class cw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3711a = "";
    public String b = "";
    public int c = 99;
    public int d = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f3712e = 0;
    public long f = 0;
    public int g = 0;
    public boolean h;
    public boolean i;

    public cw(boolean z, boolean z2) {
        this.i = true;
        this.h = z;
        this.i = z2;
    }

    public static int a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e2) {
            dg.a(e2);
            return 0;
        }
    }

    @Override // 
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public abstract cw clone();

    public final void a(cw cwVar) {
        if (cwVar != null) {
            this.f3711a = cwVar.f3711a;
            this.b = cwVar.b;
            this.c = cwVar.c;
            this.d = cwVar.d;
            this.f3712e = cwVar.f3712e;
            this.f = cwVar.f;
            this.g = cwVar.g;
            this.h = cwVar.h;
            this.i = cwVar.i;
        }
    }

    public final int b() {
        return a(this.f3711a);
    }

    public final int c() {
        return a(this.b);
    }

    public String toString() {
        return "AmapCell{mcc=" + this.f3711a + ", mnc=" + this.b + ", signalStrength=" + this.c + ", asulevel=" + this.d + ", lastUpdateSystemMills=" + this.f3712e + ", lastUpdateUtcMills=" + this.f + ", age=" + this.g + ", main=" + this.h + ", newapi=" + this.i + Operators.BLOCK_END;
    }
}
