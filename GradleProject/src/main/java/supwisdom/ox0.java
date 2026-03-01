package supwisdom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Build;
import com.nostra13.dcloudimageloader.core.assist.ImageScaleType;
import com.nostra13.dcloudimageloader.core.download.ImageDownloader;
import io.dcloud.common.DHInterface.IFeature;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class ox0 implements px0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f8728a;

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ix0 f8730a;
        public final a b;

        public b(ix0 ix0Var, a aVar) {
            this.f8730a = ix0Var;
            this.b = aVar;
        }
    }

    public ox0(boolean z) {
        this.f8728a = z;
    }

    public final boolean a(String str, String str2) {
        return Build.VERSION.SDK_INT >= 5 && "image/jpeg".equalsIgnoreCase(str2) && ImageDownloader.Scheme.ofUri(str) == ImageDownloader.Scheme.FILE;
    }

    public InputStream b(qx0 qx0Var) throws IOException {
        return qx0Var.b().a(qx0Var.f(), qx0Var.c());
    }

    public Bitmap a(Bitmap bitmap, qx0 qx0Var, int i, boolean z) {
        Matrix matrix = new Matrix();
        ImageScaleType imageScaleTypeE = qx0Var.e();
        if (imageScaleTypeE == ImageScaleType.EXACTLY || imageScaleTypeE == ImageScaleType.EXACTLY_STRETCHED) {
            ix0 ix0Var = new ix0(bitmap.getWidth(), bitmap.getHeight(), i);
            float fB = zx0.b(ix0Var, qx0Var.g(), qx0Var.h(), imageScaleTypeE == ImageScaleType.EXACTLY_STRETCHED);
            if (Float.compare(fB, 1.0f) != 0) {
                matrix.setScale(fB, fB);
                if (this.f8728a) {
                    by0.a("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", ix0Var, ix0Var.a(fB), Float.valueOf(fB), qx0Var.d());
                }
            }
        }
        if (z) {
            matrix.postScale(-1.0f, 1.0f);
            if (this.f8728a) {
                by0.a("Flip image horizontally [%s]", qx0Var.d());
            }
        }
        if (i != 0) {
            matrix.postRotate(i);
            if (this.f8728a) {
                by0.a("Rotate image on %1$d�� [%2$s]", Integer.valueOf(i), qx0Var.d());
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (bitmapCreateBitmap != bitmap) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    public InputStream b(InputStream inputStream, qx0 qx0Var) throws IOException {
        try {
            inputStream.reset();
            return inputStream;
        } catch (IOException unused) {
            return b(qx0Var);
        }
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f8729a;
        public final boolean b;

        public a() {
            this.f8729a = 0;
            this.b = false;
        }

        public a(int i, boolean z) {
            this.f8729a = i;
            this.b = z;
        }
    }

    @Override // supwisdom.px0
    public Bitmap a(qx0 qx0Var) throws IOException {
        InputStream inputStreamB = b(qx0Var);
        b bVarA = a(inputStreamB, qx0Var);
        Bitmap bitmapA = a(b(inputStreamB, qx0Var), a(bVarA.f8730a, qx0Var));
        if (bitmapA == null) {
            by0.b("Image can't be decoded [%s]", qx0Var.d());
            return bitmapA;
        }
        a aVar = bVarA.b;
        return a(bitmapA, qx0Var, aVar.f8729a, aVar.b);
    }

    public Bitmap a(InputStream inputStream, BitmapFactory.Options options) throws IOException {
        try {
            return BitmapFactory.decodeStream(inputStream, null, options);
        } finally {
            ay0.a(inputStream);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    public a a(String str) {
        int i = 0;
        boolean z = 1;
        try {
        } catch (IOException unused) {
            by0.d("Can't read EXIF tags from file [%s]", str);
        }
        switch (new ExifInterface(ImageDownloader.Scheme.FILE.crop(str)).getAttributeInt(IFeature.F_ORIENTATION, 1)) {
            case 1:
            default:
                z = 0;
                break;
            case 2:
                break;
            case 3:
                z = i;
                i = 180;
                break;
            case 4:
                i = 1;
                z = i;
                i = 180;
                break;
            case 5:
                i = 1;
                z = i;
                i = 270;
                break;
            case 6:
                z = i;
                i = 90;
                break;
            case 7:
                i = 1;
                z = i;
                i = 90;
                break;
            case 8:
                z = i;
                i = 270;
                break;
        }
        return new a(i, z);
    }

    public b a(InputStream inputStream, qx0 qx0Var) throws IOException {
        a aVar;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        String strF = qx0Var.f();
        if (qx0Var.i() && a(strF, options.outMimeType)) {
            aVar = a(strF);
        } else {
            aVar = new a();
        }
        return new b(new ix0(options.outWidth, options.outHeight, aVar.f8729a), aVar);
    }

    public BitmapFactory.Options a(ix0 ix0Var, qx0 qx0Var) {
        ImageScaleType imageScaleTypeE = qx0Var.e();
        ix0 ix0VarG = qx0Var.g();
        int i = 1;
        if (imageScaleTypeE != ImageScaleType.NONE) {
            int iA = zx0.a(ix0Var, ix0VarG, qx0Var.h(), imageScaleTypeE == ImageScaleType.IN_SAMPLE_POWER_OF_2);
            if (this.f8728a) {
                by0.a("Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]", ix0Var, ix0Var.a(iA), Integer.valueOf(iA), qx0Var.d());
            }
            i = iA;
        }
        BitmapFactory.Options optionsA = qx0Var.a();
        optionsA.inSampleSize = i;
        return optionsA;
    }
}
