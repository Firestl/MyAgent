package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: ParserCursor.java */
/* JADX INFO: loaded from: classes2.dex */
public class uo1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9428a;
    public final int b;
    public int c;

    public uo1(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Lower bound cannot be negative");
        }
        if (i > i2) {
            throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
        }
        this.f9428a = i;
        this.b = i2;
        this.c = i;
    }

    public void a(int i) {
        if (i < this.f9428a) {
            throw new IndexOutOfBoundsException("pos: " + i + " < lowerBound: " + this.f9428a);
        }
        if (i <= this.b) {
            this.c = i;
            return;
        }
        throw new IndexOutOfBoundsException("pos: " + i + " > upperBound: " + this.b);
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.b;
    }

    public String toString() {
        return Operators.ARRAY_START + Integer.toString(this.f9428a) + '>' + Integer.toString(this.c) + '>' + Integer.toString(this.b) + Operators.ARRAY_END;
    }

    public boolean a() {
        return this.c >= this.b;
    }
}
