package supwisdom;

import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import supwisdom.u60;

/* JADX INFO: compiled from: WebvttCueParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class v60 {
    public static final Pattern b = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
    public static final Pattern c = Pattern.compile("(\\S+?):(\\S+)");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final StringBuilder f9482a = new StringBuilder();

    /* JADX INFO: compiled from: WebvttCueParser.java */
    public static final class b implements Comparable<b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9485a;
        public final t60 b;

        public b(int i, t60 t60Var) {
            this.f9485a = i;
            this.b = t60Var;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            return this.f9485a - bVar.f9485a;
        }
    }

    public static void b(String str, u60.b bVar) throws NumberFormatException {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf != -1) {
            bVar.b(a(str.substring(iIndexOf + 1)));
            str = str.substring(0, iIndexOf);
        } else {
            bVar.b(Integer.MIN_VALUE);
        }
        if (str.endsWith("%")) {
            bVar.a(x60.b(str));
            bVar.a(0);
            return;
        }
        int i = Integer.parseInt(str);
        if (i < 0) {
            i--;
        }
        bVar.a(i);
        bVar.a(1);
    }

    public static void c(String str, u60.b bVar) throws NumberFormatException {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf != -1) {
            bVar.c(a(str.substring(iIndexOf + 1)));
            str = str.substring(0, iIndexOf);
        } else {
            bVar.c(Integer.MIN_VALUE);
        }
        bVar.b(x60.b(str));
    }

    public static String d(String str) {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return null;
        }
        return strTrim.split("[ \\.]")[0];
    }

    public boolean a(o80 o80Var, u60.b bVar, List<t60> list) {
        String strY = o80Var.y();
        Matcher matcher = b.matcher(strY);
        if (matcher.matches()) {
            return a(null, matcher, o80Var, bVar, this.f9482a, list);
        }
        Matcher matcher2 = b.matcher(o80Var.y());
        if (matcher2.matches()) {
            return a(strY.trim(), matcher2, o80Var, bVar, this.f9482a, list);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean c(java.lang.String r8) {
        /*
            int r0 = r8.hashCode()
            r1 = 98
            r2 = 0
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r0 == r1) goto L56
            r1 = 99
            if (r0 == r1) goto L4c
            r1 = 105(0x69, float:1.47E-43)
            if (r0 == r1) goto L42
            r1 = 3314158(0x3291ee, float:4.644125E-39)
            if (r0 == r1) goto L38
            r1 = 117(0x75, float:1.64E-43)
            if (r0 == r1) goto L2e
            r1 = 118(0x76, float:1.65E-43)
            if (r0 == r1) goto L24
            goto L60
        L24:
            java.lang.String r0 = "v"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L60
            r8 = 5
            goto L61
        L2e:
            java.lang.String r0 = "u"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L60
            r8 = 4
            goto L61
        L38:
            java.lang.String r0 = "lang"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L60
            r8 = 3
            goto L61
        L42:
            java.lang.String r0 = "i"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L60
            r8 = 2
            goto L61
        L4c:
            java.lang.String r0 = "c"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L60
            r8 = 1
            goto L61
        L56:
            java.lang.String r0 = "b"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L60
            r8 = 0
            goto L61
        L60:
            r8 = -1
        L61:
            if (r8 == 0) goto L6e
            if (r8 == r7) goto L6e
            if (r8 == r6) goto L6e
            if (r8 == r5) goto L6e
            if (r8 == r4) goto L6e
            if (r8 == r3) goto L6e
            return r2
        L6e:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.v60.c(java.lang.String):boolean");
    }

    public static void a(String str, u60.b bVar) {
        Matcher matcher = c.matcher(str);
        while (matcher.find()) {
            String strGroup = matcher.group(1);
            String strGroup2 = matcher.group(2);
            try {
                if ("line".equals(strGroup)) {
                    b(strGroup2, bVar);
                } else if (AbsoluteConst.JSON_KEY_ALIGN.equals(strGroup)) {
                    bVar.a(b(strGroup2));
                } else if ("position".equals(strGroup)) {
                    c(strGroup2, bVar);
                } else if (AbsoluteConst.JSON_KEY_SIZE.equals(strGroup)) {
                    bVar.c(x60.b(strGroup2));
                } else {
                    Log.w("WebvttCueParser", "Unknown cue setting " + strGroup + Constants.COLON_SEPARATOR + strGroup2);
                }
            } catch (NumberFormatException unused) {
                Log.w("WebvttCueParser", "Skipping bad cue setting: " + matcher.group());
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.text.Layout.Alignment b(java.lang.String r6) {
        /*
            int r0 = r6.hashCode()
            r1 = 5
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r0) {
                case -1364013995: goto L3f;
                case -1074341483: goto L35;
                case 100571: goto L2b;
                case 3317767: goto L21;
                case 108511772: goto L17;
                case 109757538: goto Ld;
                default: goto Lc;
            }
        Lc:
            goto L49
        Ld:
            java.lang.String r0 = "start"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L49
            r0 = 0
            goto L4a
        L17:
            java.lang.String r0 = "right"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L49
            r0 = 5
            goto L4a
        L21:
            java.lang.String r0 = "left"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L49
            r0 = 1
            goto L4a
        L2b:
            java.lang.String r0 = "end"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L49
            r0 = 4
            goto L4a
        L35:
            java.lang.String r0 = "middle"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L49
            r0 = 3
            goto L4a
        L3f:
            java.lang.String r0 = "center"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L49
            r0 = 2
            goto L4a
        L49:
            r0 = -1
        L4a:
            if (r0 == 0) goto L74
            if (r0 == r5) goto L74
            if (r0 == r4) goto L71
            if (r0 == r3) goto L71
            if (r0 == r2) goto L6e
            if (r0 == r1) goto L6e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid alignment value: "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            java.lang.String r0 = "WebvttCueParser"
            android.util.Log.w(r0, r6)
            r6 = 0
            return r6
        L6e:
            android.text.Layout$Alignment r6 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            return r6
        L71:
            android.text.Layout$Alignment r6 = android.text.Layout.Alignment.ALIGN_CENTER
            return r6
        L74:
            android.text.Layout$Alignment r6 = android.text.Layout.Alignment.ALIGN_NORMAL
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.v60.b(java.lang.String):android.text.Layout$Alignment");
    }

    /* JADX INFO: compiled from: WebvttCueParser.java */
    public static final class a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final String[] f9483e = new String[0];

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f9484a;
        public final int b;
        public final String c;
        public final String[] d;

        public a(String str, int i, String str2, String[] strArr) {
            this.b = i;
            this.f9484a = str;
            this.c = str2;
            this.d = strArr;
        }

        public static a a(String str, int i) {
            String str2;
            String strTrim = str.trim();
            if (strTrim.isEmpty()) {
                return null;
            }
            int iIndexOf = strTrim.indexOf(Operators.SPACE_STR);
            if (iIndexOf == -1) {
                str2 = "";
            } else {
                String strTrim2 = strTrim.substring(iIndexOf).trim();
                strTrim = strTrim.substring(0, iIndexOf);
                str2 = strTrim2;
            }
            String[] strArrSplit = strTrim.split("\\.");
            return new a(strArrSplit[0], i, str2, strArrSplit.length > 1 ? (String[]) Arrays.copyOfRange(strArrSplit, 1, strArrSplit.length) : f9483e);
        }

        public static a a() {
            return new a("", 0, "", new String[0]);
        }
    }

    public static void a(String str, String str2, u60.b bVar, List<t60> list) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Stack stack = new Stack();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < str2.length()) {
            char cCharAt = str2.charAt(i);
            if (cCharAt == '&') {
                i++;
                int iIndexOf = str2.indexOf(59, i);
                int iIndexOf2 = str2.indexOf(32, i);
                if (iIndexOf == -1) {
                    iIndexOf = iIndexOf2;
                } else if (iIndexOf2 != -1) {
                    iIndexOf = Math.min(iIndexOf, iIndexOf2);
                }
                if (iIndexOf != -1) {
                    a(str2.substring(i, iIndexOf), spannableStringBuilder);
                    if (iIndexOf == iIndexOf2) {
                        spannableStringBuilder.append(Operators.SPACE_STR);
                    }
                    i = iIndexOf + 1;
                } else {
                    spannableStringBuilder.append(cCharAt);
                }
            } else if (cCharAt != '<') {
                spannableStringBuilder.append(cCharAt);
                i++;
            } else {
                int iA = i + 1;
                if (iA < str2.length()) {
                    boolean z = str2.charAt(iA) == '/';
                    iA = a(str2, iA);
                    int i2 = iA - 2;
                    boolean z2 = str2.charAt(i2) == '/';
                    int i3 = i + (z ? 2 : 1);
                    if (!z2) {
                        i2 = iA - 1;
                    }
                    String strSubstring = str2.substring(i3, i2);
                    String strD = d(strSubstring);
                    if (strD != null && c(strD)) {
                        if (z) {
                            while (!stack.isEmpty()) {
                                a aVar = (a) stack.pop();
                                a(str, aVar, spannableStringBuilder, list, arrayList);
                                if (aVar.f9484a.equals(strD)) {
                                    break;
                                }
                            }
                        } else if (!z2) {
                            stack.push(a.a(strSubstring, spannableStringBuilder.length()));
                        }
                    }
                }
                i = iA;
            }
        }
        while (!stack.isEmpty()) {
            a(str, (a) stack.pop(), spannableStringBuilder, list, arrayList);
        }
        a(str, a.a(), spannableStringBuilder, list, arrayList);
        bVar.a(spannableStringBuilder);
    }

    public static boolean a(String str, Matcher matcher, o80 o80Var, u60.b bVar, StringBuilder sb, List<t60> list) {
        try {
            bVar.a(x60.a(matcher.group(1)));
            bVar.b(x60.a(matcher.group(2)));
            a(matcher.group(3), bVar);
            sb.setLength(0);
            while (true) {
                String strY = o80Var.y();
                if (strY == null || strY.isEmpty()) {
                    break;
                }
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(strY.trim());
            }
            a(str, sb.toString(), bVar, list);
            return true;
        } catch (NumberFormatException unused) {
            Log.w("WebvttCueParser", "Skipping cue with bad header: " + matcher.group());
            return false;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(java.lang.String r5) {
        /*
            int r0 = r5.hashCode()
            r1 = 0
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r0) {
                case -1364013995: goto L2a;
                case -1074341483: goto L20;
                case 100571: goto L16;
                case 109757538: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L34
        Lc:
            java.lang.String r0 = "start"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L34
            r0 = 0
            goto L35
        L16:
            java.lang.String r0 = "end"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L34
            r0 = 3
            goto L35
        L20:
            java.lang.String r0 = "middle"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L34
            r0 = 2
            goto L35
        L2a:
            java.lang.String r0 = "center"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L34
            r0 = 1
            goto L35
        L34:
            r0 = -1
        L35:
            if (r0 == 0) goto L58
            if (r0 == r4) goto L57
            if (r0 == r3) goto L57
            if (r0 == r2) goto L56
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid anchor value: "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            java.lang.String r0 = "WebvttCueParser"
            android.util.Log.w(r0, r5)
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            return r5
        L56:
            return r3
        L57:
            return r4
        L58:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.v60.a(java.lang.String):int");
    }

    public static int a(String str, int i) {
        int iIndexOf = str.indexOf(62, i);
        return iIndexOf == -1 ? str.length() : iIndexOf + 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(java.lang.String r5, android.text.SpannableStringBuilder r6) {
        /*
            int r0 = r5.hashCode()
            r1 = 3309(0xced, float:4.637E-42)
            r2 = 3
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L38
            r1 = 3464(0xd88, float:4.854E-42)
            if (r0 == r1) goto L2e
            r1 = 96708(0x179c4, float:1.35517E-40)
            if (r0 == r1) goto L24
            r1 = 3374865(0x337f11, float:4.729193E-39)
            if (r0 == r1) goto L1a
            goto L42
        L1a:
            java.lang.String r0 = "nbsp"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L42
            r0 = 2
            goto L43
        L24:
            java.lang.String r0 = "amp"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L42
            r0 = 3
            goto L43
        L2e:
            java.lang.String r0 = "lt"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L42
            r0 = 0
            goto L43
        L38:
            java.lang.String r0 = "gt"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L42
            r0 = 1
            goto L43
        L42:
            r0 = -1
        L43:
            if (r0 == 0) goto L79
            if (r0 == r4) goto L73
            if (r0 == r3) goto L6d
            if (r0 == r2) goto L67
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = "ignoring unsupported entity: '&"
            r6.append(r0)
            r6.append(r5)
            java.lang.String r5 = ";'"
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            java.lang.String r6 = "WebvttCueParser"
            android.util.Log.w(r6, r5)
            goto L7e
        L67:
            r5 = 38
            r6.append(r5)
            goto L7e
        L6d:
            r5 = 32
            r6.append(r5)
            goto L7e
        L73:
            r5 = 62
            r6.append(r5)
            goto L7e
        L79:
            r5 = 60
            r6.append(r5)
        L7e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.v60.a(java.lang.String, android.text.SpannableStringBuilder):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(java.lang.String r8, supwisdom.v60.a r9, android.text.SpannableStringBuilder r10, java.util.List<supwisdom.t60> r11, java.util.List<supwisdom.v60.b> r12) {
        /*
            int r0 = r9.b
            int r1 = r10.length()
            java.lang.String r2 = r9.f9484a
            int r3 = r2.hashCode()
            r4 = 0
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L67
            r7 = 105(0x69, float:1.47E-43)
            if (r3 == r7) goto L5d
            r7 = 3314158(0x3291ee, float:4.644125E-39)
            if (r3 == r7) goto L53
            r7 = 98
            if (r3 == r7) goto L49
            r7 = 99
            if (r3 == r7) goto L3f
            r7 = 117(0x75, float:1.64E-43)
            if (r3 == r7) goto L35
            r7 = 118(0x76, float:1.65E-43)
            if (r3 == r7) goto L2b
            goto L71
        L2b:
            java.lang.String r3 = "v"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L71
            r2 = 5
            goto L72
        L35:
            java.lang.String r3 = "u"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L71
            r2 = 2
            goto L72
        L3f:
            java.lang.String r3 = "c"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L71
            r2 = 3
            goto L72
        L49:
            java.lang.String r3 = "b"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L71
            r2 = 0
            goto L72
        L53:
            java.lang.String r3 = "lang"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L71
            r2 = 4
            goto L72
        L5d:
            java.lang.String r3 = "i"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L71
            r2 = 1
            goto L72
        L67:
            java.lang.String r3 = ""
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L71
            r2 = 6
            goto L72
        L71:
            r2 = -1
        L72:
            r3 = 33
            switch(r2) {
                case 0: goto L8a;
                case 1: goto L81;
                case 2: goto L78;
                case 3: goto L92;
                case 4: goto L92;
                case 5: goto L92;
                case 6: goto L92;
                default: goto L77;
            }
        L77:
            return
        L78:
            android.text.style.UnderlineSpan r2 = new android.text.style.UnderlineSpan
            r2.<init>()
            r10.setSpan(r2, r0, r1, r3)
            goto L92
        L81:
            android.text.style.StyleSpan r2 = new android.text.style.StyleSpan
            r2.<init>(r5)
            r10.setSpan(r2, r0, r1, r3)
            goto L92
        L8a:
            android.text.style.StyleSpan r2 = new android.text.style.StyleSpan
            r2.<init>(r6)
            r10.setSpan(r2, r0, r1, r3)
        L92:
            r12.clear()
            a(r11, r8, r9, r12)
            int r8 = r12.size()
        L9c:
            if (r4 >= r8) goto Lac
            java.lang.Object r9 = r12.get(r4)
            supwisdom.v60$b r9 = (supwisdom.v60.b) r9
            supwisdom.t60 r9 = r9.b
            a(r10, r9, r0, r1)
            int r4 = r4 + 1
            goto L9c
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.v60.a(java.lang.String, supwisdom.v60$a, android.text.SpannableStringBuilder, java.util.List, java.util.List):void");
    }

    public static void a(SpannableStringBuilder spannableStringBuilder, t60 t60Var, int i, int i2) {
        if (t60Var == null) {
            return;
        }
        if (t60Var.b() != -1) {
            spannableStringBuilder.setSpan(new StyleSpan(t60Var.b()), i, i2, 33);
        }
        if (t60Var.c()) {
            spannableStringBuilder.setSpan(new StrikethroughSpan(), i, i2, 33);
        }
        if (t60Var.d()) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
        }
        if (t60Var.g()) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(t60Var.f()), i, i2, 33);
        }
        if (t60Var.i()) {
            spannableStringBuilder.setSpan(new BackgroundColorSpan(t60Var.h()), i, i2, 33);
        }
        if (t60Var.e() != null) {
            spannableStringBuilder.setSpan(new TypefaceSpan(t60Var.e()), i, i2, 33);
        }
        if (t60Var.j() != null) {
            spannableStringBuilder.setSpan(new AlignmentSpan.Standard(t60Var.j()), i, i2, 33);
        }
        int iK = t60Var.k();
        if (iK == 1) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) t60Var.l(), true), i, i2, 33);
        } else if (iK == 2) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(t60Var.l()), i, i2, 33);
        } else {
            if (iK != 3) {
                return;
            }
            spannableStringBuilder.setSpan(new RelativeSizeSpan(t60Var.l() / 100.0f), i, i2, 33);
        }
    }

    public static void a(List<t60> list, String str, a aVar, List<b> list2) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            t60 t60Var = list.get(i);
            int iA = t60Var.a(str, aVar.f9484a, aVar.d, aVar.c);
            if (iA > 0) {
                list2.add(new b(iA, t60Var));
            }
        }
        Collections.sort(list2);
    }
}
