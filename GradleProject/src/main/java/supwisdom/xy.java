package supwisdom;

import com.baidu.speech.core.BDSHttpRequestMaker;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.google.zxing.client.android.encode.MECARDContactEncoder;
import com.huawei.secure.android.common.util.LogsUtil;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.FunctionParser;
import com.taobao.weex.utils.WXUtils;

/* JADX INFO: loaded from: classes.dex */
public final class xy {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ew f9825a;
    public final ry b = new ry();
    public final StringBuilder c = new StringBuilder();

    public xy(ew ewVar) {
        this.f9825a = ewVar;
    }

    public String a(StringBuilder sb, int i) throws FormatException, NotFoundException {
        String str = null;
        while (true) {
            ty tyVarA = a(i, str);
            String strA = wy.a(tyVarA.b());
            if (strA != null) {
                sb.append(strA);
            }
            String strValueOf = tyVarA.d() ? String.valueOf(tyVarA.c()) : null;
            if (i == tyVarA.a()) {
                return sb.toString();
            }
            i = tyVarA.a();
            str = strValueOf;
        }
    }

    public final sy b(int i) throws FormatException {
        char c;
        int iA = a(i, 5);
        if (iA == 15) {
            return new sy(i + 5, '$');
        }
        if (iA >= 5 && iA < 15) {
            return new sy(i + 5, (char) ((iA + 48) - 5));
        }
        int iA2 = a(i, 7);
        if (iA2 >= 64 && iA2 < 90) {
            return new sy(i + 7, (char) (iA2 + 1));
        }
        if (iA2 >= 90 && iA2 < 116) {
            return new sy(i + 7, (char) (iA2 + 7));
        }
        switch (a(i, 8)) {
            case 232:
                c = '!';
                break;
            case 233:
                c = Operators.QUOTE;
                break;
            case 234:
                c = WXUtils.PERCENT;
                break;
            case 235:
                c = '&';
                break;
            case 236:
                c = Operators.SINGLE_QUOTE;
                break;
            case 237:
                c = Operators.BRACKET_START;
                break;
            case 238:
                c = Operators.BRACKET_END;
                break;
            case 239:
                c = LogsUtil.b;
                break;
            case 240:
                c = FunctionParser.Lexer.PLUS;
                break;
            case BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL /* 241 */:
                c = ',';
                break;
            case BDSHttpRequestMaker.TYPE_DOWNLOAD_THIRD_DATA /* 242 */:
                c = FunctionParser.Lexer.MINUS;
                break;
            case BDSHttpRequestMaker.TYPE_DOWNLOAD_FINISH /* 243 */:
                c = '.';
                break;
            case 244:
                c = '/';
                break;
            case 245:
                c = Operators.CONDITION_IF_MIDDLE;
                break;
            case 246:
                c = MECARDContactEncoder.TERMINATOR;
                break;
            case 247:
                c = '<';
                break;
            case 248:
                c = '=';
                break;
            case 249:
                c = '>';
                break;
            case 250:
                c = Operators.CONDITION_IF;
                break;
            case 251:
                c = '_';
                break;
            case 252:
                c = ' ';
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        return new sy(i + 8, c);
    }

    public final uy c(int i) throws FormatException {
        int i2 = i + 7;
        if (i2 > this.f9825a.c()) {
            int iA = a(i, 4);
            return iA == 0 ? new uy(this.f9825a.c(), 10, 10) : new uy(this.f9825a.c(), iA - 1, 10);
        }
        int iA2 = a(i, 7) - 8;
        return new uy(i2, iA2 / 11, iA2 % 11);
    }

    public final boolean d(int i) {
        int i2 = i + 3;
        if (i2 > this.f9825a.c()) {
            return false;
        }
        while (i < i2) {
            if (this.f9825a.a(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public final boolean e(int i) {
        int i2;
        if (i + 1 > this.f9825a.c()) {
            return false;
        }
        for (int i3 = 0; i3 < 5 && (i2 = i3 + i) < this.f9825a.c(); i3++) {
            if (i3 == 2) {
                if (!this.f9825a.a(i + 2)) {
                    return false;
                }
            } else if (this.f9825a.a(i2)) {
                return false;
            }
        }
        return true;
    }

    public final boolean f(int i) {
        int i2;
        if (i + 1 > this.f9825a.c()) {
            return false;
        }
        for (int i3 = 0; i3 < 4 && (i2 = i3 + i) < this.f9825a.c(); i3++) {
            if (this.f9825a.a(i2)) {
                return false;
            }
        }
        return true;
    }

    public final boolean g(int i) {
        int iA;
        if (i + 5 > this.f9825a.c()) {
            return false;
        }
        int iA2 = a(i, 5);
        if (iA2 < 5 || iA2 >= 16) {
            return i + 6 <= this.f9825a.c() && (iA = a(i, 6)) >= 16 && iA < 63;
        }
        return true;
    }

    public final boolean h(int i) {
        int iA;
        if (i + 5 > this.f9825a.c()) {
            return false;
        }
        int iA2 = a(i, 5);
        if (iA2 >= 5 && iA2 < 16) {
            return true;
        }
        if (i + 7 > this.f9825a.c()) {
            return false;
        }
        int iA3 = a(i, 7);
        if (iA3 < 64 || iA3 >= 116) {
            return i + 8 <= this.f9825a.c() && (iA = a(i, 8)) >= 232 && iA < 253;
        }
        return true;
    }

    public final boolean i(int i) {
        if (i + 7 > this.f9825a.c()) {
            return i + 4 <= this.f9825a.c();
        }
        int i2 = i;
        while (true) {
            int i3 = i + 3;
            if (i2 >= i3) {
                return this.f9825a.a(i3);
            }
            if (this.f9825a.a(i2)) {
                return true;
            }
            i2++;
        }
    }

    public final qy d() throws FormatException {
        ty tyVar;
        while (i(this.b.a())) {
            uy uyVarC = c(this.b.a());
            this.b.b(uyVarC.a());
            if (uyVarC.d()) {
                if (uyVarC.e()) {
                    tyVar = new ty(this.b.a(), this.c.toString());
                } else {
                    tyVar = new ty(this.b.a(), this.c.toString(), uyVarC.c());
                }
                return new qy(tyVar, true);
            }
            this.c.append(uyVarC.b());
            if (uyVarC.e()) {
                return new qy(new ty(this.b.a(), this.c.toString()), true);
            }
            this.c.append(uyVarC.c());
        }
        if (f(this.b.a())) {
            this.b.d();
            this.b.a(4);
        }
        return new qy(false);
    }

    public final sy a(int i) {
        char c;
        int iA = a(i, 5);
        if (iA == 15) {
            return new sy(i + 5, '$');
        }
        if (iA >= 5 && iA < 15) {
            return new sy(i + 5, (char) ((iA + 48) - 5));
        }
        int iA2 = a(i, 6);
        if (iA2 >= 32 && iA2 < 58) {
            return new sy(i + 6, (char) (iA2 + 33));
        }
        switch (iA2) {
            case 58:
                c = LogsUtil.b;
                break;
            case 59:
                c = ',';
                break;
            case 60:
                c = FunctionParser.Lexer.MINUS;
                break;
            case 61:
                c = '.';
                break;
            case 62:
                c = '/';
                break;
            default:
                throw new IllegalStateException("Decoding invalid alphanumeric value: " + iA2);
        }
        return new sy(i + 6, c);
    }

    public final qy c() throws FormatException {
        while (h(this.b.a())) {
            sy syVarB = b(this.b.a());
            this.b.b(syVarB.a());
            if (syVarB.c()) {
                return new qy(new ty(this.b.a(), this.c.toString()), true);
            }
            this.c.append(syVarB.b());
        }
        if (d(this.b.a())) {
            this.b.a(3);
            this.b.f();
        } else if (e(this.b.a())) {
            if (this.b.a() + 5 < this.f9825a.c()) {
                this.b.a(5);
            } else {
                this.b.b(this.f9825a.c());
            }
            this.b.d();
        }
        return new qy(false);
    }

    public final ty b() throws FormatException {
        qy qyVarD;
        boolean zB;
        do {
            int iA = this.b.a();
            if (this.b.b()) {
                qyVarD = a();
                zB = qyVarD.b();
            } else if (this.b.c()) {
                qyVarD = c();
                zB = qyVarD.b();
            } else {
                qyVarD = d();
                zB = qyVarD.b();
            }
            if (!(iA != this.b.a()) && !zB) {
                break;
            }
        } while (!zB);
        return qyVarD.a();
    }

    public ty a(int i, String str) throws FormatException {
        this.c.setLength(0);
        if (str != null) {
            this.c.append(str);
        }
        this.b.b(i);
        ty tyVarB = b();
        if (tyVarB != null && tyVarB.d()) {
            return new ty(this.b.a(), this.c.toString(), tyVarB.c());
        }
        return new ty(this.b.a(), this.c.toString());
    }

    public int a(int i, int i2) {
        return a(this.f9825a, i, i2);
    }

    public final qy a() {
        while (g(this.b.a())) {
            sy syVarA = a(this.b.a());
            this.b.b(syVarA.a());
            if (syVarA.c()) {
                return new qy(new ty(this.b.a(), this.c.toString()), true);
            }
            this.c.append(syVarA.b());
        }
        if (d(this.b.a())) {
            this.b.a(3);
            this.b.f();
        } else if (e(this.b.a())) {
            if (this.b.a() + 5 < this.f9825a.c()) {
                this.b.a(5);
            } else {
                this.b.b(this.f9825a.c());
            }
            this.b.e();
        }
        return new qy(false);
    }

    public static int a(ew ewVar, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (ewVar.a(i + i4)) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }
}
