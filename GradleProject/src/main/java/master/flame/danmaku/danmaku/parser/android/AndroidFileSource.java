package master.flame.danmaku.danmaku.parser.android;

import android.net.Uri;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.util.IOUtils;

/* JADX INFO: loaded from: classes3.dex */
public class AndroidFileSource implements IDataSource<InputStream> {
    public InputStream inStream;

    public AndroidFileSource(String str) {
        fillStreamFromFile(new File(str));
    }

    public void fillStreamFromFile(File file) {
        try {
            this.inStream = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public void fillStreamFromHttpFile(Uri uri) {
        try {
            URL url = new URL(uri.getPath());
            url.openConnection();
            this.inStream = new BufferedInputStream(url.openStream());
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public void fillStreamFromUri(Uri uri) {
        String scheme = uri.getScheme();
        if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
            fillStreamFromHttpFile(uri);
        } else if ("file".equalsIgnoreCase(scheme)) {
            fillStreamFromFile(new File(uri.getPath()));
        }
    }

    @Override // master.flame.danmaku.danmaku.parser.IDataSource
    public void release() {
        IOUtils.closeQuietly(this.inStream);
        this.inStream = null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // master.flame.danmaku.danmaku.parser.IDataSource
    public InputStream data() {
        return this.inStream;
    }

    public AndroidFileSource(Uri uri) {
        fillStreamFromUri(uri);
    }

    public AndroidFileSource(File file) {
        fillStreamFromFile(file);
    }

    public AndroidFileSource(InputStream inputStream) {
        this.inStream = inputStream;
    }
}
