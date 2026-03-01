package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: compiled from: UnmodifiableLazyStringList.java */
/* JADX INFO: loaded from: classes.dex */
public class ur0 extends AbstractList<String> implements kq0, RandomAccess {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final kq0 f9430a;

    /* JADX INFO: compiled from: UnmodifiableLazyStringList.java */
    public class a implements ListIterator<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ListIterator<String> f9431a;
        public final /* synthetic */ int b;

        public a(int i) {
            this.b = i;
            this.f9431a = ur0.this.f9430a.listIterator(this.b);
        }

        public void a(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void add(String str) {
            a(str);
            throw null;
        }

        public void b(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.f9431a.hasNext();
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.f9431a.hasPrevious();
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.f9431a.nextIndex();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.f9431a.previousIndex();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void set(String str) {
            b(str);
            throw null;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public String next() {
            return this.f9431a.next();
        }

        @Override // java.util.ListIterator
        public String previous() {
            return this.f9431a.previous();
        }
    }

    /* JADX INFO: compiled from: UnmodifiableLazyStringList.java */
    public class b implements Iterator<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Iterator<String> f9432a;

        public b() {
            this.f9432a = ur0.this.f9430a.iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f9432a.hasNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public String next() {
            return this.f9432a.next();
        }
    }

    public ur0(kq0 kq0Var) {
        this.f9430a = kq0Var;
    }

    @Override // supwisdom.kq0
    public Object b(int i) {
        return this.f9430a.b(i);
    }

    @Override // supwisdom.kq0
    public List<?> d() {
        return this.f9430a.d();
    }

    @Override // supwisdom.kq0
    public kq0 e() {
        return this;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<String> iterator() {
        return new b();
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<String> listIterator(int i) {
        return new a(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.f9430a.size();
    }

    @Override // supwisdom.kq0
    public void a(ByteString byteString) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public String get(int i) {
        return this.f9430a.get(i);
    }
}
