package com.igexin.push.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.igexin.c.a.b.e;
import com.igexin.c.a.d.f;
import com.igexin.push.core.d;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d extends f {
    public static final int l = -2147483640;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3245a;
    public SQLiteDatabase d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Cursor f3246e;
    public Context f;
    public String[] g;
    public ContentValues h;
    public ContentValues[] i;
    public Object j;
    public c k;

    public d() {
        super(1);
        this.f3245a = getClass().getName();
    }

    public d(ContentValues contentValues) {
        super(1);
        this.f3245a = getClass().getName();
        this.h = contentValues;
    }

    public d(Context context) {
        super(1);
        this.f3245a = getClass().getName();
        this.f = context;
    }

    public d(Context context, ContentValues contentValues) {
        super(1);
        this.f3245a = getClass().getName();
        this.f = context;
        this.h = contentValues;
    }

    public d(Context context, c cVar) {
        super(1);
        this.f3245a = getClass().getName();
        this.f = context;
        this.k = cVar;
    }

    public d(Context context, Object obj) {
        super(1);
        this.f3245a = getClass().getName();
        this.f = context;
        this.j = obj;
    }

    public d(Context context, ContentValues[] contentValuesArr) {
        super(1);
        this.f3245a = getClass().getName();
        this.f = context;
        this.i = contentValuesArr;
    }

    public d(Context context, String[] strArr) {
        super(1);
        this.f3245a = getClass().getName();
        this.f = context;
        this.g = strArr;
    }

    private void a(c cVar) {
        this.k = cVar;
    }

    public abstract void a_() throws Exception;

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void b_() throws Exception {
        super.b_();
        this.d = d.a.f3384a.i.getWritableDatabase();
        a_();
        if (this.k != null) {
            e.a().a(this.k);
            e.a().b();
        }
    }

    @Override // com.igexin.c.a.d.a.e
    public final int c() {
        return l;
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void d() {
        this.o = true;
        this.L = true;
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void d_() {
        super.d_();
        Cursor cursor = this.f3246e;
        if (cursor == null || cursor.isClosed()) {
            return;
        }
        try {
            this.f3246e.close();
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
    }

    @Override // com.igexin.c.a.d.f
    public final void e() {
    }

    @Override // com.igexin.c.a.d.f
    public final void f() {
    }
}
