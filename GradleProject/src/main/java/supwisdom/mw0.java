package supwisdom;

import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class mw0 extends kw0 {
    public mw0(File file, int i) {
        this(file, ww0.b(), i);
    }

    @Override // supwisdom.kw0
    public int a(File file) {
        return (int) file.length();
    }

    public mw0(File file, ow0 ow0Var, int i) {
        super(file, ow0Var, i);
        if (i < 2097152) {
            by0.d("You set too small disc cache size (less than %1$d Mb)", 2);
        }
    }
}
