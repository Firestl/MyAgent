package supwisdom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.util.UriUtil;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import supwisdom.lh1;

/* JADX INFO: compiled from: ResourceRequestHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class mh1 extends lh1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f8395a;

    public mh1(Context context) {
        this.f8395a = context;
    }

    @Override // supwisdom.lh1
    public boolean a(jh1 jh1Var) {
        if (jh1Var.f8063e != 0) {
            return true;
        }
        return UriUtil.QUALIFIED_RESOURCE_SCHEME.equals(jh1Var.d.getScheme());
    }

    @Override // supwisdom.lh1
    public lh1.a a(jh1 jh1Var, int i) throws IOException {
        Resources resourcesA = rh1.a(this.f8395a, jh1Var);
        return new lh1.a(a(resourcesA, rh1.a(resourcesA, jh1Var), jh1Var), Picasso.LoadedFrom.DISK);
    }

    public static Bitmap a(Resources resources, int i, jh1 jh1Var) {
        BitmapFactory.Options optionsB = lh1.b(jh1Var);
        if (lh1.a(optionsB)) {
            BitmapFactory.decodeResource(resources, i, optionsB);
            lh1.a(jh1Var.h, jh1Var.i, optionsB, jh1Var);
        }
        return BitmapFactory.decodeResource(resources, i, optionsB);
    }
}
