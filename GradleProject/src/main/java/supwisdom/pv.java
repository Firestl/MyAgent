package supwisdom;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes.dex */
public final class pv extends ContextWrapper {

    public final class b extends ContextWrapper {
        @Override // android.content.ContextWrapper, android.content.Context
        public Object getSystemService(String str) {
            return "window".equals(str) ? new c((WindowManager) getBaseContext().getSystemService(str)) : super.getSystemService(str);
        }

        public b(Context context) {
            super(context);
        }
    }

    public final class c implements WindowManager {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WindowManager f8837a;

        @Override // android.view.ViewManager
        public void addView(View view, ViewGroup.LayoutParams layoutParams) {
            try {
                this.f8837a.addView(view, layoutParams);
            } catch (WindowManager.BadTokenException e2) {
                Log.i("WindowManagerWrapper", e2.getMessage());
            } catch (Throwable th) {
                Log.e("WindowManagerWrapper", "[addView]", th);
            }
        }

        @Override // android.view.WindowManager
        public Display getDefaultDisplay() {
            return this.f8837a.getDefaultDisplay();
        }

        @Override // android.view.ViewManager
        public void removeView(View view) {
            this.f8837a.removeView(view);
        }

        @Override // android.view.WindowManager
        public void removeViewImmediate(View view) {
            this.f8837a.removeViewImmediate(view);
        }

        @Override // android.view.ViewManager
        public void updateViewLayout(View view, ViewGroup.LayoutParams layoutParams) {
            this.f8837a.updateViewLayout(view, layoutParams);
        }

        public c(pv pvVar, WindowManager windowManager) {
            this.f8837a = windowManager;
        }
    }

    public pv(Context context) {
        super(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        return new b(getBaseContext().getApplicationContext());
    }
}
