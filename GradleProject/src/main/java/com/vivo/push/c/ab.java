package com.vivo.push.c;

import java.util.List;

/* JADX INFO: compiled from: OnSetTagsReceiveTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ab implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5586a;
    public final /* synthetic */ List b;
    public final /* synthetic */ List c;
    public final /* synthetic */ String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ z f5587e;

    public ab(z zVar, int i, List list, List list2, String str) {
        this.f5587e = zVar;
        this.f5586a = i;
        this.b = list;
        this.c = list2;
        this.d = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        z zVar = this.f5587e;
        ((y) zVar).b.onSetAlias(zVar.f5616a, this.f5586a, this.b, this.c, this.d);
    }
}
