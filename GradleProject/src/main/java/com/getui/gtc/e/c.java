package com.getui.gtc.e;

import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.db.DbManager;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f2212a;
    public com.getui.gtc.e.a b;
    public e c;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static c f2213a = new c(0);
    }

    public c() {
        try {
            DbManager.init(GtcProvider.context(), b.class, com.getui.gtc.e.a.class, d.class, e.class);
            this.f2212a = (d) DbManager.getTable(b.class, d.class);
            this.c = (e) DbManager.getTable(b.class, e.class);
            this.b = (com.getui.gtc.e.a) DbManager.getTable(b.class, com.getui.gtc.e.a.class);
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
    }

    public /* synthetic */ c(byte b) {
        this();
    }
}
