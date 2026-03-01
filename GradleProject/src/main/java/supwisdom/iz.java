package supwisdom;

import com.dcloud.zxing2.FormatException;

/* JADX INFO: loaded from: classes.dex */
public final class iz extends hz {
    public final boolean c;

    public iz(dz dzVar, boolean z) {
        super(dzVar);
        this.c = z;
    }

    public int a(bz bzVar) {
        ez[] ezVarArrB = b();
        f();
        a(ezVarArrB, bzVar);
        dz dzVarA = a();
        yv yvVarH = this.c ? dzVarA.h() : dzVarA.i();
        yv yvVarB = this.c ? dzVarA.b() : dzVarA.c();
        int iC = c((int) yvVarH.b());
        int iC2 = c((int) yvVarB.b());
        float fC = (iC2 - iC) / bzVar.c();
        int iC3 = -1;
        int i = 0;
        int iMax = 1;
        while (iC < iC2) {
            if (ezVarArrB[iC] != null) {
                ez ezVar = ezVarArrB[iC];
                int iC4 = ezVar.c() - iC3;
                if (iC4 == 0) {
                    i++;
                } else {
                    if (iC4 == 1) {
                        iMax = Math.max(iMax, i);
                        iC3 = ezVar.c();
                    } else if (iC4 < 0 || ezVar.c() >= bzVar.c() || iC4 > iC) {
                        ezVarArrB[iC] = null;
                    } else {
                        if (iMax > 2) {
                            iC4 *= iMax - 2;
                        }
                        boolean z = iC4 >= iC;
                        for (int i2 = 1; i2 <= iC4 && !z; i2++) {
                            z = ezVarArrB[iC - i2] != null;
                        }
                        if (z) {
                            ezVarArrB[iC] = null;
                        } else {
                            iC3 = ezVar.c();
                        }
                    }
                    i = 1;
                }
            }
            iC++;
        }
        return (int) (((double) fC) + 0.5d);
    }

    public int b(bz bzVar) {
        dz dzVarA = a();
        yv yvVarH = this.c ? dzVarA.h() : dzVarA.i();
        yv yvVarB = this.c ? dzVarA.b() : dzVarA.c();
        int iC = c((int) yvVarH.b());
        int iC2 = c((int) yvVarB.b());
        float fC = (iC2 - iC) / bzVar.c();
        ez[] ezVarArrB = b();
        int iC3 = -1;
        int i = 0;
        int iMax = 1;
        while (iC < iC2) {
            if (ezVarArrB[iC] != null) {
                ez ezVar = ezVarArrB[iC];
                ezVar.h();
                int iC4 = ezVar.c() - iC3;
                if (iC4 == 0) {
                    i++;
                } else {
                    if (iC4 == 1) {
                        iMax = Math.max(iMax, i);
                        iC3 = ezVar.c();
                    } else if (ezVar.c() >= bzVar.c()) {
                        ezVarArrB[iC] = null;
                    } else {
                        iC3 = ezVar.c();
                    }
                    i = 1;
                }
            }
            iC++;
        }
        return (int) (((double) fC) + 0.5d);
    }

    public bz c() {
        ez[] ezVarArrB = b();
        cz czVar = new cz();
        cz czVar2 = new cz();
        cz czVar3 = new cz();
        cz czVar4 = new cz();
        for (ez ezVar : ezVarArrB) {
            if (ezVar != null) {
                ezVar.h();
                int iE = ezVar.e() % 30;
                int iC = ezVar.c();
                if (!this.c) {
                    iC += 2;
                }
                int i = iC % 3;
                if (i == 0) {
                    czVar2.a((iE * 3) + 1);
                } else if (i == 1) {
                    czVar4.a(iE / 3);
                    czVar3.a(iE % 3);
                } else if (i == 2) {
                    czVar.a(iE + 1);
                }
            }
        }
        if (czVar.a().length == 0 || czVar2.a().length == 0 || czVar3.a().length == 0 || czVar4.a().length == 0 || czVar.a()[0] < 1 || czVar2.a()[0] + czVar3.a()[0] < 3 || czVar2.a()[0] + czVar3.a()[0] > 90) {
            return null;
        }
        bz bzVar = new bz(czVar.a()[0], czVar2.a()[0], czVar3.a()[0], czVar4.a()[0]);
        a(ezVarArrB, bzVar);
        return bzVar;
    }

    public int[] d() throws FormatException {
        int iC;
        bz bzVarC = c();
        if (bzVarC == null) {
            return null;
        }
        b(bzVarC);
        int iC2 = bzVarC.c();
        int[] iArr = new int[iC2];
        for (ez ezVar : b()) {
            if (ezVar != null && (iC = ezVar.c()) < iC2) {
                iArr[iC] = iArr[iC] + 1;
            }
        }
        return iArr;
    }

    public boolean e() {
        return this.c;
    }

    public void f() {
        for (ez ezVar : b()) {
            if (ezVar != null) {
                ezVar.h();
            }
        }
    }

    @Override // supwisdom.hz
    public String toString() {
        return "IsLeft: " + this.c + '\n' + super.toString();
    }

    public final void a(ez[] ezVarArr, bz bzVar) {
        for (int i = 0; i < ezVarArr.length; i++) {
            ez ezVar = ezVarArr[i];
            if (ezVarArr[i] != null) {
                int iE = ezVar.e() % 30;
                int iC = ezVar.c();
                if (iC > bzVar.c()) {
                    ezVarArr[i] = null;
                } else {
                    if (!this.c) {
                        iC += 2;
                    }
                    int i2 = iC % 3;
                    if (i2 != 0) {
                        if (i2 != 1) {
                            if (i2 == 2 && iE + 1 != bzVar.a()) {
                                ezVarArr[i] = null;
                            }
                        } else if (iE / 3 != bzVar.b() || iE % 3 != bzVar.d()) {
                            ezVarArr[i] = null;
                        }
                    } else if ((iE * 3) + 1 != bzVar.e()) {
                        ezVarArr[i] = null;
                    }
                }
            }
        }
    }
}
