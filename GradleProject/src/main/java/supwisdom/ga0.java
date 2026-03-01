package supwisdom;

import android.net.Uri;
import com.taobao.weex.el.parse.Operators;
import java.util.Collections;
import java.util.List;
import supwisdom.ia0;

/* JADX INFO: compiled from: Representation.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ga0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.google.android.exoplayer2.j f7707a;
    public final String b;
    public final long c;
    public final List<ha0> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final fa0 f7708e;

    /* JADX INFO: compiled from: Representation.java */
    public static class b extends ga0 implements oa0 {
        public final ia0.a f;

        public b(String str, long j, com.google.android.exoplayer2.j jVar, String str2, ia0.a aVar, List<ha0> list) {
            super(str, j, jVar, str2, aVar, list);
            this.f = aVar;
        }

        @Override // supwisdom.oa0
        public int a(long j, long j2) {
            return this.f.a(j, j2);
        }

        @Override // supwisdom.oa0
        public fa0 b(int i) {
            return this.f.a(this, i);
        }

        @Override // supwisdom.ga0
        public fa0 d() {
            return null;
        }

        @Override // supwisdom.ga0
        public oa0 e() {
            return this;
        }

        @Override // supwisdom.ga0
        public String f() {
            return null;
        }

        @Override // supwisdom.oa0
        public long a(int i) {
            return this.f.a(i);
        }

        @Override // supwisdom.oa0
        public boolean b() {
            return this.f.c();
        }

        @Override // supwisdom.oa0
        public long a(int i, long j) {
            return this.f.a(i, j);
        }

        @Override // supwisdom.oa0
        public int a() {
            return this.f.b();
        }

        @Override // supwisdom.oa0
        public int a(long j) {
            return this.f.a(j);
        }
    }

    /* JADX INFO: compiled from: Representation.java */
    public static class c extends ga0 {
        public final Uri f;
        public final String g;
        public final fa0 h;
        public final ja0 i;

        public c(String str, long j, com.google.android.exoplayer2.j jVar, String str2, ia0.e eVar, List<ha0> list, String str3, long j2) {
            String str4;
            super(str, j, jVar, str2, eVar, list);
            this.f = Uri.parse(str2);
            this.h = eVar.b();
            if (str3 != null) {
                str4 = str3;
            } else if (str != null) {
                str4 = str + Operators.DOT_STR + jVar.f2303a + Operators.DOT_STR + j;
            } else {
                str4 = null;
            }
            this.g = str4;
            this.i = this.h == null ? new ja0(new fa0(null, 0L, j2)) : null;
        }

        @Override // supwisdom.ga0
        public fa0 d() {
            return this.h;
        }

        @Override // supwisdom.ga0
        public oa0 e() {
            return this.i;
        }

        @Override // supwisdom.ga0
        public String f() {
            return this.g;
        }
    }

    public static ga0 a(String str, long j, com.google.android.exoplayer2.j jVar, String str2, ia0 ia0Var, List<ha0> list) {
        return a(str, j, jVar, str2, ia0Var, list, null);
    }

    public fa0 c() {
        return this.f7708e;
    }

    public abstract fa0 d();

    public abstract oa0 e();

    public abstract String f();

    public ga0(String str, long j, com.google.android.exoplayer2.j jVar, String str2, ia0 ia0Var, List<ha0> list) {
        this.f7707a = jVar;
        this.b = str2;
        this.d = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        this.f7708e = ia0Var.a(this);
        this.c = ia0Var.a();
    }

    public static ga0 a(String str, long j, com.google.android.exoplayer2.j jVar, String str2, ia0 ia0Var, List<ha0> list, String str3) {
        if (ia0Var instanceof ia0.e) {
            return new c(str, j, jVar, str2, (ia0.e) ia0Var, list, str3, -1L);
        }
        if (ia0Var instanceof ia0.a) {
            return new b(str, j, jVar, str2, (ia0.a) ia0Var, list);
        }
        throw new IllegalArgumentException("segmentBase must be of type SingleSegmentBase or MultiSegmentBase");
    }
}
