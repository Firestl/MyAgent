package supwisdom;

import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import supwisdom.mp0;

/* JADX INFO: compiled from: Schema.java */
/* JADX INFO: loaded from: classes.dex */
public interface lr0<T> {
    void a(T t);

    void a(T t, Writer writer) throws IOException;

    void a(T t, T t2);

    void a(T t, jr0 jr0Var, xp0 xp0Var) throws IOException;

    void a(T t, byte[] bArr, int i, int i2, mp0.b bVar) throws IOException;

    boolean b(T t);

    boolean b(T t, T t2);

    int c(T t);

    int d(T t);

    T newInstance();
}
