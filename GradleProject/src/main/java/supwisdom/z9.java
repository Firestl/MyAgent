package supwisdom;

import android.text.SpannableStringBuilder;
import com.google.zxing.client.android.encode.MECARDContactEncoder;
import java.util.Locale;
import org.bouncycastle.pqc.crypto.newhope.Params;

/* JADX INFO: compiled from: BidiFormatter.java */
/* JADX INFO: loaded from: classes.dex */
public final class z9 {
    public static final ca d = da.c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f9977e = Character.toString(8206);
    public static final String f = Character.toString(8207);
    public static final z9 g = new z9(false, 2, d);
    public static final z9 h = new z9(true, 2, d);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f9978a;
    public final int b;
    public final ca c;

    /* JADX INFO: compiled from: BidiFormatter.java */
    public static class b {
        public static final byte[] f = new byte[Params.POLY_BYTES];

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final CharSequence f9980a;
        public final boolean b;
        public final int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public char f9981e;

        static {
            for (int i = 0; i < 1792; i++) {
                f[i] = Character.getDirectionality(i);
            }
        }

        public b(CharSequence charSequence, boolean z) {
            this.f9980a = charSequence;
            this.b = z;
            this.c = charSequence.length();
        }

        public static byte a(char c) {
            return c < 1792 ? f[c] : Character.getDirectionality(c);
        }

        public byte b() {
            char cCharAt = this.f9980a.charAt(this.d);
            this.f9981e = cCharAt;
            if (Character.isHighSurrogate(cCharAt)) {
                int iCodePointAt = Character.codePointAt(this.f9980a, this.d);
                this.d += Character.charCount(iCodePointAt);
                return Character.getDirectionality(iCodePointAt);
            }
            this.d++;
            byte bA = a(this.f9981e);
            if (!this.b) {
                return bA;
            }
            char c = this.f9981e;
            return c == '<' ? h() : c == '&' ? f() : bA;
        }

        public int c() {
            this.d = 0;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (this.d < this.c && i == 0) {
                byte b = b();
                if (b != 0) {
                    if (b == 1 || b == 2) {
                        if (i3 == 0) {
                            return 1;
                        }
                    } else if (b != 9) {
                        switch (b) {
                            case 14:
                            case 15:
                                i3++;
                                i2 = -1;
                                continue;
                            case 16:
                            case 17:
                                i3++;
                                i2 = 1;
                                continue;
                            case 18:
                                i3--;
                                i2 = 0;
                                continue;
                        }
                    }
                } else if (i3 == 0) {
                    return -1;
                }
                i = i3;
            }
            if (i == 0) {
                return 0;
            }
            if (i2 != 0) {
                return i2;
            }
            while (this.d > 0) {
                switch (a()) {
                    case 14:
                    case 15:
                        if (i == i3) {
                            return -1;
                        }
                        break;
                    case 16:
                    case 17:
                        if (i == i3) {
                            return 1;
                        }
                        break;
                    case 18:
                        i3++;
                        continue;
                }
                i3--;
            }
            return 0;
        }

        public int d() {
            this.d = this.c;
            int i = 0;
            int i2 = 0;
            while (this.d > 0) {
                byte bA = a();
                if (bA == 0) {
                    if (i == 0) {
                        return -1;
                    }
                    if (i2 == 0) {
                        i2 = i;
                    }
                } else if (bA == 1 || bA == 2) {
                    if (i == 0) {
                        return 1;
                    }
                    if (i2 == 0) {
                        i2 = i;
                    }
                } else if (bA != 9) {
                    switch (bA) {
                        case 14:
                        case 15:
                            if (i2 == i) {
                                return -1;
                            }
                            i--;
                            break;
                        case 16:
                        case 17:
                            if (i2 == i) {
                                return 1;
                            }
                            i--;
                            break;
                        case 18:
                            i++;
                            break;
                        default:
                            if (i2 == 0) {
                                i2 = i;
                            }
                            break;
                    }
                } else {
                    continue;
                }
            }
            return 0;
        }

        public final byte e() {
            char cCharAt;
            int i = this.d;
            do {
                int i2 = this.d;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f9980a;
                int i3 = i2 - 1;
                this.d = i3;
                cCharAt = charSequence.charAt(i3);
                this.f9981e = cCharAt;
                if (cCharAt == '&') {
                    return (byte) 12;
                }
            } while (cCharAt != ';');
            this.d = i;
            this.f9981e = MECARDContactEncoder.TERMINATOR;
            return (byte) 13;
        }

        public final byte f() {
            char cCharAt;
            do {
                int i = this.d;
                if (i >= this.c) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.f9980a;
                this.d = i + 1;
                cCharAt = charSequence.charAt(i);
                this.f9981e = cCharAt;
            } while (cCharAt != ';');
            return (byte) 12;
        }

        public final byte g() {
            char cCharAt;
            int i = this.d;
            while (true) {
                int i2 = this.d;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f9980a;
                int i3 = i2 - 1;
                this.d = i3;
                char cCharAt2 = charSequence.charAt(i3);
                this.f9981e = cCharAt2;
                if (cCharAt2 == '<') {
                    return (byte) 12;
                }
                if (cCharAt2 == '>') {
                    break;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    char c = this.f9981e;
                    do {
                        int i4 = this.d;
                        if (i4 > 0) {
                            CharSequence charSequence2 = this.f9980a;
                            int i5 = i4 - 1;
                            this.d = i5;
                            cCharAt = charSequence2.charAt(i5);
                            this.f9981e = cCharAt;
                        }
                    } while (cCharAt != c);
                }
            }
            this.d = i;
            this.f9981e = '>';
            return (byte) 13;
        }

        public final byte h() {
            char cCharAt;
            int i = this.d;
            while (true) {
                int i2 = this.d;
                if (i2 >= this.c) {
                    this.d = i;
                    this.f9981e = '<';
                    return (byte) 13;
                }
                CharSequence charSequence = this.f9980a;
                this.d = i2 + 1;
                char cCharAt2 = charSequence.charAt(i2);
                this.f9981e = cCharAt2;
                if (cCharAt2 == '>') {
                    return (byte) 12;
                }
                if (cCharAt2 == '\"' || cCharAt2 == '\'') {
                    char c = this.f9981e;
                    do {
                        int i3 = this.d;
                        if (i3 < this.c) {
                            CharSequence charSequence2 = this.f9980a;
                            this.d = i3 + 1;
                            cCharAt = charSequence2.charAt(i3);
                            this.f9981e = cCharAt;
                        }
                    } while (cCharAt != c);
                }
            }
        }

        public byte a() {
            char cCharAt = this.f9980a.charAt(this.d - 1);
            this.f9981e = cCharAt;
            if (Character.isLowSurrogate(cCharAt)) {
                int iCodePointBefore = Character.codePointBefore(this.f9980a, this.d);
                this.d -= Character.charCount(iCodePointBefore);
                return Character.getDirectionality(iCodePointBefore);
            }
            this.d--;
            byte bA = a(this.f9981e);
            if (!this.b) {
                return bA;
            }
            char c = this.f9981e;
            return c == '>' ? g() : c == ';' ? e() : bA;
        }
    }

    public z9(boolean z, int i, ca caVar) {
        this.f9978a = z;
        this.b = i;
        this.c = caVar;
    }

    public static z9 b() {
        return new a().a();
    }

    public static int c(CharSequence charSequence) {
        return new b(charSequence, false).d();
    }

    public boolean a() {
        return (this.b & 2) != 0;
    }

    public final String a(CharSequence charSequence, ca caVar) {
        boolean zA = caVar.a(charSequence, 0, charSequence.length());
        return (this.f9978a || !(zA || c(charSequence) == 1)) ? this.f9978a ? (!zA || c(charSequence) == -1) ? f : "" : "" : f9977e;
    }

    public final String b(CharSequence charSequence, ca caVar) {
        boolean zA = caVar.a(charSequence, 0, charSequence.length());
        return (this.f9978a || !(zA || b(charSequence) == 1)) ? this.f9978a ? (!zA || b(charSequence) == -1) ? f : "" : "" : f9977e;
    }

    /* JADX INFO: compiled from: BidiFormatter.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f9979a;
        public int b;
        public ca c;

        public a() {
            a(z9.a(Locale.getDefault()));
        }

        public static z9 b(boolean z) {
            return z ? z9.h : z9.g;
        }

        public final void a(boolean z) {
            this.f9979a = z;
            this.c = z9.d;
            this.b = 2;
        }

        public z9 a() {
            if (this.b == 2 && this.c == z9.d) {
                return b(this.f9979a);
            }
            return new z9(this.f9979a, this.b, this.c);
        }
    }

    public static int b(CharSequence charSequence) {
        return new b(charSequence, false).c();
    }

    public CharSequence a(CharSequence charSequence, ca caVar, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean zA = caVar.a(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (a() && z) {
            spannableStringBuilder.append((CharSequence) b(charSequence, zA ? da.b : da.f7314a));
        }
        if (zA != this.f9978a) {
            spannableStringBuilder.append(zA ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append((CharSequence) a(charSequence, zA ? da.b : da.f7314a));
        }
        return spannableStringBuilder;
    }

    public CharSequence a(CharSequence charSequence) {
        return a(charSequence, this.c, true);
    }

    public static boolean a(Locale locale) {
        return ea.b(locale) == 1;
    }
}
