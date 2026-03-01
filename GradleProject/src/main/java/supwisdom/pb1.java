package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class pb1 {
    public volatile boolean b;
    public volatile int c;
    public Map<String, Object> d = new HashMap(5);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile boolean f8789a = true;

    public int a() {
        return this.c;
    }

    public boolean b() {
        return this.f8789a;
    }

    public String toString() {
        return "     Config{mHookEnabled=" + this.f8789a + ", mEnabled=" + this.b + ", mMode=" + this.c + ", mConfigMap=" + this.d + Operators.BLOCK_END;
    }
}
