package supwisdom;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class r71 {

    /* JADX INFO: compiled from: Proguard */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f9020a;

        public a(View view) {
            this.f9020a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f9020a.setFocusable(true);
            this.f9020a.setFocusableInTouchMode(true);
            this.f9020a.requestFocus();
            ((InputMethodManager) this.f9020a.getContext().getApplicationContext().getSystemService("input_method")).showSoftInput(this.f9020a, 1);
        }
    }

    public static void a(View view) {
        view.setFocusable(false);
        view.setFocusableInTouchMode(false);
        view.clearFocus();
        ((InputMethodManager) view.getContext().getApplicationContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void b(View view) {
        view.postDelayed(new a(view), i71.f7920a);
    }
}
