package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes3.dex */
public class gw1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f7779a;
    public int b;
    public long c = System.currentTimeMillis() + 86400000;

    public gw1(String str, int i) {
        this.f7779a = str;
        this.b = i;
    }

    public String toString() {
        return "ValueData{value='" + this.f7779a + Operators.SINGLE_QUOTE + ", code=" + this.b + ", expired=" + this.c + Operators.BLOCK_END;
    }
}
