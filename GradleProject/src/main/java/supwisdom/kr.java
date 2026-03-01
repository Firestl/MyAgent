package supwisdom;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class kr implements jr {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static jr f8192a;
    public static br b;

    public static jr a(Context context, String str) {
        if (context == null) {
            return null;
        }
        if (f8192a == null) {
            b = er.a(context, str);
            f8192a = new kr();
        }
        return f8192a;
    }

    @Override // supwisdom.jr
    public hr a(ir irVar) {
        return gr.a(b.a(gr.a(irVar)));
    }

    @Override // supwisdom.jr
    public boolean a(String str) {
        return b.a(str);
    }
}
