package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.util.Date;

/* JADX INFO: compiled from: MediaHeaderBox.java */
/* JADX INFO: loaded from: classes.dex */
public class ys extends ys0 {
    public Date g;
    public Date h;
    public long i;
    public long j;
    public String k;

    public ys() {
        super("mdhd");
    }

    @Override // supwisdom.ws0
    public long a() {
        return (f() == 1 ? 32L : 20L) + 2 + 2;
    }

    public void b(Date date) {
        this.h = date;
    }

    public Date g() {
        return this.g;
    }

    public long h() {
        return this.j;
    }

    public String i() {
        return this.k;
    }

    public Date j() {
        return this.h;
    }

    public long k() {
        return this.i;
    }

    public String toString() {
        return "MediaHeaderBox[creationTime=" + g() + ";modificationTime=" + j() + ";timescale=" + k() + ";duration=" + h() + ";language=" + i() + Operators.ARRAY_END_STR;
    }

    public void a(Date date) {
        this.g = date;
    }

    public void b(long j) {
        this.i = j;
    }

    public void a(long j) {
        this.j = j;
    }

    public void a(String str) {
        this.k = str;
    }

    @Override // supwisdom.ws0
    public void a(ByteBuffer byteBuffer) {
        c(byteBuffer);
        if (f() == 1) {
            ks.b(byteBuffer, at0.a(this.g));
            ks.b(byteBuffer, at0.a(this.h));
            ks.a(byteBuffer, this.i);
            ks.b(byteBuffer, this.j);
        } else {
            ks.a(byteBuffer, at0.a(this.g));
            ks.a(byteBuffer, at0.a(this.h));
            ks.a(byteBuffer, this.i);
            ks.a(byteBuffer, this.j);
        }
        ks.a(byteBuffer, this.k);
        ks.a(byteBuffer, 0);
    }
}
