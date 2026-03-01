package com.vivo.push.b;

/* JADX INFO: compiled from: StopServiceCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class y extends com.vivo.push.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5581a;

    public y(String str) {
        super(2008);
        this.f5581a = str;
    }

    @Override // com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        aVar.a("package_name", this.f5581a);
    }

    @Override // com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        this.f5581a = aVar.a("package_name");
    }

    @Override // com.vivo.push.o
    public final String toString() {
        return "StopServiceCommand";
    }

    public y() {
        super(2008);
    }
}
