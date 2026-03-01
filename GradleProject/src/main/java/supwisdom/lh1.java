package supwisdom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.NetworkInfo;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: RequestHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class lh1 {

    /* JADX INFO: compiled from: RequestHandler.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Picasso.LoadedFrom f8292a;
        public final Bitmap b;
        public final InputStream c;
        public final int d;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public a(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
            this(bitmap, null, loadedFrom, 0);
            rh1.a(bitmap, "bitmap == null");
        }

        public Bitmap a() {
            return this.b;
        }

        public int b() {
            return this.d;
        }

        public Picasso.LoadedFrom c() {
            return this.f8292a;
        }

        public InputStream d() {
            return this.c;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public a(InputStream inputStream, Picasso.LoadedFrom loadedFrom) {
            this(null, inputStream, loadedFrom, 0);
            rh1.a(inputStream, "stream == null");
        }

        public a(Bitmap bitmap, InputStream inputStream, Picasso.LoadedFrom loadedFrom, int i) {
            if ((inputStream != null) ^ (bitmap != null)) {
                this.b = bitmap;
                this.c = inputStream;
                rh1.a(loadedFrom, "loadedFrom == null");
                this.f8292a = loadedFrom;
                this.d = i;
                return;
            }
            throw new AssertionError();
        }
    }

    public static boolean a(BitmapFactory.Options options) {
        return options != null && options.inJustDecodeBounds;
    }

    public static BitmapFactory.Options b(jh1 jh1Var) {
        boolean zC = jh1Var.c();
        boolean z = jh1Var.q != null;
        BitmapFactory.Options options = null;
        if (zC || z) {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = zC;
            if (z) {
                options.inPreferredConfig = jh1Var.q;
            }
        }
        return options;
    }

    public int a() {
        return 0;
    }

    public abstract a a(jh1 jh1Var, int i) throws IOException;

    public abstract boolean a(jh1 jh1Var);

    public boolean a(boolean z, NetworkInfo networkInfo) {
        return false;
    }

    public boolean b() {
        return false;
    }

    public static void a(int i, int i2, BitmapFactory.Options options, jh1 jh1Var) {
        a(i, i2, options.outWidth, options.outHeight, options, jh1Var);
    }

    public static void a(int i, int i2, int i3, int i4, BitmapFactory.Options options, jh1 jh1Var) {
        int iMin;
        double dFloor;
        if (i4 > i2 || i3 > i) {
            if (i2 == 0) {
                dFloor = Math.floor(i3 / i);
            } else if (i == 0) {
                dFloor = Math.floor(i4 / i2);
            } else {
                int iFloor = (int) Math.floor(i4 / i2);
                int iFloor2 = (int) Math.floor(i3 / i);
                if (jh1Var.k) {
                    iMin = Math.max(iFloor, iFloor2);
                } else {
                    iMin = Math.min(iFloor, iFloor2);
                }
            }
            iMin = (int) dFloor;
        } else {
            iMin = 1;
        }
        options.inSampleSize = iMin;
        options.inJustDecodeBounds = false;
    }
}
