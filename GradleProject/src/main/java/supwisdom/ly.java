package supwisdom;

import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.NotFoundException;

/* JADX INFO: loaded from: classes.dex */
public final class ly extends my {
    public ly(ew ewVar) {
        super(ewVar);
    }

    @Override // supwisdom.oy
    public String c() throws FormatException, NotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append("(01)");
        int length = sb.length();
        sb.append(a().a(4, 4));
        a(sb, 8, length);
        return a().a(sb, 48);
    }
}
