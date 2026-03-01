package a.a.a.a;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;
import supwisdom.b0;
import supwisdom.e0;
import supwisdom.z;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends AppCompatActivity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public z f1068a;

    public class a implements z.a {
        public a(g gVar) {
        }

        @Override // supwisdom.z.a
        public void a(z zVar) {
            zVar.dismiss();
        }
    }

    public z a(String str) {
        z zVarA = a(str, new a(this));
        if (!TextUtils.isEmpty(str) && str.contains("修改密码")) {
            zVarA.b.setText("修改密码");
        }
        return zVarA;
    }

    public z a(String str, z.a aVar) {
        return a(str, aVar, true);
    }

    public void a() {
        try {
            getWindow().addFlags(8192);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void b(String str) {
        b0.a(this, str);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e0.a(this, false, -1, true);
    }

    public z a(String str, z.a aVar, boolean z) {
        z zVar = this.f1068a;
        if (zVar != null && zVar.isShowing()) {
            this.f1068a.isShowing();
        }
        z zVar2 = new z(this);
        this.f1068a = zVar2;
        zVar2.f9943a.setText(str);
        if (z) {
            this.f1068a.setCancelable(false);
            this.f1068a.setCanceledOnTouchOutside(false);
        }
        this.f1068a.c = aVar;
        if (!isFinishing() && !isDestroyed()) {
            this.f1068a.show();
        }
        return this.f1068a;
    }

    public z a(String str, String str2, z.a aVar) {
        z zVarA = a(str, aVar, true);
        if (!TextUtils.isEmpty(str2)) {
            zVarA.b.setText(str2);
        }
        return zVarA;
    }
}
