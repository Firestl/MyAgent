package supwisdom;

import java.util.Comparator;

/* JADX INFO: compiled from: Spliterator.java */
/* JADX INFO: loaded from: classes3.dex */
public interface lq1<T> {

    /* JADX INFO: compiled from: Spliterator.java */
    public interface a extends d<Double, Object, a> {
    }

    /* JADX INFO: compiled from: Spliterator.java */
    public interface b extends d<Integer, Object, b> {
    }

    /* JADX INFO: compiled from: Spliterator.java */
    public interface c extends d<Long, Object, c> {
    }

    /* JADX INFO: compiled from: Spliterator.java */
    public interface d<T, T_CONS, T_SPLITR extends d<T, T_CONS, T_SPLITR>> extends lq1<T> {
    }

    Comparator<? super T> a();

    int b();
}
