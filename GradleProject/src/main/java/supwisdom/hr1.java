package supwisdom;

import android.os.Handler;
import android.os.Looper;
import java.util.Objects;
import okio.ByteString;

/* JADX INFO: loaded from: classes3.dex */
public class hr1 extends ht1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ gr1 f7868a;

    public hr1(gr1 gr1Var) {
        this.f7868a = gr1Var;
    }

    @Override // supwisdom.ht1
    public void a(gt1 gt1Var, int i, String str) {
        String.format("BaseSocketController：onClosed! 【%d-%s】", Integer.valueOf(i), str);
        gr1 gr1Var = this.f7868a;
        gr1Var.getClass();
        new Handler(Looper.getMainLooper()).post(new kr1(gr1Var));
    }

    @Override // supwisdom.ht1
    public void a(gt1 gt1Var, ByteString byteString) {
    }

    @Override // supwisdom.ht1
    public void b(gt1 gt1Var, int i, String str) {
    }

    @Override // supwisdom.ht1
    public void a(gt1 gt1Var, Throwable th, dt1 dt1Var) {
        Objects.toString(dt1Var);
        th.getMessage();
        gr1 gr1Var = this.f7868a;
        String str = dt1Var + "/ " + th.getMessage();
        gr1Var.getClass();
        new Handler(Looper.getMainLooper()).post(new lr1(gr1Var, str));
    }

    @Override // supwisdom.ht1
    public void a(gt1 gt1Var, String str) {
        gr1 gr1Var = this.f7868a;
        gr1Var.getClass();
        new Handler(Looper.getMainLooper()).post(new jr1(gr1Var, str));
    }

    @Override // supwisdom.ht1
    public void a(gt1 gt1Var, dt1 dt1Var) {
        Objects.toString(this.f7868a.c.g());
        gr1 gr1Var = this.f7868a;
        gr1Var.getClass();
        new Handler(Looper.getMainLooper()).post(new ir1(gr1Var));
    }
}
