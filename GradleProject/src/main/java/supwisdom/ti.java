package supwisdom;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: BindingXJSFunctionRegister.java */
/* JADX INFO: loaded from: classes.dex */
public class ti {
    public static final ti b = new ti();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap<String, kj> f9294a = new LinkedHashMap<>(8);

    public static ti b() {
        return b;
    }

    public Map<String, kj> a() {
        return Collections.unmodifiableMap(this.f9294a);
    }
}
