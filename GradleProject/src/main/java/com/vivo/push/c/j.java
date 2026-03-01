package com.vivo.push.c;

import java.util.List;

/* JADX INFO: compiled from: OnDelTagsReceiveTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5592a;
    public final /* synthetic */ List b;
    public final /* synthetic */ List c;
    public final /* synthetic */ String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ h f5593e;

    public j(h hVar, int i, List list, List list2, String str) {
        this.f5593e = hVar;
        this.f5592a = i;
        this.b = list;
        this.c = list2;
        this.d = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        h hVar = this.f5593e;
        ((y) hVar).b.onDelAlias(hVar.f5616a, this.f5592a, this.b, this.c, this.d);
    }
}
