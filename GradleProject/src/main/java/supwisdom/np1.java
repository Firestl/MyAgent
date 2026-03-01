package supwisdom;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import supwisdom.gs1;

/* JADX INFO: loaded from: classes2.dex */
public class np1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public vs1 f8550a;
    public gs1.a b;
    public Map<Method, op1> c = new HashMap();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public vs1 f8551a;
        public gs1.a b;
    }

    public np1(a aVar) {
        this.f8550a = aVar.f8551a;
        this.b = aVar.b;
    }

    public vs1 a() {
        return this.f8550a;
    }

    public gs1.a b() {
        return this.b;
    }
}
