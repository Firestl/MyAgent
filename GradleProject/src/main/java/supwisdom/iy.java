package supwisdom;

import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes.dex */
public final class iy extends my {
    public iy(ew ewVar) {
        super(ewVar);
    }

    @Override // supwisdom.oy
    public String c() throws FormatException, NotFoundException {
        if (b().c() < 48) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder sb = new StringBuilder();
        a(sb, 8);
        int iA = a().a(48, 2);
        sb.append("(393");
        sb.append(iA);
        sb.append(Operators.BRACKET_END);
        int iA2 = a().a(50, 10);
        if (iA2 / 100 == 0) {
            sb.append('0');
        }
        if (iA2 / 10 == 0) {
            sb.append('0');
        }
        sb.append(iA2);
        sb.append(a().a(60, (String) null).b());
        return sb.toString();
    }
}
