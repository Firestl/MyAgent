package supwisdom;

import com.dcloud.zxing2.NotFoundException;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes.dex */
public final class jy extends ny {
    public final String c;
    public final String d;

    public jy(ew ewVar, String str, String str2) {
        super(ewVar);
        this.c = str2;
        this.d = str;
    }

    @Override // supwisdom.ny
    public int a(int i) {
        return i % 100000;
    }

    @Override // supwisdom.ny
    public void c(StringBuilder sb, int i) {
        sb.append(Operators.BRACKET_START);
        sb.append(this.d);
        sb.append(i / 100000);
        sb.append(Operators.BRACKET_END);
    }

    public final void d(StringBuilder sb, int i) {
        int iA = a().a(i, 16);
        if (iA == 38400) {
            return;
        }
        sb.append(Operators.BRACKET_START);
        sb.append(this.c);
        sb.append(Operators.BRACKET_END);
        int i2 = iA % 32;
        int i3 = iA / 32;
        int i4 = (i3 % 12) + 1;
        int i5 = i3 / 12;
        if (i5 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i5);
        if (i4 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i4);
        if (i2 / 10 == 0) {
            sb.append('0');
        }
        sb.append(i2);
    }

    @Override // supwisdom.oy
    public String c() throws NotFoundException {
        if (b().c() == 84) {
            StringBuilder sb = new StringBuilder();
            a(sb, 8);
            b(sb, 48, 20);
            d(sb, 68);
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
