package io.dcloud.feature.ui.nativeui;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import io.dcloud.common.DHInterface.ICallBack;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class b extends Toast implements ICallBack {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static ArrayList<b> f6659e = new ArrayList<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f6660a;
    public TextView b;
    public WindowManager.LayoutParams c;
    public WindowManager d;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a();
        }
    }

    public b(Activity activity, String str) {
        super(activity);
        this.f6660a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.d = activity.getWindowManager();
        TextView textView = new TextView(activity);
        this.b = textView;
        this.f6660a = textView;
        textView.setPadding(20, 20, 20, 20);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2, 8, -2);
        this.c = layoutParams;
        layoutParams.gravity = 17;
    }

    @Override // io.dcloud.common.DHInterface.ICallBack
    public Object onCallBack(int i, Object obj) {
        if (Build.VERSION.SDK_INT > 19) {
            return null;
        }
        a();
        return null;
    }

    @Override // android.widget.Toast
    public void setDuration(int i) {
        if (i == 1) {
            i = 3500;
        } else if (i == 0) {
            i = 2000;
        }
        super.setDuration(i);
    }

    @Override // android.widget.Toast
    public void setGravity(int i, int i2, int i3) {
        WindowManager.LayoutParams layoutParams = this.c;
        layoutParams.gravity = i;
        layoutParams.x = i2;
        layoutParams.y = i3;
        super.setGravity(i, i2, i3);
    }

    @Override // android.widget.Toast
    public void setText(CharSequence charSequence) {
        this.b.setText(charSequence);
        super.setText(charSequence);
    }

    @Override // android.widget.Toast
    public synchronized void show() {
        f6659e.add(this);
        this.d.addView(this.f6660a, this.c);
        this.f6660a.postDelayed(new a(), getDuration());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a() {
        View view = this.f6660a;
        if (view != null) {
            try {
                this.d.removeViewImmediate(view);
            } catch (Exception unused) {
            }
            f6659e.remove(this);
            this.f6660a = null;
        }
    }

    public void a(View view, TextView textView) {
        this.f6660a = view;
        this.b = textView;
    }

    public static synchronized void a(String str) {
        if (!f6659e.isEmpty()) {
            for (int size = f6659e.size() - 1; size >= 0; size--) {
                f6659e.get(size).a();
            }
            f6659e.clear();
        }
    }
}
