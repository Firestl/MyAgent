package supwisdom;

/* JADX INFO: compiled from: Observer.java */
/* JADX INFO: loaded from: classes3.dex */
public interface sw1<T> {
    void onCompleted();

    void onError(Throwable th);

    void onNext(T t);
}
