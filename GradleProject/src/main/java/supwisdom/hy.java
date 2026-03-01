package supwisdom;

import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes.dex */
public final class hy extends my {
    public hy(ew ewVar) {
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
        sb.append("(392");
        sb.append(iA);
        sb.append(Operators.BRACKET_END);
        sb.append(a().a(50, (String) null).b());
        return sb.toString();
    }
}
