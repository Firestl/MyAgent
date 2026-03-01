package supwisdom;

import supwisdom.bt1;

/* JADX INFO: loaded from: classes3.dex */
public class mr1 extends gr1 {
    public mr1(String str, String str2) {
        super(str, str2);
    }

    @Override // supwisdom.gr1
    public bt1 a(String str) {
        if (!this.f.endsWith("/")) {
            this.f += "/";
        }
        this.f += str;
        bt1.a aVar = new bt1.a();
        aVar.b(this.f);
        return aVar.a();
    }
}
