package com.vivo.push.c;

import com.vivo.push.model.UnvarnishedMessage;

/* JADX INFO: compiled from: OnMessageReceiveTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class q implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ UnvarnishedMessage f5596a;
    public final /* synthetic */ p b;

    public q(p pVar, UnvarnishedMessage unvarnishedMessage) {
        this.b = pVar;
        this.f5596a = unvarnishedMessage;
    }

    @Override // java.lang.Runnable
    public final void run() {
        p pVar = this.b;
        ((y) pVar).b.onTransmissionMessage(pVar.f5616a, this.f5596a);
    }
}
