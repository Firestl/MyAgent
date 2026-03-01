package org.mozilla.universalchardet;

import org.mozilla.universalchardet.prober.CharsetProber;
import supwisdom.jv1;
import supwisdom.kv1;
import supwisdom.mv1;
import supwisdom.nv1;

/* JADX INFO: loaded from: classes3.dex */
public class UniversalDetector {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public InputState f6810a;
    public boolean b;
    public boolean c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte f6811e;
    public String f;
    public CharsetProber[] g;
    public CharsetProber h;
    public jv1 i;

    public enum InputState {
        PURE_ASCII,
        ESC_ASCII,
        HIGHBYTE
    }

    public UniversalDetector() {
        this(null);
    }

    public void a() {
        CharsetProber[] charsetProberArr;
        if (this.d) {
            String str = this.f;
            if (str != null) {
                this.b = true;
                jv1 jv1Var = this.i;
                if (jv1Var != null) {
                    jv1Var.a(str);
                    return;
                }
                return;
            }
            if (this.f6810a != InputState.HIGHBYTE) {
                InputState inputState = InputState.ESC_ASCII;
                return;
            }
            float f = 0.0f;
            int i = 0;
            int i2 = 0;
            while (true) {
                charsetProberArr = this.g;
                if (i >= charsetProberArr.length) {
                    break;
                }
                float fB = charsetProberArr[i].b();
                if (fB > f) {
                    i2 = i;
                    f = fB;
                }
                i++;
            }
            if (f > 0.2f) {
                String strA = charsetProberArr[i2].a();
                this.f = strA;
                jv1 jv1Var2 = this.i;
                if (jv1Var2 != null) {
                    jv1Var2.a(strA);
                }
            }
        }
    }

    public String b() {
        return this.f;
    }

    public void c() {
        int i = 0;
        this.b = false;
        this.c = true;
        this.f = null;
        this.d = false;
        this.f6810a = InputState.PURE_ASCII;
        this.f6811e = (byte) 0;
        CharsetProber charsetProber = this.h;
        if (charsetProber != null) {
            charsetProber.d();
        }
        while (true) {
            CharsetProber[] charsetProberArr = this.g;
            if (i >= charsetProberArr.length) {
                return;
            }
            if (charsetProberArr[i] != null) {
                charsetProberArr[i].d();
            }
            i++;
        }
    }

    public UniversalDetector(jv1 jv1Var) {
        this.i = jv1Var;
        this.h = null;
        this.g = new CharsetProber[1];
        c();
    }

    public void a(byte[] bArr) {
        a(bArr, 0, bArr.length);
    }

    public void a(byte[] bArr, int i, int i2) {
        if (this.b) {
            return;
        }
        if (i2 > 0) {
            this.d = true;
        }
        int i3 = 0;
        if (this.c) {
            this.c = false;
            if (i2 > 3) {
                int i4 = bArr[i] & 255;
                int i5 = bArr[i + 1] & 255;
                int i6 = bArr[i + 2] & 255;
                int i7 = bArr[i + 3] & 255;
                if (i4 != 0) {
                    if (i4 != 239) {
                        if (i4 != 254) {
                            if (i4 == 255) {
                                if (i5 == 254 && i6 == 0 && i7 == 0) {
                                    this.f = kv1.i;
                                } else if (i5 == 254) {
                                    this.f = kv1.g;
                                }
                            }
                        } else if (i5 == 255 && i6 == 0 && i7 == 0) {
                            this.f = kv1.k;
                        } else if (i5 == 255) {
                            this.f = kv1.f;
                        }
                    } else if (i5 == 187 && i6 == 191) {
                        this.f = kv1.f8213e;
                    }
                } else if (i5 == 0 && i6 == 254 && i7 == 255) {
                    this.f = kv1.h;
                } else if (i5 == 0 && i6 == 255 && i7 == 254) {
                    this.f = kv1.l;
                }
                if (this.f != null) {
                    this.b = true;
                    return;
                }
            }
        }
        int i8 = i + i2;
        for (int i9 = i; i9 < i8; i9++) {
            int i10 = bArr[i9] & 255;
            if ((i10 & 128) != 0 && i10 != 160) {
                InputState inputState = this.f6810a;
                InputState inputState2 = InputState.HIGHBYTE;
                if (inputState != inputState2) {
                    this.f6810a = inputState2;
                    if (this.h != null) {
                        this.h = null;
                    }
                    CharsetProber[] charsetProberArr = this.g;
                    if (charsetProberArr[0] == null) {
                        charsetProberArr[0] = new nv1();
                    }
                }
            } else {
                if (this.f6810a == InputState.PURE_ASCII && (i10 == 27 || (i10 == 123 && this.f6811e == 126))) {
                    this.f6810a = InputState.ESC_ASCII;
                }
                this.f6811e = bArr[i9];
            }
        }
        InputState inputState3 = this.f6810a;
        if (inputState3 == InputState.ESC_ASCII) {
            if (this.h == null) {
                this.h = new mv1();
            }
            if (this.h.a(bArr, i, i2) == CharsetProber.ProbingState.FOUND_IT) {
                this.b = true;
                this.f = this.h.a();
                return;
            }
            return;
        }
        if (inputState3 != InputState.HIGHBYTE) {
            return;
        }
        while (true) {
            CharsetProber[] charsetProberArr2 = this.g;
            if (i3 >= charsetProberArr2.length) {
                return;
            }
            if (charsetProberArr2[i3].a(bArr, i, i2) == CharsetProber.ProbingState.FOUND_IT) {
                this.b = true;
                this.f = this.g[i3].a();
                return;
            }
            i3++;
        }
    }
}
