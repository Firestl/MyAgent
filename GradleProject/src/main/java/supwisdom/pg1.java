package supwisdom;

import com.taobao.weex.el.parse.Operators;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: compiled from: DistinguishedNameParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pg1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8793a;
    public final int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8794e;
    public int f;
    public char[] g;

    public pg1(X500Principal x500Principal) {
        String name = x500Principal.getName("RFC2253");
        this.f8793a = name;
        this.b = name.length();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
    
        r1 = r8.g;
        r2 = r8.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005f, code lost:
    
        return new java.lang.String(r1, r2, r8.f8794e - r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String a() {
        /*
            r8 = this;
            int r0 = r8.c
            r8.d = r0
            r8.f8794e = r0
        L6:
            int r0 = r8.c
            int r1 = r8.b
            if (r0 < r1) goto L19
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.g
            int r2 = r8.d
            int r3 = r8.f8794e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L19:
            char[] r1 = r8.g
            char r2 = r1[r0]
            r3 = 44
            r4 = 43
            r5 = 59
            r6 = 32
            if (r2 == r6) goto L60
            if (r2 == r5) goto L53
            r5 = 92
            if (r2 == r5) goto L40
            if (r2 == r4) goto L53
            if (r2 == r3) goto L53
            int r2 = r8.f8794e
            int r3 = r2 + 1
            r8.f8794e = r3
            char r3 = r1[r0]
            r1[r2] = r3
            int r0 = r0 + 1
            r8.c = r0
            goto L6
        L40:
            int r0 = r8.f8794e
            int r2 = r0 + 1
            r8.f8794e = r2
            char r2 = r8.b()
            r1[r0] = r2
            int r0 = r8.c
            int r0 = r0 + 1
            r8.c = r0
            goto L6
        L53:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.g
            int r2 = r8.d
            int r3 = r8.f8794e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L60:
            int r2 = r8.f8794e
            r8.f = r2
            int r0 = r0 + 1
            r8.c = r0
            int r0 = r2 + 1
            r8.f8794e = r0
            r1[r2] = r6
        L6e:
            int r0 = r8.c
            int r1 = r8.b
            if (r0 >= r1) goto L87
            char[] r1 = r8.g
            char r2 = r1[r0]
            if (r2 != r6) goto L87
            int r2 = r8.f8794e
            int r7 = r2 + 1
            r8.f8794e = r7
            r1[r2] = r6
            int r0 = r0 + 1
            r8.c = r0
            goto L6e
        L87:
            int r0 = r8.c
            int r1 = r8.b
            if (r0 == r1) goto L9b
            char[] r1 = r8.g
            char r2 = r1[r0]
            if (r2 == r3) goto L9b
            char r2 = r1[r0]
            if (r2 == r4) goto L9b
            char r0 = r1[r0]
            if (r0 != r5) goto L6
        L9b:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.g
            int r2 = r8.d
            int r3 = r8.f
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.pg1.a():java.lang.String");
    }

    public final char b() {
        int i = this.c + 1;
        this.c = i;
        if (i == this.b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f8793a);
        }
        char c = this.g[i];
        if (c != ' ' && c != '%' && c != '\\' && c != '_' && c != '\"' && c != '#') {
            switch (c) {
                case '*':
                case '+':
                case ',':
                    break;
                default:
                    switch (c) {
                        case ';':
                        case '<':
                        case '=':
                        case '>':
                            break;
                        default:
                            return c();
                    }
                    break;
            }
        }
        return this.g[this.c];
    }

    public final char c() {
        int i;
        int i2;
        int iA = a(this.c);
        this.c++;
        if (iA < 128) {
            return (char) iA;
        }
        if (iA < 192 || iA > 247) {
            return Operators.CONDITION_IF;
        }
        if (iA <= 223) {
            i2 = iA & 31;
            i = 1;
        } else if (iA <= 239) {
            i = 2;
            i2 = iA & 15;
        } else {
            i = 3;
            i2 = iA & 7;
        }
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = this.c + 1;
            this.c = i4;
            if (i4 == this.b || this.g[i4] != '\\') {
                return Operators.CONDITION_IF;
            }
            int i5 = i4 + 1;
            this.c = i5;
            int iA2 = a(i5);
            this.c++;
            if ((iA2 & 192) != 128) {
                return Operators.CONDITION_IF;
            }
            i2 = (i2 << 6) + (iA2 & 63);
        }
        return (char) i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0061, code lost:
    
        r6.f8794e = r6.c;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String d() {
        /*
            r6 = this;
            int r0 = r6.c
            int r1 = r0 + 4
            int r2 = r6.b
            java.lang.String r3 = "Unexpected end of DN: "
            if (r1 >= r2) goto La7
            r6.d = r0
            int r0 = r0 + 1
            r6.c = r0
        L10:
            int r0 = r6.c
            int r1 = r6.b
            if (r0 == r1) goto L61
            char[] r1 = r6.g
            char r2 = r1[r0]
            r4 = 43
            if (r2 == r4) goto L61
            char r2 = r1[r0]
            r4 = 44
            if (r2 == r4) goto L61
            char r2 = r1[r0]
            r4 = 59
            if (r2 != r4) goto L2b
            goto L61
        L2b:
            char r2 = r1[r0]
            r4 = 32
            if (r2 != r4) goto L48
            r6.f8794e = r0
            int r0 = r0 + 1
            r6.c = r0
        L37:
            int r0 = r6.c
            int r1 = r6.b
            if (r0 >= r1) goto L65
            char[] r1 = r6.g
            char r1 = r1[r0]
            if (r1 != r4) goto L65
            int r0 = r0 + 1
            r6.c = r0
            goto L37
        L48:
            char r2 = r1[r0]
            r5 = 65
            if (r2 < r5) goto L5a
            char r2 = r1[r0]
            r5 = 70
            if (r2 > r5) goto L5a
            char r2 = r1[r0]
            int r2 = r2 + r4
            char r2 = (char) r2
            r1[r0] = r2
        L5a:
            int r0 = r6.c
            int r0 = r0 + 1
            r6.c = r0
            goto L10
        L61:
            int r0 = r6.c
            r6.f8794e = r0
        L65:
            int r0 = r6.f8794e
            int r1 = r6.d
            int r0 = r0 - r1
            r2 = 5
            if (r0 < r2) goto L90
            r2 = r0 & 1
            if (r2 == 0) goto L90
            int r2 = r0 / 2
            byte[] r3 = new byte[r2]
            r4 = 0
            int r1 = r1 + 1
        L78:
            if (r4 >= r2) goto L86
            int r5 = r6.a(r1)
            byte r5 = (byte) r5
            r3[r4] = r5
            int r1 = r1 + 2
            int r4 = r4 + 1
            goto L78
        L86:
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.g
            int r3 = r6.d
            r1.<init>(r2, r3, r0)
            return r1
        L90:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            java.lang.String r2 = r6.f8793a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        La7:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            java.lang.String r2 = r6.f8793a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.pg1.d():java.lang.String");
    }

    public final String e() {
        while (true) {
            int i = this.c;
            if (i >= this.b || this.g[i] != ' ') {
                break;
            }
            this.c = i + 1;
        }
        int i2 = this.c;
        if (i2 == this.b) {
            return null;
        }
        this.d = i2;
        this.c = i2 + 1;
        while (true) {
            int i3 = this.c;
            if (i3 >= this.b) {
                break;
            }
            char[] cArr = this.g;
            if (cArr[i3] == '=' || cArr[i3] == ' ') {
                break;
            }
            this.c = i3 + 1;
        }
        int i4 = this.c;
        if (i4 >= this.b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f8793a);
        }
        this.f8794e = i4;
        if (this.g[i4] == ' ') {
            while (true) {
                int i5 = this.c;
                if (i5 >= this.b) {
                    break;
                }
                char[] cArr2 = this.g;
                if (cArr2[i5] == '=' || cArr2[i5] != ' ') {
                    break;
                }
                this.c = i5 + 1;
            }
            char[] cArr3 = this.g;
            int i6 = this.c;
            if (cArr3[i6] != '=' || i6 == this.b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f8793a);
            }
        }
        this.c++;
        while (true) {
            int i7 = this.c;
            if (i7 >= this.b || this.g[i7] != ' ') {
                break;
            }
            this.c = i7 + 1;
        }
        int i8 = this.f8794e;
        int i9 = this.d;
        if (i8 - i9 > 4) {
            char[] cArr4 = this.g;
            if (cArr4[i9 + 3] == '.' && (cArr4[i9] == 'O' || cArr4[i9] == 'o')) {
                char[] cArr5 = this.g;
                int i10 = this.d;
                if (cArr5[i10 + 1] == 'I' || cArr5[i10 + 1] == 'i') {
                    char[] cArr6 = this.g;
                    int i11 = this.d;
                    if (cArr6[i11 + 2] == 'D' || cArr6[i11 + 2] == 'd') {
                        this.d += 4;
                    }
                }
            }
        }
        char[] cArr7 = this.g;
        int i12 = this.d;
        return new String(cArr7, i12, this.f8794e - i12);
    }

    public final String f() {
        int i = this.c + 1;
        this.c = i;
        this.d = i;
        this.f8794e = i;
        while (true) {
            int i2 = this.c;
            if (i2 == this.b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f8793a);
            }
            char[] cArr = this.g;
            if (cArr[i2] == '\"') {
                this.c = i2 + 1;
                while (true) {
                    int i3 = this.c;
                    if (i3 >= this.b || this.g[i3] != ' ') {
                        break;
                    }
                    this.c = i3 + 1;
                }
                char[] cArr2 = this.g;
                int i4 = this.d;
                return new String(cArr2, i4, this.f8794e - i4);
            }
            if (cArr[i2] == '\\') {
                cArr[this.f8794e] = b();
            } else {
                cArr[this.f8794e] = cArr[i2];
            }
            this.c++;
            this.f8794e++;
        }
    }

    public final int a(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 < this.b) {
            char c = this.g[i];
            if (c >= '0' && c <= '9') {
                i2 = c - '0';
            } else if (c >= 'a' && c <= 'f') {
                i2 = c - 'W';
            } else {
                if (c < 'A' || c > 'F') {
                    throw new IllegalStateException("Malformed DN: " + this.f8793a);
                }
                i2 = c - '7';
            }
            char c2 = this.g[i4];
            if (c2 >= '0' && c2 <= '9') {
                i3 = c2 - '0';
            } else if (c2 >= 'a' && c2 <= 'f') {
                i3 = c2 - 'W';
            } else {
                if (c2 < 'A' || c2 > 'F') {
                    throw new IllegalStateException("Malformed DN: " + this.f8793a);
                }
                i3 = c2 - '7';
            }
            return (i2 << 4) + i3;
        }
        throw new IllegalStateException("Malformed DN: " + this.f8793a);
    }

    public String a(String str) {
        String strF;
        this.c = 0;
        this.d = 0;
        this.f8794e = 0;
        this.f = 0;
        this.g = this.f8793a.toCharArray();
        String strE = e();
        if (strE == null) {
            return null;
        }
        do {
            int i = this.c;
            if (i == this.b) {
                return null;
            }
            char c = this.g[i];
            if (c == '\"') {
                strF = f();
            } else if (c != '#') {
                strF = (c == '+' || c == ',' || c == ';') ? "" : a();
            } else {
                strF = d();
            }
            if (str.equalsIgnoreCase(strE)) {
                return strF;
            }
            int i2 = this.c;
            if (i2 >= this.b) {
                return null;
            }
            char[] cArr = this.g;
            if (cArr[i2] != ',' && cArr[i2] != ';' && cArr[i2] != '+') {
                throw new IllegalStateException("Malformed DN: " + this.f8793a);
            }
            this.c++;
            strE = e();
        } while (strE != null);
        throw new IllegalStateException("Malformed DN: " + this.f8793a);
    }
}
