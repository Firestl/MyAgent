package supwisdom;

import com.nostra13.dcloudimageloader.core.download.ImageDownloader;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class ux0 implements ImageDownloader {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageDownloader f9450a;

    public ux0(ImageDownloader imageDownloader) {
        this.f9450a = imageDownloader;
    }

    @Override // com.nostra13.dcloudimageloader.core.download.ImageDownloader
    public InputStream a(String str, Object obj) throws IOException {
        int iOrdinal = ImageDownloader.Scheme.ofUri(str).ordinal();
        if (iOrdinal == 1 || iOrdinal == 2) {
            throw new IllegalStateException();
        }
        return this.f9450a.a(str, obj);
    }
}
