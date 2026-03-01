package supwisdom;

import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AxisChartGraphicBuffer.java */
/* JADX INFO: loaded from: classes.dex */
public class ck {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f7223a = false;

    /* JADX INFO: compiled from: AxisChartGraphicBuffer.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float[] f7224a;
        public float[] b;

        public float[] a() {
            return this.f7224a;
        }

        public float[] b() {
            return this.b;
        }
    }

    /* JADX INFO: compiled from: AxisChartGraphicBuffer.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public List<Path> f7225a = new ArrayList();
        public List<Path> b = new ArrayList();

        public List<Path> a() {
            return this.f7225a;
        }

        public List<Path> b() {
            return this.b;
        }

        public void a(List<Path> list) {
            this.f7225a.clear();
            this.f7225a.addAll(list);
        }

        public void b(List<Path> list) {
            this.b.clear();
            this.b.addAll(list);
        }
    }

    public void a(boolean z) {
        this.f7223a = z;
    }

    public boolean a() {
        return this.f7223a;
    }
}
