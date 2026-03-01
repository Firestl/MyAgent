package supwisdom;

import android.view.View;
import java.util.Map;

/* JADX INFO: compiled from: PlatformManager.java */
/* JADX INFO: loaded from: classes.dex */
public class yi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c f9885a;
    public d b;
    public e c;

    /* JADX INFO: compiled from: PlatformManager.java */
    public interface c {
        double a(double d, Object... objArr);

        double b(double d, Object... objArr);
    }

    /* JADX INFO: compiled from: PlatformManager.java */
    public interface d {
        View a(String str, Object... objArr);
    }

    /* JADX INFO: compiled from: PlatformManager.java */
    public interface e {
        void a(View view, String str, Object obj, c cVar, Map<String, Object> map, Object... objArr);
    }

    public d b() {
        return this.b;
    }

    public e c() {
        return this.c;
    }

    public yi() {
    }

    /* JADX INFO: compiled from: PlatformManager.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c f9886a;
        public d b;
        public e c;

        public yi a() {
            yi yiVar = new yi();
            yiVar.b = this.b;
            yiVar.f9885a = this.f9886a;
            yiVar.c = this.c;
            return yiVar;
        }

        public b a(c cVar) {
            this.f9886a = cVar;
            return this;
        }

        public b a(d dVar) {
            this.b = dVar;
            return this;
        }

        public b a(e eVar) {
            this.c = eVar;
            return this;
        }
    }

    public c a() {
        return this.f9885a;
    }
}
