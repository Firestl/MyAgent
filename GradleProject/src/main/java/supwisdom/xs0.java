package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: compiled from: AbstractContainerBox.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class xs0 extends ws0 implements qs {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<os> f9804e;

    static {
        Logger.getLogger(xs0.class.getName());
    }

    public xs0(String str) {
        super(str);
        this.f9804e = new LinkedList();
    }

    @Override // supwisdom.ws0
    public long a() {
        Iterator<os> it = this.f9804e.iterator();
        long size = 0;
        while (it.hasNext()) {
            size += it.next().getSize();
        }
        return size;
    }

    public final void c(ByteBuffer byteBuffer) {
        kt0 kt0Var = new kt0(byteBuffer);
        Iterator<os> it = this.f9804e.iterator();
        while (it.hasNext()) {
            try {
                it.next().getBox(kt0Var);
            } catch (IOException e2) {
                throw new RuntimeException("Cannot happen to me", e2);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(Operators.ARRAY_START_STR);
        for (int i = 0; i < this.f9804e.size(); i++) {
            if (i > 0) {
                sb.append(";");
            }
            sb.append(this.f9804e.get(i).toString());
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    public void a(os osVar) {
        osVar.setParent(this);
        this.f9804e.add(osVar);
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
    }
}
