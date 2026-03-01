package supwisdom;

import com.google.android.exoplayer2.e.d;

/* JADX INFO: compiled from: MediaCodecSelector.java */
/* JADX INFO: loaded from: classes.dex */
public interface i50 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i50 f7915a = new a();

    /* JADX INFO: compiled from: MediaCodecSelector.java */
    public static class a implements i50 {
        @Override // supwisdom.i50
        public h50 a(String str, boolean z) throws d.b {
            return com.google.android.exoplayer2.e.d.a(str, z);
        }

        @Override // supwisdom.i50
        public h50 a() throws d.b {
            return com.google.android.exoplayer2.e.d.a();
        }
    }

    h50 a() throws d.b;

    h50 a(String str, boolean z) throws d.b;
}
