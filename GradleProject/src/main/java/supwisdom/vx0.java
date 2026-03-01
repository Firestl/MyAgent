package supwisdom;

import com.nostra13.dcloudimageloader.core.download.ImageDownloader;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class vx0 implements ImageDownloader {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageDownloader f9558a;

    public vx0(ImageDownloader imageDownloader) {
        this.f9558a = imageDownloader;
    }

    @Override // com.nostra13.dcloudimageloader.core.download.ImageDownloader
    public InputStream a(String str, Object obj) throws IOException {
        InputStream inputStreamA = this.f9558a.a(str, obj);
        int iOrdinal = ImageDownloader.Scheme.ofUri(str).ordinal();
        return (iOrdinal == 1 || iOrdinal == 2) ? new gx0(inputStreamA) : inputStreamA;
    }
}
