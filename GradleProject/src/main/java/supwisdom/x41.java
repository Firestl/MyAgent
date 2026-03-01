package supwisdom;

import com.alibaba.fastjson.parser.JSONLexer;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import io.dcloud.nineoldandroids.util.ReflectiveProperty;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class x41 extends p51 {
    public final z51 c;
    public z51 d;

    public x41(w51 w51Var, s51 s51Var) {
        super(w51Var, s51Var);
        String strE = e().d().e();
        if (g()) {
            this.c = z51.a(strE);
        } else {
            this.c = z51.b(strE);
        }
        this.d = null;
    }

    public final int a(boolean z) {
        return b(z).c().h();
    }

    @Override // supwisdom.p51, supwisdom.u41
    public final int b(u41 u41Var) {
        int iB = super.b(u41Var);
        return iB != 0 ? iB : this.c.compareTo(((x41) u41Var).c);
    }

    public final z51 f() {
        return this.c;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final boolean g() {
        w51 w51VarD = d();
        if (w51VarD.equals(w51.v)) {
            String strE = e().f().e();
            strE.hashCode();
            if (strE.equals("invoke") || strE.equals("invokeExact")) {
                return true;
            }
        } else if (w51VarD.equals(w51.w)) {
            String strE2 = e().f().e();
            strE2.hashCode();
            byte b = -1;
            switch (strE2.hashCode()) {
                case -1946504908:
                    if (strE2.equals("getAndBitwiseOrRelease")) {
                        b = 0;
                    }
                    break;
                case -1686727776:
                    if (strE2.equals("getAndBitwiseAndRelease")) {
                        b = 1;
                    }
                    break;
                case -1671098288:
                    if (strE2.equals("compareAndSet")) {
                        b = 2;
                    }
                    break;
                case -1292078254:
                    if (strE2.equals("compareAndExchangeRelease")) {
                        b = 3;
                    }
                    break;
                case -1117944904:
                    if (strE2.equals("weakCompareAndSet")) {
                        b = 4;
                    }
                    break;
                case -1103072857:
                    if (strE2.equals("getAndAddRelease")) {
                        b = 5;
                    }
                    break;
                case -1032914329:
                    if (strE2.equals("getAndBitwiseAnd")) {
                        b = 6;
                    }
                    break;
                case -1032892181:
                    if (strE2.equals("getAndBitwiseXor")) {
                        b = 7;
                    }
                    break;
                case -794517348:
                    if (strE2.equals("getAndBitwiseXorRelease")) {
                        b = 8;
                    }
                    break;
                case -567150350:
                    if (strE2.equals("weakCompareAndSetPlain")) {
                        b = 9;
                    }
                    break;
                case -240822786:
                    if (strE2.equals("weakCompareAndSetAcquire")) {
                        b = 10;
                    }
                    break;
                case -230706875:
                    if (strE2.equals("setRelease")) {
                        b = 11;
                    }
                    break;
                case -127361888:
                    if (strE2.equals("getAcquire")) {
                        b = 12;
                    }
                    break;
                case -37641530:
                    if (strE2.equals("getAndSetRelease")) {
                        b = 13;
                    }
                    break;
                case 102230:
                    if (strE2.equals("get")) {
                        b = com.umeng.analytics.pro.db.l;
                    }
                    break;
                case 113762:
                    if (strE2.equals(ReflectiveProperty.PREFIX_SET)) {
                        b = 15;
                    }
                    break;
                case 93645315:
                    if (strE2.equals("getAndBitwiseOrAcquire")) {
                        b = 16;
                    }
                    break;
                case 101293086:
                    if (strE2.equals("setVolatile")) {
                        b = 17;
                    }
                    break;
                case 189872914:
                    if (strE2.equals("getVolatile")) {
                        b = SharedPreferencesNewImpl.FINISH_MARK;
                    }
                    break;
                case 282707520:
                    if (strE2.equals("getAndAdd")) {
                        b = 19;
                    }
                    break;
                case 282724865:
                    if (strE2.equals("getAndSet")) {
                        b = 20;
                    }
                    break;
                case 353422447:
                    if (strE2.equals("getAndBitwiseAndAcquire")) {
                        b = 21;
                    }
                    break;
                case 470702883:
                    if (strE2.equals("setOpaque")) {
                        b = 22;
                    }
                    break;
                case 685319959:
                    if (strE2.equals("getOpaque")) {
                        b = 23;
                    }
                    break;
                case 748071969:
                    if (strE2.equals("compareAndExchangeAcquire")) {
                        b = 24;
                    }
                    break;
                case 937077366:
                    if (strE2.equals("getAndAddAcquire")) {
                        b = 25;
                    }
                    break;
                case 1245632875:
                    if (strE2.equals("getAndBitwiseXorAcquire")) {
                        b = JSONLexer.EOI;
                    }
                    break;
                case 1352153939:
                    if (strE2.equals("getAndBitwiseOr")) {
                        b = 27;
                    }
                    break;
                case 1483964149:
                    if (strE2.equals("compareAndExchange")) {
                        b = 28;
                    }
                    break;
                case 2002508693:
                    if (strE2.equals("getAndSetAcquire")) {
                        b = 29;
                    }
                    break;
                case 2013994287:
                    if (strE2.equals("weakCompareAndSetRelease")) {
                        b = 30;
                    }
                    break;
            }
            switch (b) {
            }
            return true;
        }
        return false;
    }

    @Override // supwisdom.c61
    public final b61 getType() {
        return this.c.d();
    }

    public final z51 b(boolean z) {
        if (z) {
            return this.c;
        }
        if (this.d == null) {
            this.d = this.c.a(d().d());
        }
        return this.d;
    }
}
