package supwisdom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class cb1 {

    /* JADX INFO: compiled from: Proguard */
    public class a implements DialogInterface.OnClickListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            cb1.this.a();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final cb1 f7175a = new cb1();
    }

    public static cb1 b() {
        return b.f7175a;
    }

    public String a(Intent intent) {
        intent.setExtrasClassLoader(n91.b().getClassLoader());
        String stringExtra = intent.getStringExtra("com.android.browser.application_id");
        if (TextUtils.isEmpty(stringExtra) || !stringExtra.contains("sangfor_h5_")) {
            return "";
        }
        String strSubstring = stringExtra.substring(11);
        return TextUtils.isEmpty(strSubstring) ? "" : strSubstring;
    }

    public void a() {
    }

    public boolean b(Intent intent) {
        return (intent == null || TextUtils.isEmpty(a(intent))) ? false : true;
    }

    public void a(Activity activity) {
        if (activity == null) {
            t91.d("ShortcutIntentChecker", "onH5AppNotPublished do nothing, reason: current app is not in forground");
            return;
        }
        String str = jb1.c.L2;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false).setTitle(jb1.c.H2).setMessage(str).setPositiveButton(jb1.c.v, new a());
        builder.create().show();
    }
}
