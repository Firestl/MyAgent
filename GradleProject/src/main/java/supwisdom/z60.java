package supwisdom;

/* JADX INFO: compiled from: SubtitleDecoderFactory.java */
/* JADX INFO: loaded from: classes.dex */
public interface z60 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final z60 f9971a = new a();

    /* JADX INFO: compiled from: SubtitleDecoderFactory.java */
    public static class a implements z60 {
        @Override // supwisdom.z60
        public boolean a(com.google.android.exoplayer2.j jVar) {
            String str = jVar.f;
            return "text/vtt".equals(str) || "application/ttml+xml".equals(str) || "application/x-mp4-vtt".equals(str) || "application/x-subrip".equals(str) || "application/x-quicktime-tx3g".equals(str) || "application/cea-608".equals(str) || "application/x-mp4-cea-608".equals(str) || "application/cea-708".equals(str) || "application/dvbsubs".equals(str);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0065  */
        @Override // supwisdom.z60
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public supwisdom.p60 b(com.google.android.exoplayer2.j r3) {
            /*
                Method dump skipped, instruction units count: 232
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.z60.a.b(com.google.android.exoplayer2.j):supwisdom.p60");
        }
    }

    boolean a(com.google.android.exoplayer2.j jVar);

    p60 b(com.google.android.exoplayer2.j jVar);
}
