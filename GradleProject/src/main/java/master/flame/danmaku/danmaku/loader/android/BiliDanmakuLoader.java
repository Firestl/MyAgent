package master.flame.danmaku.danmaku.loader.android;

import java.io.InputStream;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.parser.android.AndroidFileSource;

/* JADX INFO: loaded from: classes3.dex */
public class BiliDanmakuLoader implements ILoader {
    public static BiliDanmakuLoader _instance;
    public AndroidFileSource dataSource;

    public static BiliDanmakuLoader instance() {
        if (_instance == null) {
            _instance = new BiliDanmakuLoader();
        }
        return _instance;
    }

    @Override // master.flame.danmaku.danmaku.loader.ILoader
    public void load(String str) throws IllegalDataException {
        try {
            this.dataSource = new AndroidFileSource(str);
        } catch (Exception e2) {
            throw new IllegalDataException(e2);
        }
    }

    @Override // master.flame.danmaku.danmaku.loader.ILoader
    public AndroidFileSource getDataSource() {
        return this.dataSource;
    }

    @Override // master.flame.danmaku.danmaku.loader.ILoader
    public void load(InputStream inputStream) {
        this.dataSource = new AndroidFileSource(inputStream);
    }
}
