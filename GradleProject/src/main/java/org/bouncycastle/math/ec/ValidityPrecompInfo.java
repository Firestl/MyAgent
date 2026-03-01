package org.bouncycastle.math.ec;

/* JADX INFO: loaded from: classes3.dex */
public class ValidityPrecompInfo implements PreCompInfo {
    public static final String PRECOMP_NAME = "bc_validity";
    public boolean failed = false;
    public boolean curveEquationPassed = false;
    public boolean orderPassed = false;

    public boolean hasCurveEquationPassed() {
        return this.curveEquationPassed;
    }

    public boolean hasFailed() {
        return this.failed;
    }

    public boolean hasOrderPassed() {
        return this.orderPassed;
    }

    public void reportCurveEquationPassed() {
        this.curveEquationPassed = true;
    }

    public void reportFailed() {
        this.failed = true;
    }

    public void reportOrderPassed() {
        this.orderPassed = true;
    }
}
