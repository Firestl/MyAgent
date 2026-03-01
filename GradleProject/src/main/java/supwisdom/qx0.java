package supwisdom;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import android.os.Build;
import com.nostra13.dcloudimageloader.core.assist.ImageScaleType;
import com.nostra13.dcloudimageloader.core.assist.ViewScaleType;
import com.nostra13.dcloudimageloader.core.download.ImageDownloader;

/* JADX INFO: loaded from: classes2.dex */
public class qx0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8967a;
    public final String b;
    public final ix0 c;
    public final ImageScaleType d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ViewScaleType f8968e;
    public final ImageDownloader f;
    public final Object g;
    public final boolean h;
    public final BitmapFactory.Options i;

    public qx0(String str, String str2, ix0 ix0Var, ViewScaleType viewScaleType, ImageDownloader imageDownloader, yw0 yw0Var) {
        this.f8967a = str;
        this.b = str2;
        this.c = ix0Var;
        this.d = yw0Var.f();
        this.f8968e = viewScaleType;
        this.f = imageDownloader;
        this.g = yw0Var.d();
        this.h = yw0Var.k();
        BitmapFactory.Options options = new BitmapFactory.Options();
        this.i = options;
        a(yw0Var.a(), options);
    }

    public final void a(BitmapFactory.Options options, BitmapFactory.Options options2) {
        options2.inDensity = options.inDensity;
        options2.inDither = options.inDither;
        options2.inInputShareable = options.inInputShareable;
        options2.inJustDecodeBounds = options.inJustDecodeBounds;
        options2.inPreferredConfig = options.inPreferredConfig;
        options2.inPurgeable = options.inPurgeable;
        options2.inSampleSize = options.inSampleSize;
        options2.inScaled = options.inScaled;
        options2.inScreenDensity = options.inScreenDensity;
        options2.inTargetDensity = options.inTargetDensity;
        options2.inTempStorage = options.inTempStorage;
        int i = Build.VERSION.SDK_INT;
        if (i >= 10) {
            b(options, options2);
        }
        if (i >= 11) {
            c(options, options2);
        }
    }

    @TargetApi(10)
    public final void b(BitmapFactory.Options options, BitmapFactory.Options options2) {
        options2.inPreferQualityOverSpeed = options.inPreferQualityOverSpeed;
    }

    @TargetApi(11)
    public final void c(BitmapFactory.Options options, BitmapFactory.Options options2) {
        options2.inBitmap = options.inBitmap;
        options2.inMutable = options.inMutable;
    }

    public String d() {
        return this.f8967a;
    }

    public ImageScaleType e() {
        return this.d;
    }

    public String f() {
        return this.b;
    }

    public ix0 g() {
        return this.c;
    }

    public ViewScaleType h() {
        return this.f8968e;
    }

    public boolean i() {
        return this.h;
    }

    public ImageDownloader b() {
        return this.f;
    }

    public Object c() {
        return this.g;
    }

    public BitmapFactory.Options a() {
        return this.i;
    }
}
