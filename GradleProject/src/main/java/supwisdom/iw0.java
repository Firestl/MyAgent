package supwisdom;

import com.taobao.weex.common.WXConfig;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public abstract class iw0 implements jw0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public File f7993a;
    public ow0 b;

    public iw0(File file, ow0 ow0Var) {
        if (file == null) {
            throw new IllegalArgumentException(String.format("\"%s\" argument must be not null", WXConfig.cacheDir));
        }
        if (ow0Var == null) {
            throw new IllegalArgumentException(String.format("\"%s\" argument must be not null", "fileNameGenerator"));
        }
        this.f7993a = file;
        this.b = ow0Var;
    }

    @Override // supwisdom.jw0
    public File a(String str) {
        return new File(this.f7993a, this.b.a(str));
    }
}
