package com.vivo.push.b;

import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;

/* JADX INFO: compiled from: OnNotificationClickReceiveCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p extends com.vivo.push.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5572a;
    public String b;
    public byte[] c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public InsideNotificationItem f5573e;

    public p(String str, long j, InsideNotificationItem insideNotificationItem) {
        super(5);
        this.f5572a = str;
        this.d = j;
        this.f5573e = insideNotificationItem;
    }

    @Override // com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        aVar.a("package_name", this.f5572a);
        aVar.a("notify_id", this.d);
        aVar.a("notification_v1", com.vivo.push.util.p.b(this.f5573e));
        aVar.a("open_pkg_name", this.b);
        aVar.a("open_pkg_name_encode", this.c);
    }

    public final String d() {
        return this.f5572a;
    }

    public final long e() {
        return this.d;
    }

    public final InsideNotificationItem f() {
        return this.f5573e;
    }

    @Override // com.vivo.push.o
    public final String toString() {
        return "OnNotificationClickCommand";
    }

    @Override // com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        this.f5572a = aVar.a("package_name");
        this.d = aVar.b("notify_id", -1L);
        this.b = aVar.a("open_pkg_name");
        this.c = aVar.b("open_pkg_name_encode");
        String strA = aVar.a("notification_v1");
        if (!TextUtils.isEmpty(strA)) {
            this.f5573e = com.vivo.push.util.p.a(strA);
        }
        InsideNotificationItem insideNotificationItem = this.f5573e;
        if (insideNotificationItem != null) {
            insideNotificationItem.setMsgId(this.d);
        }
    }

    public p() {
        super(5);
    }
}
