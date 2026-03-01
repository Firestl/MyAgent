package com.vivo.push.b;

import com.baidu.speech.asr.SpeechConstant;

/* JADX INFO: compiled from: OnLogReceiveCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n extends s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5570a;
    public int b;
    public boolean c;

    public n() {
        super(7);
        this.b = 0;
        this.c = false;
    }

    public final void a(int i) {
        this.b = i;
    }

    public final void b(String str) {
        this.f5570a = str;
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        aVar.a("content", this.f5570a);
        aVar.a(SpeechConstant.LOG_LEVEL, this.b);
        aVar.a("is_server_log", this.c);
    }

    public final String d() {
        return this.f5570a;
    }

    public final int e() {
        return this.b;
    }

    public final boolean f() {
        return this.c;
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final String toString() {
        return "OnLogCommand";
    }

    public final void a(boolean z) {
        this.c = z;
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        super.d(aVar);
        this.f5570a = aVar.a("content");
        this.b = aVar.b(SpeechConstant.LOG_LEVEL, 0);
        this.c = aVar.e("is_server_log");
    }
}
