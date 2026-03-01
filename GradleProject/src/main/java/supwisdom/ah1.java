package supwisdom;

import android.content.Context;
import android.media.ExifInterface;
import android.net.Uri;
import com.squareup.picasso.Picasso;
import io.dcloud.common.DHInterface.IFeature;
import java.io.IOException;
import supwisdom.lh1;

/* JADX INFO: compiled from: FileRequestHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class ah1 extends xg1 {
    public ah1(Context context) {
        super(context);
    }

    @Override // supwisdom.xg1, supwisdom.lh1
    public boolean a(jh1 jh1Var) {
        return "file".equals(jh1Var.d.getScheme());
    }

    @Override // supwisdom.xg1, supwisdom.lh1
    public lh1.a a(jh1 jh1Var, int i) throws IOException {
        return new lh1.a(null, c(jh1Var), Picasso.LoadedFrom.DISK, a(jh1Var.d));
    }

    public static int a(Uri uri) throws IOException {
        int attributeInt = new ExifInterface(uri.getPath()).getAttributeInt(IFeature.F_ORIENTATION, 1);
        if (attributeInt == 3) {
            return 180;
        }
        if (attributeInt != 6) {
            return attributeInt != 8 ? 0 : 270;
        }
        return 90;
    }
}
