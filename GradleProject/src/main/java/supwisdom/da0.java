package supwisdom;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.c.a;
import com.lzy.okgo.model.HttpHeaders;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import supwisdom.c80;
import supwisdom.ia0;

/* JADX INFO: compiled from: DashManifestParser.java */
/* JADX INFO: loaded from: classes.dex */
public class da0 extends DefaultHandler implements c80.a<ca0> {
    public static final Pattern c = Pattern.compile("(\\d+)(?:/(\\d+))?");
    public static final Pattern d = Pattern.compile("CC([1-4])=.*");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Pattern f7318e = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7319a;
    public final XmlPullParserFactory b;

    /* JADX INFO: compiled from: DashManifestParser.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final com.google.android.exoplayer2.j f7320a;
        public final String b;
        public final ia0 c;
        public final ArrayList<a.C0050a> d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final ArrayList<ha0> f7321e;

        public a(com.google.android.exoplayer2.j jVar, String str, ia0 ia0Var, ArrayList<a.C0050a> arrayList, ArrayList<ha0> arrayList2) {
            this.f7320a = jVar;
            this.b = str;
            this.c = ia0Var;
            this.d = arrayList;
            this.f7321e = arrayList2;
        }
    }

    public da0() {
        this(null);
    }

    @Override // supwisdom.c80.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ca0 a(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser xmlPullParserNewPullParser = this.b.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStream, null);
            if (xmlPullParserNewPullParser.next() == 2 && "MPD".equals(xmlPullParserNewPullParser.getName())) {
                return a(xmlPullParserNewPullParser, uri.toString());
            }
            throw new com.google.android.exoplayer2.n("inputStream does not contain a valid media presentation description");
        } catch (XmlPullParserException e2) {
            throw new com.google.android.exoplayer2.n(e2);
        }
    }

    public a.C0050a c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        boolean zEquals = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95".equals(xmlPullParser.getAttributeValue(null, "schemeIdUri"));
        byte[] bArrA = null;
        UUID uuidA = null;
        boolean z = false;
        do {
            xmlPullParser.next();
            if (bArrA == null && y80.b(xmlPullParser, "cenc:pssh") && xmlPullParser.next() == 4) {
                bArrA = Base64.decode(xmlPullParser.getText(), 0);
                uuidA = f30.a(bArrA);
                if (uuidA == null) {
                    Log.w("MpdParser", "Skipping malformed cenc:pssh data");
                    bArrA = null;
                }
            } else if (bArrA == null && zEquals && y80.b(xmlPullParser, "mspr:pro") && xmlPullParser.next() == 4) {
                bArrA = f30.a(b20.c, Base64.decode(xmlPullParser.getText(), 0));
                uuidA = b20.c;
            } else if (y80.b(xmlPullParser, "widevine:license")) {
                String attributeValue = xmlPullParser.getAttributeValue(null, "robustness_level");
                z = attributeValue != null && attributeValue.startsWith("HW");
            }
        } while (!y80.a(xmlPullParser, "ContentProtection"));
        if (bArrA != null) {
            return new a.C0050a(uuidA, "video/mp4", bArrA, z);
        }
        return null;
    }

    public ha0 d(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return b(xmlPullParser, "InbandEventStream");
    }

    public ha0 e(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return b(xmlPullParser, "Accessibility");
    }

    public int f(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String strB = b(xmlPullParser, "schemeIdUri", (String) null);
        String strB2 = b(xmlPullParser, "value", (String) null);
        do {
            xmlPullParser.next();
        } while (!y80.a(xmlPullParser, "Role"));
        return ("urn:mpeg:dash:role:2011".equals(strB) && "main".equals(strB2)) ? 1 : 0;
    }

    public void g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
    }

    public List<ia0.d> h(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        long jD = 0;
        do {
            xmlPullParser.next();
            if (y80.b(xmlPullParser, "S")) {
                jD = d(xmlPullParser, "t", jD);
                long jD2 = d(xmlPullParser, cn.com.chinatelecom.account.api.a.d.f1473a, -9223372036854775807L);
                int iA = a(xmlPullParser, "r", 0) + 1;
                for (int i = 0; i < iA; i++) {
                    arrayList.add(a(jD, jD2));
                    jD += jD2;
                }
            }
        } while (!y80.a(xmlPullParser, "SegmentTimeline"));
        return arrayList;
    }

    public fa0 i(XmlPullParser xmlPullParser) {
        return a(xmlPullParser, "sourceURL", AbsoluteConst.PULL_REFRESH_RANGE);
    }

    public fa0 j(XmlPullParser xmlPullParser) {
        return a(xmlPullParser, "media", "mediaRange");
    }

    public int k(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int iA = "urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(b(xmlPullParser, "schemeIdUri", (String) null)) ? a(xmlPullParser, "value", -1) : -1;
        do {
            xmlPullParser.next();
        } while (!y80.a(xmlPullParser, "AudioChannelConfiguration"));
        return iA;
    }

    public da0(String str) {
        this.f7319a = str;
        try {
            this.b = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e2) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e2);
        }
    }

    public static long d(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Long.parseLong(attributeValue);
    }

    public ca0 a(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        long j;
        la0 la0VarA;
        long jC = c(xmlPullParser, "availabilityStartTime", -9223372036854775807L);
        long jB = b(xmlPullParser, "mediaPresentationDuration", -9223372036854775807L);
        long jB2 = b(xmlPullParser, "minBufferTime", -9223372036854775807L);
        String attributeValue = xmlPullParser.getAttributeValue(null, "type");
        boolean z = attributeValue != null && attributeValue.equals("dynamic");
        long jB3 = z ? b(xmlPullParser, "minimumUpdatePeriod", -9223372036854775807L) : -9223372036854775807L;
        long jB4 = z ? b(xmlPullParser, "timeShiftBufferDepth", -9223372036854775807L) : -9223372036854775807L;
        long jB5 = z ? b(xmlPullParser, "suggestedPresentationDelay", -9223372036854775807L) : -9223372036854775807L;
        ArrayList arrayList = new ArrayList();
        long j2 = z ? -9223372036854775807L : 0L;
        boolean z2 = false;
        boolean z3 = false;
        Uri uri = null;
        String strC = str;
        la0 la0Var = null;
        while (true) {
            xmlPullParser.next();
            if (y80.b(xmlPullParser, "BaseURL")) {
                if (!z2) {
                    strC = c(xmlPullParser, strC);
                    j = jB3;
                    z2 = true;
                    la0VarA = la0Var;
                }
                j = jB3;
                la0VarA = la0Var;
                j2 = j2;
            } else if (y80.b(xmlPullParser, "UTCTiming")) {
                la0VarA = a(xmlPullParser);
                j = jB3;
            } else {
                if (y80.b(xmlPullParser, HttpHeaders.HEAD_KEY_LOCATION)) {
                    uri = Uri.parse(xmlPullParser.nextText());
                    j = jB3;
                } else if (!y80.b(xmlPullParser, "Period") || z3) {
                    j = jB3;
                    la0VarA = la0Var;
                    j2 = j2;
                } else {
                    Pair<ea0, Long> pairA = a(xmlPullParser, strC, j2);
                    ea0 ea0Var = (ea0) pairA.first;
                    long j3 = j2;
                    if (ea0Var.b != -9223372036854775807L) {
                        long jLongValue = ((Long) pairA.second).longValue();
                        j = jB3;
                        j2 = jLongValue == -9223372036854775807L ? -9223372036854775807L : jLongValue + ea0Var.b;
                        arrayList.add(ea0Var);
                    } else {
                        if (!z) {
                            throw new com.google.android.exoplayer2.n("Unable to determine start of period " + arrayList.size());
                        }
                        la0VarA = la0Var;
                        j = jB3;
                        j2 = j3;
                        z3 = true;
                    }
                }
                la0VarA = la0Var;
            }
            if (y80.a(xmlPullParser, "MPD")) {
                if (jB != -9223372036854775807L) {
                    j2 = jB;
                } else if (j2 == -9223372036854775807L) {
                    if (!z) {
                        throw new com.google.android.exoplayer2.n("Unable to determine duration of static manifest.");
                    }
                    j2 = jB;
                }
                if (arrayList.isEmpty()) {
                    throw new com.google.android.exoplayer2.n("No periods found.");
                }
                return a(jC, j2, jB2, z, j, jB4, jB5, la0VarA, uri, arrayList);
            }
            la0Var = la0VarA;
            jB3 = j;
        }
    }

    public int b(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "contentType");
        if (TextUtils.isEmpty(attributeValue)) {
            return -1;
        }
        if ("audio".equals(attributeValue)) {
            return 1;
        }
        if ("video".equals(attributeValue)) {
            return 2;
        }
        return "text".equals(attributeValue) ? 3 : -1;
    }

    public static String b(String str, String str2) {
        if (l80.a(str)) {
            return l80.e(str2);
        }
        if (l80.b(str)) {
            return l80.d(str2);
        }
        if (a(str)) {
            return str;
        }
        if ("application/mp4".equals(str)) {
            if ("stpp".equals(str2)) {
                return "application/ttml+xml";
            }
            if ("wvtt".equals(str2)) {
                return "application/x-mp4-vtt";
            }
        } else if ("application/x-rawcc".equals(str) && str2 != null) {
            if (str2.contains("cea708")) {
                return "application/cea-708";
            }
            if (str2.contains("eia608") || str2.contains("cea608")) {
                return "application/cea-608";
            }
        }
        return null;
    }

    public static String c(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        e80.b(str.equals(str2));
        return str;
    }

    public static long c(XmlPullParser xmlPullParser, String str, long j) throws com.google.android.exoplayer2.n {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : x80.f(attributeValue);
    }

    public static String c(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.next();
        return w80.b(str, xmlPullParser.getText());
    }

    public static ha0 b(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String strB = b(xmlPullParser, "schemeIdUri", (String) null);
        String strB2 = b(xmlPullParser, "value", (String) null);
        do {
            xmlPullParser.next();
        } while (!y80.a(xmlPullParser, str));
        return new ha0(strB, strB2);
    }

    public static int b(List<ha0> list) {
        String str;
        for (int i = 0; i < list.size(); i++) {
            ha0 ha0Var = list.get(i);
            if ("urn:scte:dash:cc:cea-708:2015".equals(ha0Var.f7819a) && (str = ha0Var.b) != null) {
                Matcher matcher = f7318e.matcher(str);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w("MpdParser", "Unable to parse CEA-708 service block number from: " + ha0Var.b);
            }
        }
        return -1;
    }

    public ca0 a(long j, long j2, long j3, boolean z, long j4, long j5, long j6, la0 la0Var, Uri uri, List<ea0> list) {
        return new ca0(j, j2, j3, z, j4, j5, j6, la0Var, uri, list);
    }

    public la0 a(XmlPullParser xmlPullParser) {
        return a(xmlPullParser.getAttributeValue(null, "schemeIdUri"), xmlPullParser.getAttributeValue(null, "value"));
    }

    public la0 a(String str, String str2) {
        return new la0(str, str2);
    }

    public static long b(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : x80.e(attributeValue);
    }

    public Pair<ea0, Long> a(XmlPullParser xmlPullParser, String str, long j) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "id");
        long jB = b(xmlPullParser, "start", j);
        long jB2 = b(xmlPullParser, "duration", -9223372036854775807L);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        ia0 ia0VarA = null;
        do {
            xmlPullParser.next();
            if (y80.b(xmlPullParser, "BaseURL")) {
                if (!z) {
                    str = c(xmlPullParser, str);
                    z = true;
                }
            } else if (y80.b(xmlPullParser, "AdaptationSet")) {
                arrayList.add(a(xmlPullParser, str, ia0VarA));
            } else if (y80.b(xmlPullParser, "SegmentBase")) {
                ia0VarA = a(xmlPullParser, (ia0.e) null);
            } else if (y80.b(xmlPullParser, "SegmentList")) {
                ia0VarA = a(xmlPullParser, (ia0.b) null);
            } else if (y80.b(xmlPullParser, "SegmentTemplate")) {
                ia0VarA = a(xmlPullParser, (ia0.c) null);
            }
        } while (!y80.a(xmlPullParser, "Period"));
        return Pair.create(a(attributeValue, jB, arrayList), Long.valueOf(jB2));
    }

    public static String b(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    public ea0 a(String str, long j, List<ba0> list) {
        return new ea0(str, j, list);
    }

    public ba0 a(XmlPullParser xmlPullParser, String str, ia0 ia0Var) throws XmlPullParserException, IOException {
        String strC;
        String str2;
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList<a.C0050a> arrayList3;
        String str3;
        String str4;
        int i;
        XmlPullParser xmlPullParser2;
        int i2;
        ArrayList<ha0> arrayList4;
        ia0 ia0VarA;
        XmlPullParser xmlPullParser3 = xmlPullParser;
        int iA = a(xmlPullParser3, "id", -1);
        int iB = b(xmlPullParser);
        String str5 = null;
        String attributeValue = xmlPullParser3.getAttributeValue(null, "mimeType");
        String attributeValue2 = xmlPullParser3.getAttributeValue(null, "codecs");
        int iA2 = a(xmlPullParser3, "width", -1);
        int iA3 = a(xmlPullParser3, "height", -1);
        float fA = a(xmlPullParser3, -1.0f);
        int iA4 = a(xmlPullParser3, "audioSamplingRate", -1);
        String str6 = AbsoluteConst.JSON_KEY_LANG;
        String attributeValue3 = xmlPullParser3.getAttributeValue(null, AbsoluteConst.JSON_KEY_LANG);
        ArrayList<a.C0050a> arrayList5 = new ArrayList<>();
        ArrayList<ha0> arrayList6 = new ArrayList<>();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        String strC2 = str;
        ia0 ia0Var2 = ia0Var;
        int iA5 = iB;
        String str7 = attributeValue3;
        int iK = -1;
        boolean z = false;
        int iF = 0;
        while (true) {
            xmlPullParser.next();
            if (y80.b(xmlPullParser3, "BaseURL")) {
                if (!z) {
                    z = true;
                    strC2 = c(xmlPullParser3, strC2);
                    strC = str7;
                    arrayList = arrayList8;
                    arrayList2 = arrayList7;
                    arrayList4 = arrayList6;
                    arrayList3 = arrayList5;
                    str3 = str6;
                    str4 = str5;
                    i = iA;
                    xmlPullParser2 = xmlPullParser3;
                }
                strC = str7;
                i2 = iA5;
                str2 = strC2;
                arrayList = arrayList8;
                arrayList2 = arrayList7;
                arrayList4 = arrayList6;
                arrayList3 = arrayList5;
                str3 = str6;
                str4 = str5;
                i = iA;
                xmlPullParser2 = xmlPullParser3;
                iA5 = i2;
                strC2 = str2;
            } else {
                if (y80.b(xmlPullParser3, "ContentProtection")) {
                    a.C0050a c0050aC = c(xmlPullParser);
                    if (c0050aC != null) {
                        arrayList5.add(c0050aC);
                    }
                } else if (y80.b(xmlPullParser3, "ContentComponent")) {
                    strC = c(str7, xmlPullParser3.getAttributeValue(str5, str6));
                    iA5 = a(iA5, b(xmlPullParser));
                    arrayList = arrayList8;
                    arrayList2 = arrayList7;
                    arrayList4 = arrayList6;
                    arrayList3 = arrayList5;
                    str3 = str6;
                    str4 = str5;
                    i = iA;
                    xmlPullParser2 = xmlPullParser3;
                } else {
                    if (y80.b(xmlPullParser3, "Role")) {
                        iF |= f(xmlPullParser);
                    } else if (y80.b(xmlPullParser3, "AudioChannelConfiguration")) {
                        iK = k(xmlPullParser);
                    } else if (y80.b(xmlPullParser3, "Accessibility")) {
                        arrayList7.add(e(xmlPullParser));
                    } else if (y80.b(xmlPullParser3, "Representation")) {
                        strC = str7;
                        arrayList2 = arrayList7;
                        arrayList3 = arrayList5;
                        str3 = str6;
                        str4 = str5;
                        i = iA;
                        a aVarA = a(xmlPullParser, strC2, attributeValue, attributeValue2, iA2, iA3, fA, iK, iA4, strC, iF, arrayList2, ia0Var2);
                        int iA6 = a(iA5, a(aVarA.f7320a));
                        arrayList = arrayList8;
                        arrayList.add(aVarA);
                        iA5 = iA6;
                        strC2 = strC2;
                        arrayList4 = arrayList6;
                        xmlPullParser2 = xmlPullParser;
                    } else {
                        strC = str7;
                        str2 = strC2;
                        arrayList = arrayList8;
                        arrayList2 = arrayList7;
                        ArrayList<ha0> arrayList9 = arrayList6;
                        arrayList3 = arrayList5;
                        str3 = str6;
                        str4 = str5;
                        i = iA;
                        xmlPullParser2 = xmlPullParser;
                        i2 = iA5;
                        if (y80.b(xmlPullParser2, "SegmentBase")) {
                            ia0VarA = a(xmlPullParser2, (ia0.e) ia0Var2);
                        } else if (y80.b(xmlPullParser2, "SegmentList")) {
                            ia0VarA = a(xmlPullParser2, (ia0.b) ia0Var2);
                        } else if (y80.b(xmlPullParser2, "SegmentTemplate")) {
                            ia0VarA = a(xmlPullParser2, (ia0.c) ia0Var2);
                        } else {
                            if (y80.b(xmlPullParser2, "InbandEventStream")) {
                                arrayList4 = arrayList9;
                                arrayList4.add(d(xmlPullParser));
                            } else {
                                arrayList4 = arrayList9;
                                if (y80.b(xmlPullParser)) {
                                    g(xmlPullParser);
                                }
                            }
                            iA5 = i2;
                            strC2 = str2;
                        }
                        ia0Var2 = ia0VarA;
                        iA5 = i2;
                        strC2 = str2;
                        arrayList4 = arrayList9;
                    }
                    strC = str7;
                    arrayList = arrayList8;
                    arrayList2 = arrayList7;
                    arrayList4 = arrayList6;
                    arrayList3 = arrayList5;
                    str3 = str6;
                    str4 = str5;
                    i = iA;
                    xmlPullParser2 = xmlPullParser3;
                }
                strC = str7;
                i2 = iA5;
                str2 = strC2;
                arrayList = arrayList8;
                arrayList2 = arrayList7;
                arrayList4 = arrayList6;
                arrayList3 = arrayList5;
                str3 = str6;
                str4 = str5;
                i = iA;
                xmlPullParser2 = xmlPullParser3;
                iA5 = i2;
                strC2 = str2;
            }
            if (y80.a(xmlPullParser2, "AdaptationSet")) {
                break;
            }
            arrayList7 = arrayList2;
            xmlPullParser3 = xmlPullParser2;
            arrayList8 = arrayList;
            arrayList6 = arrayList4;
            str7 = strC;
            arrayList5 = arrayList3;
            str6 = str3;
            str5 = str4;
            iA = i;
        }
        List<ga0> arrayList10 = new ArrayList<>(arrayList.size());
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            arrayList10.add(a((a) arrayList.get(i3), this.f7319a, arrayList3, arrayList4));
        }
        return a(i, iA5, arrayList10, arrayList2);
    }

    public ba0 a(int i, int i2, List<ga0> list, List<ha0> list2) {
        return new ba0(i, i2, list, list2);
    }

    public int a(com.google.android.exoplayer2.j jVar) {
        String str = jVar.f;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (l80.b(str)) {
            return 2;
        }
        if (l80.a(str)) {
            return 1;
        }
        return a(str) ? 3 : -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0107 A[LOOP:0: B:3:0x0052->B:38:0x0107, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00de A[EDGE_INSN: B:39:0x00de->B:32:0x00de BREAK  A[LOOP:0: B:3:0x0052->B:38:0x0107], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public supwisdom.da0.a a(org.xmlpull.v1.XmlPullParser r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, int r23, int r24, float r25, int r26, int r27, java.lang.String r28, int r29, java.util.List<supwisdom.ha0> r30, supwisdom.ia0 r31) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.da0.a(org.xmlpull.v1.XmlPullParser, java.lang.String, java.lang.String, java.lang.String, int, int, float, int, int, java.lang.String, int, java.util.List, supwisdom.ia0):supwisdom.da0$a");
    }

    public com.google.android.exoplayer2.j a(String str, String str2, int i, int i2, float f, int i3, int i4, int i5, String str3, int i6, List<ha0> list, String str4) {
        int i7;
        int iB;
        String strB = b(str2, str4);
        if (strB != null) {
            if (l80.b(strB)) {
                return com.google.android.exoplayer2.j.a(str, str2, strB, str4, i5, i, i2, f, (List<byte[]>) null, i6);
            }
            if (l80.a(strB)) {
                return com.google.android.exoplayer2.j.a(str, str2, strB, str4, i5, i3, i4, (List<byte[]>) null, i6, str3);
            }
            if (a(strB)) {
                if ("application/cea-608".equals(strB)) {
                    iB = a(list);
                } else if ("application/cea-708".equals(strB)) {
                    iB = b(list);
                } else {
                    i7 = -1;
                    return com.google.android.exoplayer2.j.a(str, str2, strB, str4, i5, i6, str3, i7);
                }
                i7 = iB;
                return com.google.android.exoplayer2.j.a(str, str2, strB, str4, i5, i6, str3, i7);
            }
        }
        return com.google.android.exoplayer2.j.b(str, str2, strB, str4, i5, i6, str3);
    }

    public ga0 a(a aVar, String str, ArrayList<a.C0050a> arrayList, ArrayList<ha0> arrayList2) {
        com.google.android.exoplayer2.j jVarA = aVar.f7320a;
        ArrayList<a.C0050a> arrayList3 = aVar.d;
        arrayList3.addAll(arrayList);
        if (!arrayList3.isEmpty()) {
            jVarA = jVarA.a(new com.google.android.exoplayer2.c.a(arrayList3));
        }
        ArrayList<ha0> arrayList4 = aVar.f7321e;
        arrayList4.addAll(arrayList2);
        return ga0.a(str, -1L, jVarA, aVar.b, aVar.c, arrayList4);
    }

    public ia0.e a(XmlPullParser xmlPullParser, ia0.e eVar) throws XmlPullParserException, IOException {
        long j;
        long j2;
        long jD = d(xmlPullParser, "timescale", eVar != null ? eVar.b : 1L);
        long jD2 = d(xmlPullParser, "presentationTimeOffset", eVar != null ? eVar.c : 0L);
        long j3 = eVar != null ? eVar.d : 0L;
        long j4 = eVar != null ? eVar.f7933e : 0L;
        String attributeValue = xmlPullParser.getAttributeValue(null, "indexRange");
        if (attributeValue != null) {
            String[] strArrSplit = attributeValue.split("-");
            long j5 = Long.parseLong(strArrSplit[0]);
            j = (Long.parseLong(strArrSplit[1]) - j5) + 1;
            j2 = j5;
        } else {
            j = j4;
            j2 = j3;
        }
        fa0 fa0VarI = eVar != null ? eVar.f7930a : null;
        do {
            xmlPullParser.next();
            if (y80.b(xmlPullParser, "Initialization")) {
                fa0VarI = i(xmlPullParser);
            }
        } while (!y80.a(xmlPullParser, "SegmentBase"));
        return a(fa0VarI, jD, jD2, j2, j);
    }

    public ia0.e a(fa0 fa0Var, long j, long j2, long j3, long j4) {
        return new ia0.e(fa0Var, j, j2, j3, j4);
    }

    public ia0.b a(XmlPullParser xmlPullParser, ia0.b bVar) throws XmlPullParserException, IOException {
        long jD = d(xmlPullParser, "timescale", bVar != null ? bVar.b : 1L);
        long jD2 = d(xmlPullParser, "presentationTimeOffset", bVar != null ? bVar.c : 0L);
        long jD3 = d(xmlPullParser, "duration", bVar != null ? bVar.f7931e : -9223372036854775807L);
        int iA = a(xmlPullParser, "startNumber", bVar != null ? bVar.d : 1);
        List<fa0> arrayList = null;
        fa0 fa0VarI = null;
        List<ia0.d> listH = null;
        do {
            xmlPullParser.next();
            if (y80.b(xmlPullParser, "Initialization")) {
                fa0VarI = i(xmlPullParser);
            } else if (y80.b(xmlPullParser, "SegmentTimeline")) {
                listH = h(xmlPullParser);
            } else if (y80.b(xmlPullParser, "SegmentURL")) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(j(xmlPullParser));
            }
        } while (!y80.a(xmlPullParser, "SegmentList"));
        if (bVar != null) {
            if (fa0VarI == null) {
                fa0VarI = bVar.f7930a;
            }
            if (listH == null) {
                listH = bVar.f;
            }
            if (arrayList == null) {
                arrayList = bVar.g;
            }
        }
        return a(fa0VarI, jD, jD2, iA, jD3, listH, arrayList);
    }

    public ia0.b a(fa0 fa0Var, long j, long j2, int i, long j3, List<ia0.d> list, List<fa0> list2) {
        return new ia0.b(fa0Var, j, j2, i, j3, list, list2);
    }

    public ia0.c a(XmlPullParser xmlPullParser, ia0.c cVar) throws XmlPullParserException, IOException {
        long jD = d(xmlPullParser, "timescale", cVar != null ? cVar.b : 1L);
        long jD2 = d(xmlPullParser, "presentationTimeOffset", cVar != null ? cVar.c : 0L);
        long jD3 = d(xmlPullParser, "duration", cVar != null ? cVar.f7931e : -9223372036854775807L);
        int iA = a(xmlPullParser, "startNumber", cVar != null ? cVar.d : 1);
        fa0 fa0VarI = null;
        ka0 ka0VarA = a(xmlPullParser, "media", cVar != null ? cVar.h : null);
        ka0 ka0VarA2 = a(xmlPullParser, "initialization", cVar != null ? cVar.g : null);
        List<ia0.d> listH = null;
        do {
            xmlPullParser.next();
            if (y80.b(xmlPullParser, "Initialization")) {
                fa0VarI = i(xmlPullParser);
            } else if (y80.b(xmlPullParser, "SegmentTimeline")) {
                listH = h(xmlPullParser);
            }
        } while (!y80.a(xmlPullParser, "SegmentTemplate"));
        if (cVar != null) {
            if (fa0VarI == null) {
                fa0VarI = cVar.f7930a;
            }
            if (listH == null) {
                listH = cVar.f;
            }
        }
        return a(fa0VarI, jD, jD2, iA, jD3, listH, ka0VarA2, ka0VarA);
    }

    public ia0.c a(fa0 fa0Var, long j, long j2, int i, long j3, List<ia0.d> list, ka0 ka0Var, ka0 ka0Var2) {
        return new ia0.c(fa0Var, j, j2, i, j3, list, ka0Var, ka0Var2);
    }

    public ia0.d a(long j, long j2) {
        return new ia0.d(j, j2);
    }

    public ka0 a(XmlPullParser xmlPullParser, String str, ka0 ka0Var) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue != null ? ka0.a(attributeValue) : ka0Var;
    }

    public fa0 a(XmlPullParser xmlPullParser, String str, String str2) {
        long j;
        long j2;
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, str2);
        if (attributeValue2 != null) {
            String[] strArrSplit = attributeValue2.split("-");
            j = Long.parseLong(strArrSplit[0]);
            if (strArrSplit.length == 2) {
                j2 = (Long.parseLong(strArrSplit[1]) - j) + 1;
            }
            return a(attributeValue, j, j2);
        }
        j = 0;
        j2 = -1;
        return a(attributeValue, j, j2);
    }

    public fa0 a(String str, long j, long j2) {
        return new fa0(str, j, j2);
    }

    public static boolean a(String str) {
        return l80.c(str) || "application/ttml+xml".equals(str) || "application/x-mp4-vtt".equals(str) || "application/cea-708".equals(str) || "application/cea-608".equals(str);
    }

    public static int a(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        e80.b(i == i2);
        return i;
    }

    public static int a(List<ha0> list) {
        String str;
        for (int i = 0; i < list.size(); i++) {
            ha0 ha0Var = list.get(i);
            if ("urn:scte:dash:cc:cea-608:2015".equals(ha0Var.f7819a) && (str = ha0Var.b) != null) {
                Matcher matcher = d.matcher(str);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w("MpdParser", "Unable to parse CEA-608 channel number from: " + ha0Var.b);
            }
        }
        return -1;
    }

    public static float a(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = c.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int i = Integer.parseInt(matcher.group(1));
        return !TextUtils.isEmpty(matcher.group(2)) ? i / Integer.parseInt(r2) : i;
    }

    public static int a(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? i : Integer.parseInt(attributeValue);
    }
}
