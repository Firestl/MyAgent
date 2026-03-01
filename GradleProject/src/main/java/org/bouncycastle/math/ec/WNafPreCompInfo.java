package org.bouncycastle.math.ec;

/* JADX INFO: loaded from: classes3.dex */
public class WNafPreCompInfo implements PreCompInfo {
    public volatile int promotionCountdown = 4;
    public int confWidth = -1;
    public ECPoint[] preComp = null;
    public ECPoint[] preCompNeg = null;
    public ECPoint twice = null;
    public int width = -1;

    public int decrementPromotionCountdown() {
        int i = this.promotionCountdown;
        if (i <= 0) {
            return i;
        }
        int i2 = i - 1;
        this.promotionCountdown = i2;
        return i2;
    }

    public int getConfWidth() {
        return this.confWidth;
    }

    public ECPoint[] getPreComp() {
        return this.preComp;
    }

    public ECPoint[] getPreCompNeg() {
        return this.preCompNeg;
    }

    public int getPromotionCountdown() {
        return this.promotionCountdown;
    }

    public ECPoint getTwice() {
        return this.twice;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isPromoted() {
        return this.promotionCountdown <= 0;
    }

    public void setConfWidth(int i) {
        this.confWidth = i;
    }

    public void setPreComp(ECPoint[] eCPointArr) {
        this.preComp = eCPointArr;
    }

    public void setPreCompNeg(ECPoint[] eCPointArr) {
        this.preCompNeg = eCPointArr;
    }

    public void setPromotionCountdown(int i) {
        this.promotionCountdown = i;
    }

    public void setTwice(ECPoint eCPoint) {
        this.twice = eCPoint;
    }

    public void setWidth(int i) {
        this.width = i;
    }
}
