package supwisdom;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.newcapec.virtualcard.activity.MainActivity;
import com.newcapec.virtualcard.activity.PasswordActivity;
import supwisdom.z;

/* JADX INFO: loaded from: classes.dex */
public class d extends n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PasswordActivity f7275a;

    public class a implements z.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f7276a;

        public a(boolean z) {
            this.f7276a = z;
        }

        @Override // supwisdom.z.a
        public void a(z zVar) {
            if (this.f7276a) {
                ne.a(d.this.f7275a).a(new Intent("com.newcapec.virtualcard.pwd"));
            }
        }
    }

    public d(PasswordActivity passwordActivity) {
        this.f7275a = passwordActivity;
    }

    @Override // supwisdom.n
    public void a(String str, String str2) {
        this.f7275a.c.dismiss();
        this.f7275a.b.a();
        String strA = g.a(str, str2);
        boolean z = !TextUtils.isEmpty(strA) && strA.contains("修改密码");
        this.f7275a.a(strA, z ? "修改密码" : "确认", new a(z));
    }

    @Override // supwisdom.n
    public void c() {
        this.f7275a.c.dismiss();
        PasswordActivity passwordActivity = this.f7275a;
        if (passwordActivity.getIntent().getBooleanExtra("arg_return", false)) {
            passwordActivity.setResult(-1);
        } else {
            MainActivity.a((Context) passwordActivity, false);
        }
        passwordActivity.finish();
    }
}
