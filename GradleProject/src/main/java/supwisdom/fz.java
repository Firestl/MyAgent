package supwisdom;

import com.dcloud.zxing2.FormatException;
import com.google.zxing.client.android.encode.MECARDContactEncoder;
import com.google.zxing.pdf417.decoder.DecodedBitStreamParser;
import com.huawei.secure.android.common.util.LogsUtil;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.list.template.TemplateDom;
import com.taobao.weex.utils.FunctionParser;
import com.taobao.weex.utils.WXUtils;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class fz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f7667a = {MECARDContactEncoder.TERMINATOR, '<', '>', TemplateDom.SEPARATOR, Operators.ARRAY_START, '\\', Operators.ARRAY_END, '_', '`', '~', '!', '\r', '\t', ',', Operators.CONDITION_IF_MIDDLE, '\n', FunctionParser.Lexer.MINUS, '.', '$', '/', Operators.QUOTE, '|', LogsUtil.b, Operators.BRACKET_START, Operators.BRACKET_END, Operators.CONDITION_IF, Operators.BLOCK_START, Operators.BLOCK_END, Operators.SINGLE_QUOTE};
    public static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, '&', '\r', '\t', ',', Operators.CONDITION_IF_MIDDLE, '#', FunctionParser.Lexer.MINUS, '.', '$', '/', FunctionParser.Lexer.PLUS, WXUtils.PERCENT, LogsUtil.b, '=', '^'};
    public static final Charset c = Charset.forName("ISO-8859-1");
    public static final BigInteger[] d;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7668a;

        static {
            int[] iArr = new int[b.values().length];
            f7668a = iArr;
            try {
                iArr[b.ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7668a[b.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7668a[b.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7668a[b.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7668a[b.ALPHA_SHIFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7668a[b.PUNCT_SHIFT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public enum b {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        d = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger bigIntegerValueOf = BigInteger.valueOf(900L);
        bigIntegerArr[1] = bigIntegerValueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = d;
            if (i >= bigIntegerArr2.length) {
                return;
            }
            bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(bigIntegerValueOf);
            i++;
        }
    }

    public static int a(int i, int[] iArr, Charset charset, int i2, StringBuilder sb) {
        int i3;
        int i4;
        int i5;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i6 = DecodedBitStreamParser.MACRO_PDF417_TERMINATOR;
        int i7 = DecodedBitStreamParser.BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
        int i8 = 928;
        int i9 = 902;
        long j = 900;
        if (i == 901) {
            int[] iArr2 = new int[6];
            i3 = i2 + 1;
            int i10 = iArr[i2];
            boolean z = false;
            loop0: while (true) {
                i4 = 0;
                long j2 = 0;
                while (i3 < iArr[0] && !z) {
                    int i11 = i4 + 1;
                    iArr2[i4] = i10;
                    j2 = (j2 * j) + ((long) i10);
                    int i12 = i3 + 1;
                    i10 = iArr[i3];
                    if (i10 == 900 || i10 == 901 || i10 == 902 || i10 == 924 || i10 == 928 || i10 == i7 || i10 == i6) {
                        i3 = i12 - 1;
                        i4 = i11;
                        i6 = DecodedBitStreamParser.MACRO_PDF417_TERMINATOR;
                        i7 = DecodedBitStreamParser.BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                        j = 900;
                        z = true;
                    } else if (i11 % 5 != 0 || i11 <= 0) {
                        i3 = i12;
                        i4 = i11;
                        i6 = DecodedBitStreamParser.MACRO_PDF417_TERMINATOR;
                        i7 = DecodedBitStreamParser.BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                        j = 900;
                    } else {
                        int i13 = 0;
                        while (i13 < 6) {
                            byteArrayOutputStream.write((byte) (j2 >> ((5 - i13) * 8)));
                            i13++;
                            i6 = DecodedBitStreamParser.MACRO_PDF417_TERMINATOR;
                            i7 = DecodedBitStreamParser.BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                        }
                        i3 = i12;
                        j = 900;
                    }
                }
                break loop0;
            }
            if (i3 != iArr[0] || i10 >= 900) {
                i5 = i4;
            } else {
                i5 = i4 + 1;
                iArr2[i4] = i10;
            }
            for (int i14 = 0; i14 < i5; i14++) {
                byteArrayOutputStream.write((byte) iArr2[i14]);
            }
        } else if (i == 924) {
            int i15 = i2;
            boolean z2 = false;
            int i16 = 0;
            long j3 = 0;
            while (i15 < iArr[0] && !z2) {
                int i17 = i15 + 1;
                int i18 = iArr[i15];
                if (i18 < 900) {
                    i16++;
                    j3 = (j3 * 900) + ((long) i18);
                    i15 = i17;
                } else {
                    if (i18 != 900 && i18 != 901 && i18 != i9 && i18 != 924 && i18 != i8) {
                        if (i18 != 923 && i18 != 922) {
                            i15 = i17;
                        }
                    }
                    i15 = i17 - 1;
                    z2 = true;
                }
                if (i16 % 5 == 0 && i16 > 0) {
                    for (int i19 = 0; i19 < 6; i19++) {
                        byteArrayOutputStream.write((byte) (j3 >> ((5 - i19) * 8)));
                    }
                    i16 = 0;
                    j3 = 0;
                }
                i8 = 928;
                i9 = 902;
            }
            i3 = i15;
        } else {
            i3 = i2;
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i3;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:14:0x0035. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x0038. Please report as an issue. */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Unknown Source)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    public static int b(int[] r8, int r9, java.lang.StringBuilder r10) {
        /*
            r0 = 0
            r1 = r8[r0]
            int r1 = r1 - r9
            int r1 = r1 * 2
            int[] r1 = new int[r1]
            r2 = r8[r0]
            int r2 = r2 - r9
            int r2 = r2 * 2
            int[] r2 = new int[r2]
            r3 = 0
            r4 = 0
        L11:
            r5 = r8[r0]
            if (r9 >= r5) goto L51
            if (r3 != 0) goto L51
            int r5 = r9 + 1
            r9 = r8[r9]
            r6 = 900(0x384, float:1.261E-42)
            if (r9 >= r6) goto L2d
            int r6 = r9 / 30
            r1[r4] = r6
            int r6 = r4 + 1
            int r9 = r9 % 30
            r1[r6] = r9
            int r4 = r4 + 2
        L2b:
            r9 = r5
            goto L11
        L2d:
            r7 = 913(0x391, float:1.28E-42)
            if (r9 == r7) goto L46
            r7 = 928(0x3a0, float:1.3E-42)
            if (r9 == r7) goto L42
            switch(r9) {
                case 900: goto L3c;
                case 901: goto L42;
                case 902: goto L42;
                default: goto L38;
            }
        L38:
            switch(r9) {
                case 922: goto L42;
                case 923: goto L42;
                case 924: goto L42;
                default: goto L3b;
            }
        L3b:
            goto L2b
        L3c:
            int r9 = r4 + 1
            r1[r4] = r6
            r4 = r9
            goto L2b
        L42:
            int r9 = r5 + (-1)
            r3 = 1
            goto L11
        L46:
            r1[r4] = r7
            int r9 = r5 + 1
            r5 = r8[r5]
            r2[r4] = r5
            int r4 = r4 + 1
            goto L11
        L51:
            a(r1, r2, r4, r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.fz.b(int[], int, java.lang.StringBuilder):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static supwisdom.hw a(int[] r7, java.lang.String r8) throws com.dcloud.zxing2.FormatException {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r1 = r7.length
            r2 = 2
            int r1 = r1 * 2
            r0.<init>(r1)
            java.nio.charset.Charset r1 = supwisdom.fz.c
            r3 = 1
            r3 = r7[r3]
            supwisdom.az r4 = new supwisdom.az
            r4.<init>()
        L13:
            r5 = 0
            r5 = r7[r5]
            if (r2 >= r5) goto L75
            r5 = 913(0x391, float:1.28E-42)
            if (r3 == r5) goto L5c
            switch(r3) {
                case 900: goto L2e;
                case 901: goto L52;
                case 902: goto L29;
                default: goto L1f;
            }
        L1f:
            switch(r3) {
                case 922: goto L57;
                case 923: goto L57;
                case 924: goto L52;
                case 925: goto L4f;
                case 926: goto L4c;
                case 927: goto L38;
                case 928: goto L33;
                default: goto L22;
            }
        L22:
            int r2 = r2 + (-1)
            int r2 = b(r7, r2, r0)
            goto L65
        L29:
            int r2 = a(r7, r2, r0)
            goto L65
        L2e:
            int r2 = b(r7, r2, r0)
            goto L65
        L33:
            int r2 = a(r7, r2, r4)
            goto L65
        L38:
            int r1 = r2 + 1
            r2 = r7[r2]
            com.dcloud.zxing2.common.CharacterSetECI r2 = com.dcloud.zxing2.common.CharacterSetECI.getCharacterSetECIByValue(r2)
            java.lang.String r2 = r2.name()
            java.nio.charset.Charset r2 = java.nio.charset.Charset.forName(r2)
            r6 = r2
            r2 = r1
            r1 = r6
            goto L65
        L4c:
            int r2 = r2 + 2
            goto L65
        L4f:
            int r2 = r2 + 1
            goto L65
        L52:
            int r2 = a(r3, r7, r1, r2, r0)
            goto L65
        L57:
            com.dcloud.zxing2.FormatException r7 = com.dcloud.zxing2.FormatException.getFormatInstance()
            throw r7
        L5c:
            int r3 = r2 + 1
            r2 = r7[r2]
            char r2 = (char) r2
            r0.append(r2)
            r2 = r3
        L65:
            int r3 = r7.length
            if (r2 >= r3) goto L70
            int r3 = r2 + 1
            r2 = r7[r2]
            r6 = r3
            r3 = r2
            r2 = r6
            goto L13
        L70:
            com.dcloud.zxing2.FormatException r7 = com.dcloud.zxing2.FormatException.getFormatInstance()
            throw r7
        L75:
            int r7 = r0.length()
            if (r7 == 0) goto L89
            supwisdom.hw r7 = new supwisdom.hw
            java.lang.String r0 = r0.toString()
            r1 = 0
            r7.<init>(r1, r0, r1, r8)
            r7.a(r4)
            return r7
        L89:
            com.dcloud.zxing2.FormatException r7 = com.dcloud.zxing2.FormatException.getFormatInstance()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.fz.a(int[], java.lang.String):supwisdom.hw");
    }

    public static String a(int[] iArr, int i) throws FormatException {
        BigInteger bigIntegerAdd = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigIntegerAdd = bigIntegerAdd.add(d[(i - i2) - 1].multiply(BigInteger.valueOf(iArr[i2])));
        }
        String string = bigIntegerAdd.toString();
        if (string.charAt(0) == '1') {
            return string.substring(1);
        }
        throw FormatException.getFormatInstance();
    }

    public static int a(int[] iArr, int i, az azVar) throws FormatException {
        if (i + 2 <= iArr[0]) {
            int[] iArr2 = new int[2];
            int i2 = 0;
            while (i2 < 2) {
                iArr2[i2] = iArr[i];
                i2++;
                i++;
            }
            azVar.a(Integer.parseInt(a(iArr2, 2)));
            StringBuilder sb = new StringBuilder();
            int iB = b(iArr, i, sb);
            azVar.a(sb.toString());
            if (iArr[iB] == 923) {
                int i3 = iB + 1;
                int[] iArr3 = new int[iArr[0] - i3];
                boolean z = false;
                int i4 = 0;
                while (i3 < iArr[0] && !z) {
                    int i5 = i3 + 1;
                    int i6 = iArr[i3];
                    if (i6 < 900) {
                        iArr3[i4] = i6;
                        i3 = i5;
                        i4++;
                    } else if (i6 == 922) {
                        azVar.a(true);
                        i3 = i5 + 1;
                        z = true;
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                azVar.a(Arrays.copyOf(iArr3, i4));
                return i3;
            }
            if (iArr[iB] != 922) {
                return iB;
            }
            azVar.a(true);
            return iB + 1;
        }
        throw FormatException.getFormatInstance();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void a(int[] iArr, int[] iArr2, int i, StringBuilder sb) {
        b bVar;
        int i2;
        char c2;
        b bVar2 = b.ALPHA;
        b bVar3 = bVar2;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            char c3 = ' ';
            switch (a.f7668a[bVar2.ordinal()]) {
                case 1:
                    if (i4 < 26) {
                        i2 = i4 + 65;
                        c3 = (char) i2;
                    } else if (i4 != 26) {
                        if (i4 == 27) {
                            bVar = b.LOWER;
                        } else if (i4 == 28) {
                            bVar = b.MIXED;
                        } else if (i4 == 29) {
                            bVar3 = b.PUNCT_SHIFT;
                            c3 = 0;
                            b bVar4 = bVar3;
                            bVar3 = bVar2;
                            bVar2 = bVar4;
                        } else {
                            if (i4 == 913) {
                                sb.append((char) iArr2[i3]);
                            } else if (i4 == 900) {
                                bVar = b.ALPHA;
                            }
                            c3 = 0;
                        }
                        c3 = 0;
                        b bVar5 = bVar3;
                        bVar3 = bVar;
                        bVar2 = bVar5;
                        b bVar42 = bVar3;
                        bVar3 = bVar2;
                        bVar2 = bVar42;
                    }
                    break;
                case 2:
                    if (i4 < 26) {
                        i2 = i4 + 97;
                        c3 = (char) i2;
                    } else if (i4 != 26) {
                        if (i4 != 27) {
                            if (i4 == 28) {
                                bVar = b.MIXED;
                            } else if (i4 != 29) {
                                if (i4 == 913) {
                                    sb.append((char) iArr2[i3]);
                                } else if (i4 == 900) {
                                    bVar = b.ALPHA;
                                }
                                c3 = 0;
                            } else {
                                bVar3 = b.PUNCT_SHIFT;
                            }
                            c3 = 0;
                            b bVar52 = bVar3;
                            bVar3 = bVar;
                            bVar2 = bVar52;
                            b bVar422 = bVar3;
                            bVar3 = bVar2;
                            bVar2 = bVar422;
                        } else {
                            bVar3 = b.ALPHA_SHIFT;
                        }
                        c3 = 0;
                        b bVar4222 = bVar3;
                        bVar3 = bVar2;
                        bVar2 = bVar4222;
                    }
                    break;
                case 3:
                    if (i4 < 25) {
                        c3 = b[i4];
                    } else {
                        if (i4 == 25) {
                            bVar = b.PUNCT;
                        } else if (i4 != 26) {
                            if (i4 == 27) {
                                bVar = b.LOWER;
                            } else if (i4 == 28) {
                                bVar = b.ALPHA;
                            } else if (i4 == 29) {
                                bVar3 = b.PUNCT_SHIFT;
                                c3 = 0;
                                b bVar42222 = bVar3;
                                bVar3 = bVar2;
                                bVar2 = bVar42222;
                            } else {
                                if (i4 == 913) {
                                    sb.append((char) iArr2[i3]);
                                } else if (i4 == 900) {
                                    bVar = b.ALPHA;
                                }
                                c3 = 0;
                            }
                        }
                        c3 = 0;
                        b bVar522 = bVar3;
                        bVar3 = bVar;
                        bVar2 = bVar522;
                        b bVar422222 = bVar3;
                        bVar3 = bVar2;
                        bVar2 = bVar422222;
                    }
                    break;
                case 4:
                    if (i4 < 29) {
                        c3 = f7667a[i4];
                    } else {
                        if (i4 != 29) {
                            if (i4 == 913) {
                                sb.append((char) iArr2[i3]);
                            } else if (i4 == 900) {
                                bVar = b.ALPHA;
                            }
                            c3 = 0;
                        } else {
                            bVar = b.ALPHA;
                        }
                        c3 = 0;
                        b bVar5222 = bVar3;
                        bVar3 = bVar;
                        bVar2 = bVar5222;
                        b bVar4222222 = bVar3;
                        bVar3 = bVar2;
                        bVar2 = bVar4222222;
                    }
                    break;
                case 5:
                    if (i4 < 26) {
                        c2 = (char) (i4 + 65);
                        c3 = c2;
                        bVar2 = bVar3;
                        b bVar42222222 = bVar3;
                        bVar3 = bVar2;
                        bVar2 = bVar42222222;
                    } else {
                        if (i4 != 26) {
                            if (i4 == 900) {
                                bVar = b.ALPHA;
                                c3 = 0;
                                b bVar52222 = bVar3;
                                bVar3 = bVar;
                                bVar2 = bVar52222;
                                b bVar422222222 = bVar3;
                                bVar3 = bVar2;
                                bVar2 = bVar422222222;
                            }
                            bVar2 = bVar3;
                            c3 = 0;
                            b bVar4222222222 = bVar3;
                            bVar3 = bVar2;
                            bVar2 = bVar4222222222;
                        }
                        bVar2 = bVar3;
                        b bVar42222222222 = bVar3;
                        bVar3 = bVar2;
                        bVar2 = bVar42222222222;
                    }
                    break;
                case 6:
                    if (i4 < 29) {
                        c2 = f7667a[i4];
                        c3 = c2;
                        bVar2 = bVar3;
                        b bVar422222222222 = bVar3;
                        bVar3 = bVar2;
                        bVar2 = bVar422222222222;
                    } else {
                        if (i4 != 29) {
                            if (i4 == 913) {
                                sb.append((char) iArr2[i3]);
                            } else if (i4 == 900) {
                                bVar = b.ALPHA;
                            }
                            bVar2 = bVar3;
                            c3 = 0;
                            b bVar4222222222222 = bVar3;
                            bVar3 = bVar2;
                            bVar2 = bVar4222222222222;
                        } else {
                            bVar = b.ALPHA;
                        }
                        c3 = 0;
                        b bVar522222 = bVar3;
                        bVar3 = bVar;
                        bVar2 = bVar522222;
                        b bVar42222222222222 = bVar3;
                        bVar3 = bVar2;
                        bVar2 = bVar42222222222222;
                    }
                    break;
                default:
                    c3 = 0;
                    break;
            }
            if (c3 != 0) {
                sb.append(c3);
            }
        }
    }

    public static int a(int[] iArr, int i, StringBuilder sb) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < 900) {
                iArr2[i2] = i4;
                i2++;
            } else if (i4 == 900 || i4 == 901 || i4 == 924 || i4 == 928 || i4 == 923 || i4 == 922) {
                i3--;
                z = true;
            }
            if ((i2 % 15 == 0 || i4 == 902 || z) && i2 > 0) {
                sb.append(a(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }
}
