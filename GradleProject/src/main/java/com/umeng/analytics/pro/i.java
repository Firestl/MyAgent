package com.umeng.analytics.pro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: UMDBManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class i {
    public static SQLiteOpenHelper b;
    public static Context d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AtomicInteger f5286a;
    public SQLiteDatabase c;

    /* JADX INFO: compiled from: UMDBManager.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final i f5287a = new i();
    }

    public static i a(Context context) {
        if (d == null && context != null) {
            Context applicationContext = context.getApplicationContext();
            d = applicationContext;
            b = h.a(applicationContext);
        }
        return a.f5287a;
    }

    public synchronized void b() {
        try {
            if (this.f5286a.decrementAndGet() == 0) {
                this.c.close();
            }
        } catch (Throwable unused) {
        }
    }

    public i() {
        this.f5286a = new AtomicInteger();
    }

    public synchronized SQLiteDatabase a() {
        if (this.f5286a.incrementAndGet() == 1) {
            this.c = b.getWritableDatabase();
        }
        return this.c;
    }
}
