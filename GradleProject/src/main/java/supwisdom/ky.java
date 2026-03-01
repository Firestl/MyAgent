package supwisdom;

import com.dcloud.zxing2.NotFoundException;

/* JADX INFO: loaded from: classes.dex */
public abstract class ky extends ny {
    public ky(ew ewVar) {
        super(ewVar);
    }

    @Override // supwisdom.oy
    public String c() throws NotFoundException {
        if (b().c() != 60) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder sb = new StringBuilder();
        a(sb, 5);
        b(sb, 45, 15);
        return sb.toString();
    }
}
