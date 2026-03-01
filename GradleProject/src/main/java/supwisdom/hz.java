package supwisdom;

import java.util.Formatter;

/* JADX INFO: loaded from: classes.dex */
public class hz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final dz f7889a;
    public final ez[] b;

    public hz(dz dzVar) {
        this.f7889a = new dz(dzVar);
        this.b = new ez[(dzVar.e() - dzVar.g()) + 1];
    }

    public final dz a() {
        return this.f7889a;
    }

    public final ez b(int i) {
        ez ezVar;
        ez ezVar2;
        ez ezVarA = a(i);
        if (ezVarA != null) {
            return ezVarA;
        }
        for (int i2 = 1; i2 < 5; i2++) {
            int iC = c(i) - i2;
            if (iC >= 0 && (ezVar2 = this.b[iC]) != null) {
                return ezVar2;
            }
            int iC2 = c(i) + i2;
            ez[] ezVarArr = this.b;
            if (iC2 < ezVarArr.length && (ezVar = ezVarArr[iC2]) != null) {
                return ezVar;
            }
        }
        return null;
    }

    public final int c(int i) {
        return i - this.f7889a.g();
    }

    public String toString() {
        Formatter formatter = new Formatter();
        int i = 0;
        for (ez ezVar : this.b) {
            if (ezVar == null) {
                formatter.format("%3d:    |   %n", Integer.valueOf(i));
                i++;
            } else {
                formatter.format("%3d: %3d|%3d%n", Integer.valueOf(i), Integer.valueOf(ezVar.c()), Integer.valueOf(ezVar.e()));
                i++;
            }
        }
        String string = formatter.toString();
        formatter.close();
        return string;
    }

    public final ez a(int i) {
        return this.b[c(i)];
    }

    public final void a(int i, ez ezVar) {
        this.b[c(i)] = ezVar;
    }

    public final ez[] b() {
        return this.b;
    }
}
