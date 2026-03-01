package supwisdom;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class e51 extends p51 {
    public f51 c;

    @Override // supwisdom.u41
    public String c() {
        return "enum";
    }

    public f51 f() {
        if (this.c == null) {
            this.c = new f51(d(), e());
        }
        return this.c;
    }

    @Override // supwisdom.c61
    public b61 getType() {
        return d().d();
    }
}
