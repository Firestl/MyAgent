package supwisdom;

import android.util.Log;
import com.lzy.okgo.cache.CacheEntity;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.taobao.weex.ui.component.richtext.node.SpanNode;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: compiled from: TtmlDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class g60 extends c60 {
    public static final Pattern o = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    public static final Pattern p = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    public static final Pattern q = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    public static final Pattern r = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    public static final a s = new a(30.0f, 1, 1);
    public final XmlPullParserFactory n;

    /* JADX INFO: compiled from: TtmlDecoder.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float f7689a;
        public final int b;
        public final int c;

        public a(float f, int i, int i2) {
            this.f7689a = f;
            this.b = i;
            this.c = i2;
        }
    }

    public g60() {
        super("TtmlDecoder");
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            this.n = xmlPullParserFactoryNewInstance;
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e2) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e2);
        }
    }

    @Override // supwisdom.c60
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public l60 a(byte[] bArr, int i, boolean z) throws com.google.android.exoplayer2.g.f {
        try {
            XmlPullParser xmlPullParserNewPullParser = this.n.newPullParser();
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            l60 l60Var = null;
            map2.put("", new i60(null));
            int i2 = 0;
            xmlPullParserNewPullParser.setInput(new ByteArrayInputStream(bArr, 0, i), null);
            LinkedList linkedList = new LinkedList();
            a aVarA = s;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.getEventType()) {
                h60 h60Var = (h60) linkedList.peekLast();
                if (i2 == 0) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (eventType == 2) {
                        if ("tt".equals(name)) {
                            aVarA = a(xmlPullParserNewPullParser);
                        }
                        if (!b(name)) {
                            Log.i("TtmlDecoder", "Ignoring unsupported tag: " + xmlPullParserNewPullParser.getName());
                        } else if (CacheEntity.HEAD.equals(name)) {
                            a(xmlPullParserNewPullParser, map, map2);
                        } else {
                            try {
                                h60 h60VarA = a(xmlPullParserNewPullParser, h60Var, map2, aVarA);
                                linkedList.addLast(h60VarA);
                                if (h60Var != null) {
                                    h60Var.a(h60VarA);
                                }
                            } catch (com.google.android.exoplayer2.g.f e2) {
                                Log.w("TtmlDecoder", "Suppressing parser error", e2);
                                i2++;
                            }
                        }
                        i2++;
                    } else if (eventType == 4) {
                        h60Var.a(h60.a(xmlPullParserNewPullParser.getText()));
                    } else if (eventType == 3) {
                        if (xmlPullParserNewPullParser.getName().equals("tt")) {
                            l60Var = new l60((h60) linkedList.getLast(), map, map2);
                        }
                        linkedList.removeLast();
                    }
                } else if (eventType == 2) {
                    i2++;
                } else if (eventType == 3) {
                    i2--;
                }
                xmlPullParserNewPullParser.next();
            }
            return l60Var;
        } catch (IOException e3) {
            throw new IllegalStateException("Unexpected error when reading input.", e3);
        } catch (XmlPullParserException e4) {
            throw new com.google.android.exoplayer2.g.f("Unable to decode source", e4);
        }
    }

    public final a a(XmlPullParser xmlPullParser) throws com.google.android.exoplayer2.g.f {
        String attributeValue = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRate");
        int i = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        float f = 1.0f;
        String attributeValue2 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRateMultiplier");
        if (attributeValue2 != null) {
            if (attributeValue2.split(Operators.SPACE_STR).length != 2) {
                throw new com.google.android.exoplayer2.g.f("frameRateMultiplier doesn't have 2 parts");
            }
            f = Integer.parseInt(r2[0]) / Integer.parseInt(r2[1]);
        }
        int i2 = s.b;
        String attributeValue3 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "subFrameRate");
        if (attributeValue3 != null) {
            i2 = Integer.parseInt(attributeValue3);
        }
        int i3 = s.c;
        String attributeValue4 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "tickRate");
        if (attributeValue4 != null) {
            i3 = Integer.parseInt(attributeValue4);
        }
        return new a(i * f, i2, i3);
    }

    public final Map<String, k60> a(XmlPullParser xmlPullParser, Map<String, k60> map, Map<String, i60> map2) throws XmlPullParserException, IOException {
        i60 i60VarB;
        do {
            xmlPullParser.next();
            if (y80.b(xmlPullParser, "style")) {
                String strC = y80.c(xmlPullParser, "style");
                k60 k60VarA = a(xmlPullParser, new k60());
                if (strC != null) {
                    for (String str : a(strC)) {
                        k60VarA.a(map.get(str));
                    }
                }
                if (k60VarA.i() != null) {
                    map.put(k60VarA.i(), k60VarA);
                }
            } else if (y80.b(xmlPullParser, "region") && (i60VarB = b(xmlPullParser)) != null) {
                map2.put(i60VarB.f7917a, i60VarB);
            }
        } while (!y80.a(xmlPullParser, CacheEntity.HEAD));
        return map;
    }

    public final String[] a(String str) {
        return str.split("\\s+");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final supwisdom.k60 a(org.xmlpull.v1.XmlPullParser r12, supwisdom.k60 r13) {
        /*
            Method dump skipped, instruction units count: 612
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.g60.a(org.xmlpull.v1.XmlPullParser, supwisdom.k60):supwisdom.k60");
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final supwisdom.i60 b(org.xmlpull.v1.XmlPullParser r13) {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.g60.b(org.xmlpull.v1.XmlPullParser):supwisdom.i60");
    }

    public static boolean b(String str) {
        return str.equals("tt") || str.equals(CacheEntity.HEAD) || str.equals("body") || str.equals(WXBasicComponentType.DIV) || str.equals("p") || str.equals(SpanNode.NODE_TYPE) || str.equals("br") || str.equals("style") || str.equals("styling") || str.equals(Constants.Name.LAYOUT) || str.equals("region") || str.equals("metadata") || str.equals("smpte:image") || str.equals("smpte:data") || str.equals("smpte:information");
    }

    public final k60 a(k60 k60Var) {
        return k60Var == null ? new k60() : k60Var;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final supwisdom.h60 a(org.xmlpull.v1.XmlPullParser r20, supwisdom.h60 r21, java.util.Map<java.lang.String, supwisdom.i60> r22, supwisdom.g60.a r23) throws com.google.android.exoplayer2.g.f {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.g60.a(org.xmlpull.v1.XmlPullParser, supwisdom.h60, java.util.Map, supwisdom.g60$a):supwisdom.h60");
    }

    public static void a(String str, k60 k60Var) throws com.google.android.exoplayer2.g.f {
        Matcher matcher;
        String[] strArrSplit = str.split("\\s+");
        if (strArrSplit.length == 1) {
            matcher = q.matcher(str);
        } else if (strArrSplit.length == 2) {
            matcher = q.matcher(strArrSplit[1]);
            Log.w("TtmlDecoder", "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            throw new com.google.android.exoplayer2.g.f("Invalid number of entries for fontSize: " + strArrSplit.length + Operators.DOT_STR);
        }
        if (matcher.matches()) {
            String strGroup = matcher.group(3);
            byte b = -1;
            int iHashCode = strGroup.hashCode();
            if (iHashCode != 37) {
                if (iHashCode != 3240) {
                    if (iHashCode == 3592 && strGroup.equals("px")) {
                        b = 0;
                    }
                } else if (strGroup.equals(com.umeng.analytics.c.d)) {
                    b = 1;
                }
            } else if (strGroup.equals("%")) {
                b = 2;
            }
            if (b == 0) {
                k60Var.c(1);
            } else if (b == 1) {
                k60Var.c(2);
            } else if (b == 2) {
                k60Var.c(3);
            } else {
                throw new com.google.android.exoplayer2.g.f("Invalid unit for fontSize: '" + strGroup + "'.");
            }
            k60Var.a(Float.valueOf(matcher.group(1)).floatValue());
            return;
        }
        throw new com.google.android.exoplayer2.g.f("Invalid expression for fontSize: '" + str + "'.");
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long a(java.lang.String r14, supwisdom.g60.a r15) throws com.google.android.exoplayer2.g.f {
        /*
            Method dump skipped, instruction units count: 295
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.g60.a(java.lang.String, supwisdom.g60$a):long");
    }
}
