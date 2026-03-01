package e;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    public static class a extends Exception {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f6313a;

        public a(Throwable th, int i) {
            super(th);
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.f6313a;
        }
    }

    public static a a(Throwable th) {
        a aVar = new a(th, 1003);
        int i = ((d) th).f6314a;
        if (i == 401) {
            aVar.f6313a = "token失效";
        } else if (i != 404) {
            aVar.f6313a = "网络错误";
        } else {
            aVar.f6313a = "404接口地址错误";
        }
        return aVar;
    }
}
