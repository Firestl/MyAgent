package supwisdom;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: HlsMasterPlaylist.java */
/* JADX INFO: loaded from: classes.dex */
public final class va0 extends xa0 {
    public final List<a> b;
    public final List<a> c;
    public final List<a> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final com.google.android.exoplayer2.j f9496e;
    public final List<com.google.android.exoplayer2.j> f;

    /* JADX INFO: compiled from: HlsMasterPlaylist.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f9497a;
        public final com.google.android.exoplayer2.j b;

        public a(String str, com.google.android.exoplayer2.j jVar) {
            this.f9497a = str;
            this.b = jVar;
        }

        public static a a(String str) {
            return new a(str, com.google.android.exoplayer2.j.b("0", "application/x-mpegURL", null, null, -1, 0, null));
        }
    }

    public va0(String str, List<a> list, List<a> list2, List<a> list3, com.google.android.exoplayer2.j jVar, List<com.google.android.exoplayer2.j> list4) {
        super(str);
        this.b = Collections.unmodifiableList(list);
        this.c = Collections.unmodifiableList(list2);
        this.d = Collections.unmodifiableList(list3);
        this.f9496e = jVar;
        this.f = list4 != null ? Collections.unmodifiableList(list4) : null;
    }

    public static va0 a(String str) {
        List listSingletonList = Collections.singletonList(a.a(str));
        List listEmptyList = Collections.emptyList();
        return new va0(null, listSingletonList, listEmptyList, listEmptyList, null, null);
    }
}
