package com.igexin.push.b;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.igexin.c.a.b.e;
import com.igexin.c.a.d.f;
import com.igexin.push.core.d;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f3240a = 0;
    public static final int b = -980948;
    public static final int g = -2147483639;
    public static final String h = "com.igexin.push.b.a";
    public SQLiteDatabase c;
    public Cursor d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<com.igexin.push.core.e.a> f3241e;
    public boolean f;

    public a() {
        super(1);
        this.f3241e = new LinkedList();
    }

    private void b(boolean z) {
        this.f = z;
    }

    public final void a(com.igexin.push.core.e.a aVar) {
        this.f3241e.add(aVar);
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void b_() throws Exception {
        super.b_();
        SQLiteDatabase writableDatabase = d.a.f3384a.i.getWritableDatabase();
        this.c = writableDatabase;
        writableDatabase.setVersion(7);
        Iterator<com.igexin.push.core.e.a> it = this.f3241e.iterator();
        while (it.hasNext()) {
            it.next().a(this.c);
        }
        for (com.igexin.push.core.e.a aVar : this.f3241e) {
            if (this.f) {
                aVar.c(this.c);
            } else {
                aVar.b(this.c);
            }
        }
        e.a().a(new c());
        e.a().b();
    }

    @Override // com.igexin.c.a.d.a.e
    public final int c() {
        return -2147483639;
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void d() {
        super.d();
        this.o = true;
        this.L = true;
    }

    @Override // com.igexin.c.a.d.f, com.igexin.c.a.d.a.f
    public final void d_() {
        super.d_();
        Cursor cursor = this.d;
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
        }
    }

    @Override // com.igexin.c.a.d.f
    public final void e() {
    }

    @Override // com.igexin.c.a.d.f
    public final void f() {
    }
}
