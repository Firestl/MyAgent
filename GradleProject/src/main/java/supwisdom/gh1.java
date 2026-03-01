package supwisdom;

import android.content.Context;
import android.net.Uri;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.NetworkPolicy;
import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import supwisdom.de1;
import supwisdom.se1;

/* JADX INFO: compiled from: OkHttpDownloader.java */
/* JADX INFO: loaded from: classes2.dex */
public class gh1 implements Downloader {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final re1 f7735a;

    public gh1(Context context) {
        this(rh1.b(context));
    }

    public static re1 a() {
        re1 re1Var = new re1();
        re1Var.a(15000L, TimeUnit.MILLISECONDS);
        re1Var.b(com.igexin.push.d.c.i, TimeUnit.MILLISECONDS);
        re1Var.c(com.igexin.push.d.c.i, TimeUnit.MILLISECONDS);
        return re1Var;
    }

    public gh1(File file) {
        this(file, rh1.a(file));
    }

    public gh1(File file, long j) {
        this(a());
        try {
            this.f7735a.a(new ce1(file, j));
        } catch (IOException unused) {
        }
    }

    public gh1(re1 re1Var) {
        this.f7735a = re1Var;
    }

    @Override // com.squareup.picasso.Downloader
    public Downloader.a a(Uri uri, int i) throws IOException {
        de1 de1VarA;
        if (i == 0) {
            de1VarA = null;
        } else if (NetworkPolicy.isOfflineOnly(i)) {
            de1VarA = de1.m;
        } else {
            de1.b bVar = new de1.b();
            if (!NetworkPolicy.shouldReadFromDiskCache(i)) {
                bVar.b();
            }
            if (!NetworkPolicy.shouldWriteToDiskCache(i)) {
                bVar.c();
            }
            de1VarA = bVar.a();
        }
        se1.b bVar2 = new se1.b();
        bVar2.b(uri.toString());
        if (de1VarA != null) {
            bVar2.a(de1VarA);
        }
        ue1 ue1VarA = this.f7735a.a(bVar2.a()).a();
        int iE = ue1VarA.e();
        if (iE < 300) {
            boolean z = ue1VarA.c() != null;
            ve1 ve1VarA = ue1VarA.a();
            return new Downloader.a(ve1VarA.a(), z, ve1VarA.b());
        }
        ue1VarA.a().close();
        throw new Downloader.ResponseException(iE + Operators.SPACE_STR + ue1VarA.h(), i, iE);
    }
}
