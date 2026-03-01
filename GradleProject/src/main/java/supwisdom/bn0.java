package supwisdom;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ShapePath.java */
/* JADX INFO: loaded from: classes.dex */
public class bn0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f7079a;
    public float b;
    public float c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<c> f7080e = new ArrayList();

    /* JADX INFO: compiled from: ShapePath.java */
    public static class a extends c {
        public static final RectF h = new RectF();
        public float b;
        public float c;
        public float d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f7081e;
        public float f;
        public float g;

        public a(float f, float f2, float f3, float f4) {
            this.b = f;
            this.c = f2;
            this.d = f3;
            this.f7081e = f4;
        }

        @Override // supwisdom.bn0.c
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f7082a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            h.set(this.b, this.c, this.d, this.f7081e);
            path.arcTo(h, this.f, this.g, false);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class b extends c {
        public float b;
        public float c;

        @Override // supwisdom.bn0.c
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f7082a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.b, this.c);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f7082a = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    public bn0() {
        b(0.0f, 0.0f);
    }

    public void a(float f, float f2) {
        b bVar = new b();
        bVar.b = f;
        bVar.c = f2;
        this.f7080e.add(bVar);
        this.c = f;
        this.d = f2;
    }

    public void b(float f, float f2) {
        this.f7079a = f;
        this.b = f2;
        this.c = f;
        this.d = f2;
        this.f7080e.clear();
    }

    public void a(float f, float f2, float f3, float f4, float f5, float f6) {
        a aVar = new a(f, f2, f3, f4);
        aVar.f = f5;
        aVar.g = f6;
        this.f7080e.add(aVar);
        double d = f5 + f6;
        this.c = ((f + f3) * 0.5f) + (((f3 - f) / 2.0f) * ((float) Math.cos(Math.toRadians(d))));
        this.d = ((f2 + f4) * 0.5f) + (((f4 - f2) / 2.0f) * ((float) Math.sin(Math.toRadians(d))));
    }

    public void a(Matrix matrix, Path path) {
        int size = this.f7080e.size();
        for (int i = 0; i < size; i++) {
            this.f7080e.get(i).a(matrix, path);
        }
    }
}
