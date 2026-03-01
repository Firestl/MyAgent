package supwisdom;

import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: BasicListHeaderIterator.java */
/* JADX INFO: loaded from: classes2.dex */
public class so1 implements zn1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<xn1> f9197a;
    public int b;
    public int c;
    public String d;

    public so1(List<xn1> list, String str) {
        yo1.a(list, "Header list");
        this.f9197a = list;
        this.d = str;
        this.b = b(-1);
        this.c = -1;
    }

    public boolean a(int i) {
        if (this.d == null) {
            return true;
        }
        return this.d.equalsIgnoreCase(this.f9197a.get(i).getName());
    }

    public int b(int i) {
        if (i < -1) {
            return -1;
        }
        int size = this.f9197a.size() - 1;
        boolean zA = false;
        while (!zA && i < size) {
            i++;
            zA = a(i);
        }
        if (zA) {
            return i;
        }
        return -1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.b >= 0;
    }

    @Override // java.util.Iterator
    public final Object next() throws NoSuchElementException {
        return a();
    }

    @Override // java.util.Iterator
    public void remove() throws UnsupportedOperationException {
        zo1.a(this.c >= 0, "No header to remove");
        this.f9197a.remove(this.c);
        this.c = -1;
        this.b--;
    }

    public xn1 a() throws NoSuchElementException {
        int i = this.b;
        if (i >= 0) {
            this.c = i;
            this.b = b(i);
            return this.f9197a.get(i);
        }
        throw new NoSuchElementException("Iteration already finished.");
    }
}
