package supwisdom;

import android.content.Context;
import com.squareup.picasso.Picasso;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import supwisdom.lh1;

/* JADX INFO: compiled from: ContentStreamRequestHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class xg1 extends lh1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f9764a;

    public xg1(Context context) {
        this.f9764a = context;
    }

    @Override // supwisdom.lh1
    public boolean a(jh1 jh1Var) {
        return "content".equals(jh1Var.d.getScheme());
    }

    public InputStream c(jh1 jh1Var) throws FileNotFoundException {
        return this.f9764a.getContentResolver().openInputStream(jh1Var.d);
    }

    @Override // supwisdom.lh1
    public lh1.a a(jh1 jh1Var, int i) throws IOException {
        return new lh1.a(c(jh1Var), Picasso.LoadedFrom.DISK);
    }
}
