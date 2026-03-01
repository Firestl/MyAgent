package supwisdom;

import android.net.Uri;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.list.template.CellDataManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import supwisdom.c80;
import supwisdom.va0;
import supwisdom.wa0;

/* JADX INFO: compiled from: HlsPlaylistParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class ya0 implements c80.a<xa0> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f9858a = Pattern.compile("BANDWIDTH=(\\d+)\\b");
    public static final Pattern b = Pattern.compile("CODECS=\"(.+?)\"");
    public static final Pattern c = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    public static final Pattern d = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Pattern f9859e = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    public static final Pattern f = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    public static final Pattern g = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    public static final Pattern h = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    public static final Pattern i = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    public static final Pattern j = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    public static final Pattern k = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    public static final Pattern l = Pattern.compile("METHOD=(NONE|AES-128)");
    public static final Pattern m = Pattern.compile("URI=\"(.+?)\"");
    public static final Pattern n = Pattern.compile("IV=([^,.*]+)");
    public static final Pattern o = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    public static final Pattern p = Pattern.compile("LANGUAGE=\"(.+?)\"");
    public static final Pattern q = Pattern.compile("NAME=\"(.+?)\"");
    public static final Pattern r = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    public static final Pattern s = b("AUTOSELECT");
    public static final Pattern t = b("DEFAULT");
    public static final Pattern u = b("FORCED");

    /* JADX INFO: compiled from: HlsPlaylistParser.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final BufferedReader f9860a;
        public final Queue<String> b;
        public String c;

        public a(Queue<String> queue, BufferedReader bufferedReader) {
            this.b = queue;
            this.f9860a = bufferedReader;
        }

        public boolean a() throws IOException {
            String strTrim;
            if (this.c != null) {
                return true;
            }
            if (!this.b.isEmpty()) {
                this.c = this.b.poll();
                return true;
            }
            do {
                String line = this.f9860a.readLine();
                this.c = line;
                if (line == null) {
                    return false;
                }
                strTrim = line.trim();
                this.c = strTrim;
            } while (strTrim.isEmpty());
            return true;
        }

        public String b() throws IOException {
            if (!a()) {
                return null;
            }
            String str = this.c;
            this.c = null;
            return str;
        }
    }

    public static double c(String str, Pattern pattern) throws com.google.android.exoplayer2.n {
        return Double.parseDouble(a(str, pattern));
    }

    public static String d(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    @Override // supwisdom.c80.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public xa0 a(Uri uri, InputStream inputStream) throws IOException {
        String strTrim;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        LinkedList linkedList = new LinkedList();
        try {
            if (!a(bufferedReader)) {
                throw new com.google.android.exoplayer2.source.n("Input does not start with the #EXTM3U header.", uri);
            }
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    x80.a(bufferedReader);
                    throw new com.google.android.exoplayer2.n("Failed to parse the playlist, could not identify any tags.");
                }
                strTrim = line.trim();
                if (!strTrim.isEmpty()) {
                    if (!strTrim.startsWith("#EXT-X-STREAM-INF")) {
                        if (strTrim.startsWith("#EXT-X-TARGETDURATION") || strTrim.startsWith("#EXT-X-MEDIA-SEQUENCE") || strTrim.startsWith("#EXTINF") || strTrim.startsWith("#EXT-X-KEY") || strTrim.startsWith("#EXT-X-BYTERANGE") || strTrim.equals("#EXT-X-DISCONTINUITY") || strTrim.equals("#EXT-X-DISCONTINUITY-SEQUENCE") || strTrim.equals("#EXT-X-ENDLIST")) {
                            break;
                        }
                        linkedList.add(strTrim);
                    } else {
                        linkedList.add(strTrim);
                        return a(new a(linkedList, bufferedReader), uri.toString());
                    }
                }
            }
            linkedList.add(strTrim);
            return b(new a(linkedList, bufferedReader), uri.toString());
        } finally {
            x80.a(bufferedReader);
        }
    }

    public static boolean a(BufferedReader bufferedReader) throws IOException {
        int i2 = bufferedReader.read();
        if (i2 == 239) {
            if (bufferedReader.read() != 187 || bufferedReader.read() != 191) {
                return false;
            }
            i2 = bufferedReader.read();
        }
        int iA = a(bufferedReader, true, i2);
        for (int i3 = 0; i3 < 7; i3++) {
            if (iA != "#EXTM3U".charAt(i3)) {
                return false;
            }
            iA = bufferedReader.read();
        }
        return x80.a(a(bufferedReader, false, iA));
    }

    public static int a(BufferedReader bufferedReader, boolean z, int i2) throws IOException {
        while (i2 != -1 && Character.isWhitespace(i2) && (z || !x80.a(i2))) {
            i2 = bufferedReader.read();
        }
        return i2;
    }

    public static va0 a(a aVar, String str) throws IOException {
        int i2;
        String str2;
        int i3;
        int i4;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        List listEmptyList = null;
        com.google.android.exoplayer2.j jVar = null;
        boolean zContains = false;
        while (aVar.a()) {
            String strB = aVar.b();
            int i5 = -1;
            if (strB.startsWith("#EXT-X-MEDIA")) {
                int iA = a(strB);
                String strD = d(strB, m);
                String strA = a(strB, q);
                String strD2 = d(strB, p);
                String strA2 = a(strB, o);
                int iHashCode = strA2.hashCode();
                if (iHashCode != -959297733) {
                    if (iHashCode != -333210994) {
                        if (iHashCode == 62628790 && strA2.equals("AUDIO")) {
                            i5 = 0;
                        }
                    } else if (strA2.equals("CLOSED-CAPTIONS")) {
                        i5 = 2;
                    }
                } else if (strA2.equals("SUBTITLES")) {
                    i5 = 1;
                }
                if (i5 == 0) {
                    com.google.android.exoplayer2.j jVarA = com.google.android.exoplayer2.j.a(strA, "application/x-mpegURL", (String) null, (String) null, -1, -1, -1, (List<byte[]>) null, iA, strD2);
                    if (strD == null) {
                        jVar = jVarA;
                    } else {
                        arrayList2.add(new va0.a(strD, jVarA));
                    }
                } else if (i5 == 1) {
                    arrayList3.add(new va0.a(strD, com.google.android.exoplayer2.j.a(strA, "application/x-mpegURL", "text/vtt", (String) null, -1, iA, strD2)));
                } else if (i5 == 2) {
                    String strA3 = a(strB, r);
                    if (strA3.startsWith("CC")) {
                        i2 = Integer.parseInt(strA3.substring(2));
                        str2 = "application/cea-608";
                    } else {
                        i2 = Integer.parseInt(strA3.substring(7));
                        str2 = "application/cea-708";
                    }
                    int i6 = i2;
                    String str3 = str2;
                    if (listEmptyList == null) {
                        listEmptyList = new ArrayList();
                    }
                    listEmptyList.add(com.google.android.exoplayer2.j.a(strA, (String) null, str3, (String) null, -1, iA, strD2, i6));
                }
            } else if (strB.startsWith("#EXT-X-STREAM-INF")) {
                int iB = b(strB, f9858a);
                String strD3 = d(strB, b);
                String strD4 = d(strB, c);
                zContains |= strB.contains("CLOSED-CAPTIONS=NONE");
                if (strD4 != null) {
                    String[] strArrSplit = strD4.split(Constants.Name.X);
                    int i7 = Integer.parseInt(strArrSplit[0]);
                    int i8 = Integer.parseInt(strArrSplit[1]);
                    if (i7 <= 0 || i8 <= 0) {
                        i8 = -1;
                    } else {
                        i5 = i7;
                    }
                    i4 = i8;
                    i3 = i5;
                } else {
                    i3 = -1;
                    i4 = -1;
                }
                arrayList.add(new va0.a(aVar.b(), com.google.android.exoplayer2.j.a(Integer.toString(arrayList.size()), "application/x-mpegURL", (String) null, strD3, iB, i3, i4, -1.0f, (List<byte[]>) null, 0)));
            }
        }
        if (zContains) {
            listEmptyList = Collections.emptyList();
        }
        return new va0(str, arrayList, arrayList2, arrayList3, jVar, listEmptyList);
    }

    public static wa0 b(a aVar, String str) throws IOException {
        String hexString;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        char c2 = 0;
        long jC = -9223372036854775807L;
        long jB = -9223372036854775807L;
        long jB2 = 0;
        long j2 = 0;
        long j3 = 0;
        long jC2 = 0;
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        int i4 = 0;
        int i5 = 0;
        int iB = 1;
        boolean z2 = false;
        wa0.a aVar2 = null;
        int iB2 = 0;
        long j4 = -1;
        boolean zEquals = false;
        while (true) {
            String strD = null;
            String strA = null;
            while (aVar.a()) {
                String strB = aVar.b();
                if (strB.startsWith("#EXT-X-PLAYLIST-TYPE")) {
                    String strA2 = a(strB, f);
                    if ("VOD".equals(strA2)) {
                        i3 = 1;
                    } else {
                        if (!"EVENT".equals(strA2)) {
                            throw new com.google.android.exoplayer2.n("Illegal playlist type: " + strA2);
                        }
                        i3 = 2;
                    }
                } else if (strB.startsWith("#EXT-X-START")) {
                    jC = (long) (c(strB, i) * 1000000.0d);
                } else if (strB.startsWith("#EXT-X-MAP")) {
                    String strA3 = a(strB, m);
                    String strD2 = d(strB, k);
                    if (strD2 != null) {
                        String[] strArrSplit = strD2.split(CellDataManager.VIRTUAL_COMPONENT_SEPRATOR);
                        j4 = Long.parseLong(strArrSplit[c2]);
                        if (strArrSplit.length > 1) {
                            j2 = Long.parseLong(strArrSplit[1]);
                        }
                    }
                    aVar2 = new wa0.a(strA3, j2, j4);
                    j2 = 0;
                    j4 = -1;
                } else if (strB.startsWith("#EXT-X-TARGETDURATION")) {
                    jB = 1000000 * ((long) b(strB, d));
                } else if (strB.startsWith("#EXT-X-MEDIA-SEQUENCE")) {
                    iB2 = b(strB, g);
                    i5 = iB2;
                } else if (strB.startsWith("#EXT-X-VERSION")) {
                    iB = b(strB, f9859e);
                } else if (strB.startsWith("#EXTINF")) {
                    jC2 = (long) (c(strB, h) * 1000000.0d);
                } else if (strB.startsWith("#EXT-X-KEY")) {
                    zEquals = "AES-128".equals(a(strB, l));
                    if (zEquals) {
                        strA = a(strB, m);
                        strD = d(strB, n);
                    }
                } else if (strB.startsWith("#EXT-X-BYTERANGE")) {
                    String[] strArrSplit2 = a(strB, j).split(CellDataManager.VIRTUAL_COMPONENT_SEPRATOR);
                    j4 = Long.parseLong(strArrSplit2[c2]);
                    if (strArrSplit2.length > 1) {
                        j2 = Long.parseLong(strArrSplit2[1]);
                    }
                } else if (strB.startsWith("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                    i4 = Integer.parseInt(strB.substring(strB.indexOf(58) + 1));
                    z = true;
                } else if (strB.equals("#EXT-X-DISCONTINUITY")) {
                    i2++;
                } else {
                    if (strB.startsWith("#EXT-X-PROGRAM-DATE-TIME")) {
                        if (jB2 == 0) {
                            jB2 = b20.b(x80.f(strB.substring(strB.indexOf(58) + 1))) - j3;
                        }
                    } else if (strB.startsWith("#EXT-X-DATERANGE")) {
                        arrayList2.add(strB);
                    } else if (!strB.startsWith("#")) {
                        if (zEquals) {
                            hexString = strD != null ? strD : Integer.toHexString(iB2);
                        } else {
                            hexString = null;
                        }
                        int i6 = iB2 + 1;
                        if (j4 == -1) {
                            j2 = 0;
                        }
                        arrayList.add(new wa0.a(strB, jC2, i2, j3, zEquals, strA, hexString, j2, j4));
                        j3 += jC2;
                        if (j4 != -1) {
                            j2 += j4;
                        }
                        iB2 = i6;
                        jC2 = 0;
                        j4 = -1;
                    } else if (strB.equals("#EXT-X-ENDLIST")) {
                        c2 = 0;
                        z2 = true;
                    }
                    c2 = 0;
                }
            }
            return new wa0(i3, str, jC, jB2, z, i4, i5, iB, jB, z2, jB2 != 0, aVar2, arrayList, arrayList2);
        }
    }

    public static int a(String str) {
        return (a(str, t, false) ? 1 : 0) | (a(str, u, false) ? 2 : 0) | (a(str, s, false) ? 4 : 0);
    }

    public static String a(String str, Pattern pattern) throws com.google.android.exoplayer2.n {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find() && matcher.groupCount() == 1) {
            return matcher.group(1);
        }
        throw new com.google.android.exoplayer2.n("Couldn't match " + pattern.pattern() + " in " + str);
    }

    public static boolean a(String str, Pattern pattern, boolean z) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group(1).equals("YES") : z;
    }

    public static int b(String str, Pattern pattern) throws com.google.android.exoplayer2.n {
        return Integer.parseInt(a(str, pattern));
    }

    public static Pattern b(String str) {
        return Pattern.compile(str + "=(NO|YES)");
    }
}
