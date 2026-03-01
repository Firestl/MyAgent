package supwisdom;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import com.bumptech.glide.load.model.AssetUriLoader;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import supwisdom.lh1;

/* JADX INFO: compiled from: AssetRequestHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class sg1 extends lh1 {
    public static final int b = 22;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AssetManager f9171a;

    public sg1(Context context) {
        this.f9171a = context.getAssets();
    }

    public static String c(jh1 jh1Var) {
        return jh1Var.d.toString().substring(b);
    }

    @Override // supwisdom.lh1
    public boolean a(jh1 jh1Var) {
        Uri uri = jh1Var.d;
        return "file".equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && AssetUriLoader.ASSET_PATH_SEGMENT.equals(uri.getPathSegments().get(0));
    }

    @Override // supwisdom.lh1
    public lh1.a a(jh1 jh1Var, int i) throws IOException {
        return new lh1.a(this.f9171a.open(c(jh1Var)), Picasso.LoadedFrom.DISK);
    }
}
