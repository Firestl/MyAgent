package supwisdom;

import com.dcloud.zxing2.NotFoundException;

/* JADX INFO: loaded from: classes.dex */
public final class pw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final fw f8842a;
    public final int b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8843e;
    public final int f;
    public final int g;

    public pw(fw fwVar) throws NotFoundException {
        this(fwVar, 10, fwVar.e() / 2, fwVar.c() / 2);
    }

    public final yv[] a(yv yvVar, yv yvVar2, yv yvVar3, yv yvVar4) {
        float fA = yvVar.a();
        float fB = yvVar.b();
        float fA2 = yvVar2.a();
        float fB2 = yvVar2.b();
        float fA3 = yvVar3.a();
        float fB3 = yvVar3.b();
        float fA4 = yvVar4.a();
        float fB4 = yvVar4.b();
        return fA < ((float) this.c) / 2.0f ? new yv[]{new yv(fA4 - 1.0f, fB4 + 1.0f), new yv(fA2 + 1.0f, fB2 + 1.0f), new yv(fA3 - 1.0f, fB3 - 1.0f), new yv(fA + 1.0f, fB - 1.0f)} : new yv[]{new yv(fA4 + 1.0f, fB4 + 1.0f), new yv(fA2 + 1.0f, fB2 - 1.0f), new yv(fA3 - 1.0f, fB3 + 1.0f), new yv(fA - 1.0f, fB - 1.0f)};
    }

    public pw(fw fwVar, int i, int i2, int i3) throws NotFoundException {
        this.f8842a = fwVar;
        int iC = fwVar.c();
        this.b = iC;
        int iE = fwVar.e();
        this.c = iE;
        int i4 = i / 2;
        int i5 = i2 - i4;
        this.d = i5;
        int i6 = i2 + i4;
        this.f8843e = i6;
        int i7 = i3 - i4;
        this.g = i7;
        int i8 = i3 + i4;
        this.f = i8;
        if (i7 < 0 || i5 < 0 || i8 >= iC || i6 >= iE) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public final boolean a(int i, int i2, int i3, boolean z) {
        if (z) {
            while (i <= i2) {
                if (this.f8842a.b(i, i3)) {
                    return true;
                }
                i++;
            }
            return false;
        }
        while (i <= i2) {
            if (this.f8842a.b(i3, i)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public yv[] a() throws NotFoundException {
        int i = this.d;
        int i2 = this.f8843e;
        int i3 = this.g;
        int i4 = this.f;
        boolean z = false;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        while (z2) {
            boolean zA = true;
            boolean z8 = false;
            while (true) {
                if ((!zA && z4) || i2 >= this.c) {
                    break;
                }
                zA = a(i3, i4, i2, false);
                if (zA) {
                    i2++;
                    z4 = true;
                    z8 = true;
                } else if (!z4) {
                    i2++;
                }
            }
            if (i2 < this.c) {
                boolean zA2 = true;
                while (true) {
                    if ((!zA2 && z5) || i4 >= this.b) {
                        break;
                    }
                    zA2 = a(i, i2, i4, true);
                    if (zA2) {
                        i4++;
                        z5 = true;
                        z8 = true;
                    } else if (!z5) {
                        i4++;
                    }
                }
                if (i4 < this.b) {
                    boolean zA3 = true;
                    while (true) {
                        if ((!zA3 && z6) || i < 0) {
                            break;
                        }
                        zA3 = a(i3, i4, i, false);
                        if (zA3) {
                            i--;
                            z6 = true;
                            z8 = true;
                        } else if (!z6) {
                            i--;
                        }
                    }
                    if (i >= 0) {
                        z2 = z8;
                        boolean zA4 = true;
                        while (true) {
                            if ((!zA4 && z7) || i3 < 0) {
                                break;
                            }
                            zA4 = a(i, i2, i3, true);
                            if (zA4) {
                                i3--;
                                z2 = true;
                                z7 = true;
                            } else if (!z7) {
                                i3--;
                            }
                        }
                        if (i3 >= 0) {
                            if (z2) {
                                z3 = true;
                            }
                        }
                    }
                }
            }
            z = true;
            break;
        }
        if (z || !z3) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i5 = i2 - i;
        yv yvVarA = null;
        yv yvVarA2 = null;
        for (int i6 = 1; i6 < i5; i6++) {
            yvVarA2 = a(i, i4 - i6, i + i6, i4);
            if (yvVarA2 != null) {
                break;
            }
        }
        if (yvVarA2 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        yv yvVarA3 = null;
        for (int i7 = 1; i7 < i5; i7++) {
            yvVarA3 = a(i, i3 + i7, i + i7, i3);
            if (yvVarA3 != null) {
                break;
            }
        }
        if (yvVarA3 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        yv yvVarA4 = null;
        for (int i8 = 1; i8 < i5; i8++) {
            yvVarA4 = a(i2, i3 + i8, i2 - i8, i3);
            if (yvVarA4 != null) {
                break;
            }
        }
        if (yvVarA4 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        for (int i9 = 1; i9 < i5; i9++) {
            yvVarA = a(i2, i4 - i9, i2 - i9, i4);
            if (yvVarA != null) {
                break;
            }
        }
        if (yvVarA != null) {
            return a(yvVarA, yvVarA2, yvVarA4, yvVarA3);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final yv a(float f, float f2, float f3, float f4) {
        int iA = ow.a(ow.a(f, f2, f3, f4));
        float f5 = iA;
        float f6 = (f3 - f) / f5;
        float f7 = (f4 - f2) / f5;
        for (int i = 0; i < iA; i++) {
            float f8 = i;
            int iA2 = ow.a((f8 * f6) + f);
            int iA3 = ow.a((f8 * f7) + f2);
            if (this.f8842a.b(iA2, iA3)) {
                return new yv(iA2, iA3);
            }
        }
        return null;
    }
}
