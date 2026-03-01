package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: compiled from: FullContainerBox.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class zs0 extends ys0 implements qs {
    public List<os> g;

    static {
        Logger.getLogger(zs0.class.getName());
    }

    public zs0(String str) {
        super(str);
        this.g = new LinkedList();
    }

    @Override // supwisdom.ws0
    public long a() {
        Iterator<os> it = this.g.iterator();
        long size = 4;
        while (it.hasNext()) {
            size += it.next().getSize();
        }
        return size;
    }

    public final void d(ByteBuffer byteBuffer) {
        kt0 kt0Var = new kt0(byteBuffer);
        Iterator<os> it = this.g.iterator();
        while (it.hasNext()) {
            try {
                it.next().getBox(kt0Var);
            } catch (IOException e2) {
                throw new RuntimeException("Cannot happen.", e2);
            }
        }
    }

    public List<os> g() {
        return this.g;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(Operators.ARRAY_START_STR);
        for (int i = 0; i < this.g.size(); i++) {
            if (i > 0) {
                sb.append(";");
            }
            sb.append(this.g.get(i).toString());
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    public void a(os osVar) {
        osVar.setParent(this);
        this.g.add(osVar);
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        d(byteBuffer);
    }
}
