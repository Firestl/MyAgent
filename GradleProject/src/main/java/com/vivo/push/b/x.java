package com.vivo.push.b;

import android.text.TextUtils;
import java.util.HashMap;

/* JADX INFO: compiled from: ReporterCommand.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x extends com.vivo.push.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<String, String> f5580a;
    public long b;

    public x() {
        super(2012);
    }

    public final void a(HashMap<String, String> map) {
        this.f5580a = map;
    }

    @Override // com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        aVar.a("ReporterCommand.EXTRA_PARAMS", this.f5580a);
        aVar.a("ReporterCommand.EXTRA_REPORTER_TYPE", this.b);
    }

    @Override // com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        this.f5580a = (HashMap) aVar.d("ReporterCommand.EXTRA_PARAMS");
        this.b = aVar.b("ReporterCommand.EXTRA_REPORTER_TYPE", this.b);
    }

    @Override // com.vivo.push.o
    public final String toString() {
        return "ReporterCommand（" + this.b + ")";
    }

    public x(long j) {
        this();
        this.b = j;
    }

    public final void d() {
        if (this.f5580a == null) {
            com.vivo.push.util.o.d("ReporterCommand", "reportParams is empty");
            return;
        }
        StringBuilder sb = new StringBuilder("report message reportType:");
        sb.append(this.b);
        sb.append(",msgId:");
        String str = this.f5580a.get("messageID");
        if (TextUtils.isEmpty(str)) {
            str = this.f5580a.get("message_id");
        }
        sb.append(str);
        com.vivo.push.util.o.d("ReporterCommand", sb.toString());
    }
}
