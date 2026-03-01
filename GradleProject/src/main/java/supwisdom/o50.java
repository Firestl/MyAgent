package supwisdom;

/* JADX INFO: compiled from: MetadataDecoderFactory.java */
/* JADX INFO: loaded from: classes.dex */
public interface o50 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o50 f8629a = new a();

    /* JADX INFO: compiled from: MetadataDecoderFactory.java */
    public static class a implements o50 {
        @Override // supwisdom.o50
        public boolean a(com.google.android.exoplayer2.j jVar) {
            String str = jVar.f;
            return "application/id3".equals(str) || "application/x-emsg".equals(str) || "application/x-scte35".equals(str);
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0036  */
        @Override // supwisdom.o50
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public supwisdom.m50 b(com.google.android.exoplayer2.j r5) {
            /*
                r4 = this;
                java.lang.String r5 = r5.f
                int r0 = r5.hashCode()
                r1 = -1248341703(0xffffffffb597d139, float:-1.1311269E-6)
                r2 = 2
                r3 = 1
                if (r0 == r1) goto L2c
                r1 = 1154383568(0x44ce7ed0, float:1651.9629)
                if (r0 == r1) goto L22
                r1 = 1652648887(0x62816bb7, float:1.1936958E21)
                if (r0 == r1) goto L18
                goto L36
            L18:
                java.lang.String r0 = "application/x-scte35"
                boolean r5 = r5.equals(r0)
                if (r5 == 0) goto L36
                r5 = 2
                goto L37
            L22:
                java.lang.String r0 = "application/x-emsg"
                boolean r5 = r5.equals(r0)
                if (r5 == 0) goto L36
                r5 = 1
                goto L37
            L2c:
                java.lang.String r0 = "application/id3"
                boolean r5 = r5.equals(r0)
                if (r5 == 0) goto L36
                r5 = 0
                goto L37
            L36:
                r5 = -1
            L37:
                if (r5 == 0) goto L51
                if (r5 == r3) goto L4b
                if (r5 != r2) goto L43
                supwisdom.n50 r5 = new supwisdom.n50
                r5.<init>()
                return r5
            L43:
                java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "Attempted to create decoder for unsupported format"
                r5.<init>(r0)
                throw r5
            L4b:
                supwisdom.k50 r5 = new supwisdom.k50
                r5.<init>()
                return r5
            L51:
                supwisdom.l50 r5 = new supwisdom.l50
                r5.<init>()
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: supwisdom.o50.a.b(com.google.android.exoplayer2.j):supwisdom.m50");
        }
    }

    boolean a(com.google.android.exoplayer2.j jVar);

    m50 b(com.google.android.exoplayer2.j jVar);
}
