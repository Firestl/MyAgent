package supwisdom;

import android.util.Log;
import com.google.android.exoplayer2.f.a;
import com.google.zxing.client.android.LocaleManager;
import io.dcloud.common.DHInterface.IFeature;

/* JADX INFO: compiled from: MetadataUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class d30 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f7289a = x80.g("nam");
    public static final int b = x80.g("trk");
    public static final int c = x80.g("cmt");
    public static final int d = x80.g("day");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f7290e = x80.g("ART");
    public static final int f = x80.g("too");
    public static final int g = x80.g("alb");
    public static final int h = x80.g(LocaleManager.DEFAULT_TLD);
    public static final int i = x80.g("wrt");
    public static final int j = x80.g("lyr");
    public static final int k = x80.g("gen");
    public static final int l = x80.g("covr");
    public static final int m = x80.g("gnre");
    public static final int n = x80.g("grp");
    public static final int o = x80.g("disk");
    public static final int p = x80.g("trkn");
    public static final int q = x80.g("tmpo");
    public static final int r = x80.g("cpil");
    public static final int s = x80.g("aART");
    public static final int t = x80.g("sonm");
    public static final int u = x80.g("soal");
    public static final int v = x80.g("soar");
    public static final int w = x80.g("soaa");
    public static final int x = x80.g("soco");
    public static final int y = x80.g("rtng");
    public static final int z = x80.g("pgap");
    public static final int A = x80.g("sosn");
    public static final int B = x80.g("tvsh");
    public static final int C = x80.g("----");
    public static final String[] D = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", IFeature.F_SPEECH, "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};

    public static a.InterfaceC0053a a(o80 o80Var) {
        int iD = o80Var.d() + o80Var.n();
        int iN = o80Var.n();
        int i2 = (iN >> 24) & 255;
        try {
            if (i2 == 169 || i2 == 65533) {
                int i3 = 16777215 & iN;
                if (i3 == c) {
                    return a(iN, o80Var);
                }
                if (i3 != f7289a && i3 != b) {
                    if (i3 != h && i3 != i) {
                        if (i3 == d) {
                            return a(iN, "TDRC", o80Var);
                        }
                        if (i3 == f7290e) {
                            return a(iN, "TPE1", o80Var);
                        }
                        if (i3 == f) {
                            return a(iN, "TSSE", o80Var);
                        }
                        if (i3 == g) {
                            return a(iN, "TALB", o80Var);
                        }
                        if (i3 == j) {
                            return a(iN, "USLT", o80Var);
                        }
                        if (i3 == k) {
                            return a(iN, "TCON", o80Var);
                        }
                        if (i3 == n) {
                            return a(iN, "TIT1", o80Var);
                        }
                    }
                    return a(iN, "TCOM", o80Var);
                }
                return a(iN, "TIT2", o80Var);
            }
            if (iN == m) {
                return b(o80Var);
            }
            if (iN == o) {
                return b(iN, "TPOS", o80Var);
            }
            if (iN == p) {
                return b(iN, "TRCK", o80Var);
            }
            if (iN == q) {
                return a(iN, "TBPM", o80Var, true, false);
            }
            if (iN == r) {
                return a(iN, "TCMP", o80Var, true, true);
            }
            if (iN == l) {
                return c(o80Var);
            }
            if (iN == s) {
                return a(iN, "TPE2", o80Var);
            }
            if (iN == t) {
                return a(iN, "TSOT", o80Var);
            }
            if (iN == u) {
                return a(iN, "TSO2", o80Var);
            }
            if (iN == v) {
                return a(iN, "TSOA", o80Var);
            }
            if (iN == w) {
                return a(iN, "TSOP", o80Var);
            }
            if (iN == x) {
                return a(iN, "TSOC", o80Var);
            }
            if (iN == y) {
                return a(iN, "ITUNESADVISORY", o80Var, false, false);
            }
            if (iN == z) {
                return a(iN, "ITUNESGAPLESS", o80Var, false, true);
            }
            if (iN == A) {
                return a(iN, "TVSHOWSORT", o80Var);
            }
            if (iN == B) {
                return a(iN, "TVSHOW", o80Var);
            }
            if (iN == C) {
                return a(o80Var, iD);
            }
            Log.d("MetadataUtil", "Skipped unknown metadata entry: " + y20.c(iN));
            return null;
        } finally {
            o80Var.c(iD);
        }
    }

    public static com.google.android.exoplayer2.f.b.j b(int i2, String str, o80 o80Var) {
        int iN = o80Var.n();
        if (o80Var.n() == y20.F0 && iN >= 22) {
            o80Var.d(10);
            int iH = o80Var.h();
            if (iH > 0) {
                String str2 = "" + iH;
                int iH2 = o80Var.h();
                if (iH2 > 0) {
                    str2 = str2 + "/" + iH2;
                }
                return new com.google.android.exoplayer2.f.b.j(str, null, str2);
            }
        }
        Log.w("MetadataUtil", "Failed to parse index/count attribute: " + y20.c(i2));
        return null;
    }

    public static com.google.android.exoplayer2.f.b.a c(o80 o80Var) {
        int iN = o80Var.n();
        if (o80Var.n() != y20.F0) {
            Log.w("MetadataUtil", "Failed to parse cover art attribute");
            return null;
        }
        int iB = y20.b(o80Var.n());
        String str = iB == 13 ? "image/jpeg" : iB == 14 ? "image/png" : null;
        if (str == null) {
            Log.w("MetadataUtil", "Unrecognized cover art flags: " + iB);
            return null;
        }
        o80Var.d(4);
        int i2 = iN - 16;
        byte[] bArr = new byte[i2];
        o80Var.a(bArr, 0, i2);
        return new com.google.android.exoplayer2.f.b.a(str, null, 3, bArr);
    }

    public static int d(o80 o80Var) {
        o80Var.d(4);
        if (o80Var.n() == y20.F0) {
            o80Var.d(8);
            return o80Var.g();
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.exoplayer2.f.b.j b(supwisdom.o80 r3) {
        /*
            int r3 = d(r3)
            r0 = 0
            if (r3 <= 0) goto L11
            java.lang.String[] r1 = supwisdom.d30.D
            int r2 = r1.length
            if (r3 > r2) goto L11
            int r3 = r3 + (-1)
            r3 = r1[r3]
            goto L12
        L11:
            r3 = r0
        L12:
            if (r3 == 0) goto L1c
            com.google.android.exoplayer2.f.b.j r1 = new com.google.android.exoplayer2.f.b.j
            java.lang.String r2 = "TCON"
            r1.<init>(r2, r0, r3)
            return r1
        L1c:
            java.lang.String r3 = "MetadataUtil"
            java.lang.String r1 = "Failed to parse standard genre code"
            android.util.Log.w(r3, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.d30.b(supwisdom.o80):com.google.android.exoplayer2.f.b.j");
    }

    public static com.google.android.exoplayer2.f.b.j a(int i2, String str, o80 o80Var) {
        int iN = o80Var.n();
        if (o80Var.n() == y20.F0) {
            o80Var.d(8);
            return new com.google.android.exoplayer2.f.b.j(str, null, o80Var.f(iN - 16));
        }
        Log.w("MetadataUtil", "Failed to parse text attribute: " + y20.c(i2));
        return null;
    }

    public static com.google.android.exoplayer2.f.b.e a(int i2, o80 o80Var) {
        int iN = o80Var.n();
        if (o80Var.n() == y20.F0) {
            o80Var.d(8);
            String strF = o80Var.f(iN - 16);
            return new com.google.android.exoplayer2.f.b.e("und", strF, strF);
        }
        Log.w("MetadataUtil", "Failed to parse comment attribute: " + y20.c(i2));
        return null;
    }

    public static com.google.android.exoplayer2.f.b.h a(int i2, String str, o80 o80Var, boolean z2, boolean z3) {
        int iD = d(o80Var);
        if (z3) {
            iD = Math.min(1, iD);
        }
        if (iD >= 0) {
            if (z2) {
                return new com.google.android.exoplayer2.f.b.j(str, null, Integer.toString(iD));
            }
            return new com.google.android.exoplayer2.f.b.e("und", str, Integer.toString(iD));
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute: " + y20.c(i2));
        return null;
    }

    public static com.google.android.exoplayer2.f.b.h a(o80 o80Var, int i2) {
        String strF = null;
        String strF2 = null;
        int i3 = -1;
        int i4 = -1;
        while (o80Var.d() < i2) {
            int iD = o80Var.d();
            int iN = o80Var.n();
            int iN2 = o80Var.n();
            o80Var.d(4);
            if (iN2 == y20.D0) {
                strF = o80Var.f(iN - 12);
            } else if (iN2 == y20.E0) {
                strF2 = o80Var.f(iN - 12);
            } else {
                if (iN2 == y20.F0) {
                    i3 = iD;
                    i4 = iN;
                }
                o80Var.d(iN - 12);
            }
        }
        if (!"com.apple.iTunes".equals(strF) || !"iTunSMPB".equals(strF2) || i3 == -1) {
            return null;
        }
        o80Var.c(i3);
        o80Var.d(16);
        return new com.google.android.exoplayer2.f.b.e("und", strF2, o80Var.f(i4 - 16));
    }
}
