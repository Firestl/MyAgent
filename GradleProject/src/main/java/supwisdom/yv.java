package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes.dex */
public class yv {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f9920a;
    public final float b;

    public yv(float f, float f2) {
        this.f9920a = f;
        this.b = f2;
    }

    public static float a(yv yvVar, yv yvVar2, yv yvVar3) {
        float f = yvVar2.f9920a;
        float f2 = yvVar2.b;
        return ((yvVar3.f9920a - f) * (yvVar.b - f2)) - ((yvVar3.b - f2) * (yvVar.f9920a - f));
    }

    public final float b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof yv)) {
            return false;
        }
        yv yvVar = (yv) obj;
        return this.f9920a == yvVar.f9920a && this.b == yvVar.b;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f9920a) * 31) + Float.floatToIntBits(this.b);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(25);
        sb.append(Operators.BRACKET_START);
        sb.append(this.f9920a);
        sb.append(',');
        sb.append(this.b);
        sb.append(Operators.BRACKET_END);
        return sb.toString();
    }

    public static float a(yv yvVar, yv yvVar2) {
        return ow.a(yvVar.f9920a, yvVar.b, yvVar2.f9920a, yvVar2.b);
    }

    public final float a() {
        return this.f9920a;
    }

    public static void a(yv[] yvVarArr) {
        yv yvVar;
        yv yvVar2;
        yv yvVar3;
        float fA = a(yvVarArr[0], yvVarArr[1]);
        float fA2 = a(yvVarArr[1], yvVarArr[2]);
        float fA3 = a(yvVarArr[0], yvVarArr[2]);
        if (fA2 >= fA && fA2 >= fA3) {
            yvVar = yvVarArr[0];
            yvVar2 = yvVarArr[1];
            yvVar3 = yvVarArr[2];
        } else if (fA3 >= fA2 && fA3 >= fA) {
            yvVar = yvVarArr[1];
            yvVar2 = yvVarArr[0];
            yvVar3 = yvVarArr[2];
        } else {
            yvVar = yvVarArr[2];
            yvVar2 = yvVarArr[0];
            yvVar3 = yvVarArr[1];
        }
        if (a(yvVar2, yvVar, yvVar3) < 0.0f) {
            yv yvVar4 = yvVar3;
            yvVar3 = yvVar2;
            yvVar2 = yvVar4;
        }
        yvVarArr[0] = yvVar2;
        yvVarArr[1] = yvVar;
        yvVarArr[2] = yvVar3;
    }
}
