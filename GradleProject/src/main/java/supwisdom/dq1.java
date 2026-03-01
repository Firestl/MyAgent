package supwisdom;

/* JADX INFO: loaded from: classes2.dex */
public class dq1 extends xp1 {
    public static dq1 c;

    public static dq1 c() {
        if (c == null) {
            synchronized (dq1.class) {
                if (c == null) {
                    c = new dq1();
                }
            }
        }
        return c;
    }

    @Override // supwisdom.xp1
    public String a() {
        return "token_manger_sp_name";
    }

    public String b() {
        return this.f9798a.getString("saved_token", "");
    }
}
