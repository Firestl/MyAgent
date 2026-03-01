package supwisdom;

import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;

/* JADX INFO: compiled from: Callback.java */
/* JADX INFO: loaded from: classes2.dex */
public interface rv0<T> extends sv0<T> {
    void a();

    void a(Progress progress);

    void a(Request<T, ? extends Request> request);

    void a(aw0<T> aw0Var);

    void b(aw0<T> aw0Var);

    void c(aw0<T> aw0Var);
}
