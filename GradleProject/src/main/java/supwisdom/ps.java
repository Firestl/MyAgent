package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: ChunkOffsetBox.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ps extends ys0 {
    public ps(String str) {
        super(str);
    }

    public abstract long[] g();

    public String toString() {
        return getClass().getSimpleName() + "[entryCount=" + g().length + Operators.ARRAY_END_STR;
    }
}
