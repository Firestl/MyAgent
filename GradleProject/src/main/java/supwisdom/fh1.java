package supwisdom;

import android.graphics.Bitmap;
import android.net.NetworkInfo;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.io.InputStream;
import supwisdom.lh1;

/* JADX INFO: compiled from: NetworkRequestHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class fh1 extends lh1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Downloader f7607a;
    public final nh1 b;

    /* JADX INFO: compiled from: NetworkRequestHandler.java */
    public static class a extends IOException {
        public a(String str) {
            super(str);
        }
    }

    public fh1(Downloader downloader, nh1 nh1Var) {
        this.f7607a = downloader;
        this.b = nh1Var;
    }

    @Override // supwisdom.lh1
    public int a() {
        return 2;
    }

    @Override // supwisdom.lh1
    public boolean a(jh1 jh1Var) {
        String scheme = jh1Var.d.getScheme();
        return "http".equals(scheme) || "https".equals(scheme);
    }

    @Override // supwisdom.lh1
    public boolean b() {
        return true;
    }

    @Override // supwisdom.lh1
    public lh1.a a(jh1 jh1Var, int i) throws IOException {
        Downloader.a aVarA = this.f7607a.a(jh1Var.d, jh1Var.c);
        if (aVarA == null) {
            return null;
        }
        Picasso.LoadedFrom loadedFrom = aVarA.c ? Picasso.LoadedFrom.DISK : Picasso.LoadedFrom.NETWORK;
        Bitmap bitmapA = aVarA.a();
        if (bitmapA != null) {
            return new lh1.a(bitmapA, loadedFrom);
        }
        InputStream inputStreamC = aVarA.c();
        if (inputStreamC == null) {
            return null;
        }
        if (loadedFrom == Picasso.LoadedFrom.DISK && aVarA.b() == 0) {
            rh1.a(inputStreamC);
            throw new a("Received response with 0 content-length header.");
        }
        if (loadedFrom == Picasso.LoadedFrom.NETWORK && aVarA.b() > 0) {
            this.b.a(aVarA.b());
        }
        return new lh1.a(inputStreamC, loadedFrom);
    }

    @Override // supwisdom.lh1
    public boolean a(boolean z, NetworkInfo networkInfo) {
        return networkInfo == null || networkInfo.isConnected();
    }
}
