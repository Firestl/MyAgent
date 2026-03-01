package supwisdom;

import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class b00 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final fw f7000a;
    public zv b;

    public b00(fw fwVar) {
        this.f7000a = fwVar;
    }

    public final float a(yv yvVar, yv yvVar2, yv yvVar3) {
        return (a(yvVar, yvVar2) + a(yvVar, yvVar3)) / 2.0f;
    }

    public final float b(int i, int i2, int i3, int i4) {
        float fE;
        float fC;
        float fA = a(i, i2, i3, i4);
        int iE = i - (i3 - i);
        int iC = 0;
        if (iE < 0) {
            fE = i / (i - iE);
            iE = 0;
        } else if (iE >= this.f7000a.e()) {
            fE = ((this.f7000a.e() - 1) - i) / (iE - i);
            iE = this.f7000a.e() - 1;
        } else {
            fE = 1.0f;
        }
        float f = i2;
        int i5 = (int) (f - ((i4 - i2) * fE));
        if (i5 < 0) {
            fC = f / (i2 - i5);
        } else if (i5 >= this.f7000a.c()) {
            fC = ((this.f7000a.c() - 1) - i2) / (i5 - i2);
            iC = this.f7000a.c() - 1;
        } else {
            iC = i5;
            fC = 1.0f;
        }
        return (fA + a(i, i2, (int) (i + ((iE - i) * fC)), iC)) - 1.0f;
    }

    public final float a(yv yvVar, yv yvVar2) {
        float fB = b((int) yvVar.a(), (int) yvVar.b(), (int) yvVar2.a(), (int) yvVar2.b());
        float fB2 = b((int) yvVar2.a(), (int) yvVar2.b(), (int) yvVar.a(), (int) yvVar.b());
        return Float.isNaN(fB) ? fB2 / 7.0f : Float.isNaN(fB2) ? fB / 7.0f : (fB + fB2) / 14.0f;
    }

    public static int a(yv yvVar, yv yvVar2, yv yvVar3, float f) throws NotFoundException {
        int iA = ((ow.a(yv.a(yvVar, yvVar2) / f) + ow.a(yv.a(yvVar, yvVar3) / f)) / 2) + 7;
        int i = iA & 3;
        if (i == 0) {
            return iA + 1;
        }
        if (i == 2) {
            return iA - 1;
        }
        if (i != 3) {
            return iA;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public static mw a(yv yvVar, yv yvVar2, yv yvVar3, yv yvVar4, int i) {
        float fA;
        float fB;
        float f;
        float f2 = i - 3.5f;
        if (yvVar4 != null) {
            fA = yvVar4.a();
            fB = yvVar4.b();
            f = f2 - 3.0f;
        } else {
            fA = (yvVar2.a() - yvVar.a()) + yvVar3.a();
            fB = (yvVar2.b() - yvVar.b()) + yvVar3.b();
            f = f2;
        }
        return mw.a(3.5f, 3.5f, f2, 3.5f, f, f, 3.5f, f2, yvVar.a(), yvVar.b(), yvVar2.a(), yvVar2.b(), fA, fB, yvVar3.a(), yvVar3.b());
    }

    public final zz a(float f, int i, int i2, float f2) throws NotFoundException {
        int i3 = (int) (f2 * f);
        int iMax = Math.max(0, i - i3);
        int iMin = Math.min(this.f7000a.e() - 1, i + i3) - iMax;
        float f3 = 3.0f * f;
        if (iMin >= f3) {
            int iMax2 = Math.max(0, i2 - i3);
            int iMin2 = Math.min(this.f7000a.c() - 1, i2 + i3) - iMax2;
            if (iMin2 >= f3) {
                return new a00(this.f7000a, iMax, iMax2, iMin, iMin2, f, this.b).a();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final jw a(e00 e00Var) throws FormatException, NotFoundException {
        c00 c00VarB = e00Var.b();
        c00 c00VarC = e00Var.c();
        c00 c00VarA = e00Var.a();
        float fA = a(c00VarB, c00VarC, c00VarA);
        if (fA >= 1.0f) {
            int iA = a(c00VarB, c00VarC, c00VarA, fA);
            yz yzVarB = yz.b(iA);
            int iC = yzVarB.c() - 7;
            zz zzVarA = null;
            if (yzVarB.b().length > 0) {
                float fA2 = (c00VarC.a() - c00VarB.a()) + c00VarA.a();
                float fB = (c00VarC.b() - c00VarB.b()) + c00VarA.b();
                float f = 1.0f - (3.0f / iC);
                int iA2 = (int) (c00VarB.a() + ((fA2 - c00VarB.a()) * f));
                int iB = (int) (c00VarB.b() + (f * (fB - c00VarB.b())));
                for (int i = 4; i <= 16; i <<= 1) {
                    try {
                        zzVarA = a(fA, iA2, iB, i);
                        break;
                    } catch (NotFoundException unused) {
                    }
                }
            }
            return new jw(a(this.f7000a, a(c00VarB, c00VarC, c00VarA, zzVarA, iA), iA), zzVarA == null ? new yv[]{c00VarA, c00VarB, c00VarC} : new yv[]{c00VarA, c00VarB, c00VarC, zzVarA});
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public static fw a(fw fwVar, mw mwVar, int i) throws NotFoundException {
        return lw.a().a(fwVar, i, i, mwVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0085, code lost:
    
        if (r15 != 2) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x008d, code lost:
    
        return supwisdom.ow.a(r19, r6, r1, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x008e, code lost:
    
        return Float.NaN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float a(int r18, int r19, int r20, int r21) {
        /*
            r17 = this;
            int r0 = r21 - r19
            int r0 = java.lang.Math.abs(r0)
            int r1 = r20 - r18
            int r1 = java.lang.Math.abs(r1)
            r3 = 1
            if (r0 <= r1) goto L11
            r0 = 1
            goto L12
        L11:
            r0 = 0
        L12:
            if (r0 == 0) goto L1d
            r4 = r18
            r1 = r19
            r6 = r20
            r5 = r21
            goto L25
        L1d:
            r1 = r18
            r4 = r19
            r5 = r20
            r6 = r21
        L25:
            int r7 = r5 - r1
            int r7 = java.lang.Math.abs(r7)
            int r8 = r6 - r4
            int r8 = java.lang.Math.abs(r8)
            int r9 = -r7
            r10 = 2
            int r9 = r9 / r10
            r11 = -1
            if (r1 >= r5) goto L39
            r12 = 1
            goto L3a
        L39:
            r12 = -1
        L3a:
            if (r4 >= r6) goto L3d
            r11 = 1
        L3d:
            int r5 = r5 + r12
            r13 = r1
            r14 = r4
            r15 = 0
        L41:
            if (r13 == r5) goto L80
            if (r0 == 0) goto L47
            r2 = r14
            goto L48
        L47:
            r2 = r13
        L48:
            if (r0 == 0) goto L4c
            r10 = r13
            goto L4d
        L4c:
            r10 = r14
        L4d:
            if (r15 != r3) goto L57
            r3 = r17
            r16 = r0
            r19 = r5
            r0 = 1
            goto L5e
        L57:
            r3 = r17
            r16 = r0
            r19 = r5
            r0 = 0
        L5e:
            supwisdom.fw r5 = r3.f7000a
            boolean r2 = r5.b(r2, r10)
            if (r0 != r2) goto L70
            r0 = 2
            if (r15 != r0) goto L6e
            float r0 = supwisdom.ow.a(r13, r14, r1, r4)
            return r0
        L6e:
            int r15 = r15 + 1
        L70:
            int r9 = r9 + r8
            if (r9 <= 0) goto L78
            if (r14 != r6) goto L76
            goto L84
        L76:
            int r14 = r14 + r11
            int r9 = r9 - r7
        L78:
            int r13 = r13 + r12
            r5 = r19
            r0 = r16
            r3 = 1
            r10 = 2
            goto L41
        L80:
            r3 = r17
            r19 = r5
        L84:
            r0 = 2
            if (r15 != r0) goto L8e
            r5 = r19
            float r0 = supwisdom.ow.a(r5, r6, r1, r4)
            return r0
        L8e:
            r0 = 2143289344(0x7fc00000, float:NaN)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.b00.a(int, int, int, int):float");
    }

    public final jw a(Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        zv zvVar = map == null ? null : (zv) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        this.b = zvVar;
        return a(new d00(this.f7000a, zvVar).a(map));
    }
}
