package supwisdom;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class s81 extends Dialog {

    /* JADX INFO: compiled from: Proguard */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f9136a;
        public String b;
        public String c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public View.OnClickListener f9137e;
        public View.OnClickListener f;

        public Dialog a(Context context) {
            return new s81(context, this);
        }

        public a b(String str) {
            this.f9136a = str;
            return this;
        }

        public View.OnClickListener c() {
            return this.f;
        }

        public View.OnClickListener d() {
            return this.f9137e;
        }

        public String e() {
            return this.c;
        }

        public String f() {
            return this.f9136a;
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        public String b() {
            return this.d;
        }

        public a a(String str, View.OnClickListener onClickListener) {
            this.c = str;
            this.f9137e = onClickListener;
            return this;
        }

        public String a() {
            return this.b;
        }
    }

    public s81(Context context, a aVar) {
        super(context);
        getWindow().requestFeature(1);
        setCancelable(false);
        addContentView(new t81(context, this, aVar), new ViewGroup.LayoutParams(-1, -2));
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.color.transparent);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            }
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = wb1.a(context, 311.0f);
            layoutParams.gravity = 17;
            window.setAttributes(layoutParams);
        }
    }
}
