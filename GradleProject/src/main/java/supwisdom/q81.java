package supwisdom;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import supwisdom.i71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class q81 extends Dialog {
    public static final int b = v71.a(8.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f8890a;

    /* JADX INFO: compiled from: Proguard */
    public class a implements Animation.AnimationListener {
        public a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            q81.super.dismiss();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f8892a;
        public String b;
        public String c;
        public String d;
        public Boolean g;
        public int l;
        public r81 o;
        public DialogInterface.OnClickListener p;
        public DialogInterface.OnClickListener q;
        public q81 r;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8893e = -1;
        public int f = -1;
        public boolean h = false;
        public boolean i = false;
        public boolean j = false;
        public String k = "";
        public int m = -1;
        public int n = -1;

        /* JADX INFO: compiled from: Proguard */
        public class a implements View.OnClickListener {
            public a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                b.this.p.onClick(b.this.r, -1);
            }
        }

        /* JADX INFO: renamed from: supwisdom.q81$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Proguard */
        public class ViewOnClickListenerC0228b implements View.OnClickListener {
            public ViewOnClickListenerC0228b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                b.this.q.onClick(b.this.r, -2);
            }
        }

        public b(Context context) {
            this.f8892a = context;
        }

        public void d(String str) {
            this.o.f9022a.setText(str);
        }

        public b a(String str) {
            this.b = str;
            return this;
        }

        public q81 b() {
            return this.r;
        }

        public b c(String str) {
            Button button;
            r81 r81Var = this.o;
            if (r81Var != null && (button = r81Var.d) != null) {
                button.setText(str);
            }
            return this;
        }

        public b a(String str, DialogInterface.OnClickListener onClickListener) {
            this.c = str;
            this.p = onClickListener;
            return this;
        }

        public void b(String str) {
            this.o.b.setText(str);
        }

        public void b(boolean z) {
            this.o.b.setVisibility(z ? 0 : 8);
        }

        public b a(boolean z) {
            this.g = Boolean.valueOf(z);
            return this;
        }

        public b a(boolean z, String str) {
            this.j = z;
            this.k = str;
            return this;
        }

        public q81 a() {
            this.o = new r81(this.f8892a);
            q81 q81Var = new q81(this.f8892a, this.o);
            this.r = q81Var;
            q81Var.setCanceledOnTouchOutside(false);
            int i = Build.VERSION.SDK_INT;
            if (i >= 16) {
                this.o.setBackground(new w71(-1, q81.b));
            }
            this.r.requestWindowFeature(1);
            this.r.addContentView(this.o, new ViewGroup.LayoutParams(-1, -2));
            if (!this.i) {
                this.o.f.setVisibility(8);
            }
            if (!this.j) {
                this.o.b.setVisibility(8);
            } else {
                this.o.b.setText(this.k);
            }
            if (TextUtils.isEmpty(this.c)) {
                this.o.d.setVisibility(8);
            } else {
                this.o.d.setVisibility(0);
                this.o.d.setText(this.c);
                int i2 = this.f8893e;
                if (i2 != -1) {
                    this.o.d.setTextColor(i2);
                }
                if (this.p != null) {
                    this.o.d.setOnClickListener(new a());
                }
            }
            if (TextUtils.isEmpty(this.d)) {
                this.o.c.setVisibility(8);
            } else {
                this.o.c.setVisibility(0);
                this.o.c.setText(this.d);
                int i3 = this.f;
                if (i3 != -1) {
                    this.o.c.setTextColor(i3);
                }
                if (this.q != null) {
                    this.o.c.setOnClickListener(new ViewOnClickListenerC0228b());
                }
            }
            boolean z = this.o.d.getVisibility() == 0;
            boolean z2 = this.o.c.getVisibility() == 0;
            if (i >= 16) {
                if (z && z2) {
                    Button button = this.o.d;
                    int i4 = i71.b.b;
                    button.setBackground(new y71(i4, 0, 0, q81.b, 0));
                    this.o.c.setBackground(new y71(i4, 0, 0, 0, q81.b));
                } else {
                    this.o.f9023e.setVisibility(8);
                    if (z) {
                        this.o.d.setBackground(new y71(i71.b.b, 0, 0, q81.b, q81.b));
                    } else if (z2) {
                        this.o.c.setBackground(new y71(i71.b.b, 0, 0, q81.b, q81.b));
                    }
                }
            }
            if (!TextUtils.isEmpty(this.b)) {
                this.o.f9022a.setText(this.b);
                int i5 = this.m;
                if (i5 > 0) {
                    this.o.f9022a.setTextSize(2, i5);
                }
                int i6 = this.n;
                if (i6 > 0) {
                    this.o.f9022a.setGravity(i6);
                }
                if (this.h) {
                    this.o.f9022a.setMovementMethod(ScrollingMovementMethod.getInstance());
                    this.o.f9022a.setMaxLines(this.l);
                    this.o.f9022a.setScrollbarFadingEnabled(false);
                }
            }
            Boolean bool = this.g;
            if (bool != null) {
                this.r.setCancelable(bool.booleanValue());
            }
            a(this.r);
            this.r.setContentView(this.o);
            return this.r;
        }

        public final void a(Dialog dialog) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setBackgroundDrawableResource(R.color.transparent);
                window.setDimAmount(0.2f);
                WindowManager.LayoutParams attributes = window.getAttributes();
                if (v71.c(this.f8892a)) {
                    attributes.width = (int) (Math.min(v71.b(), v71.a()) * 0.5f);
                } else {
                    attributes.width = (int) (Math.min(v71.b(), v71.a()) * 0.8f);
                }
                attributes.gravity = 17;
                window.setAttributes(attributes);
            }
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static class c extends AnimationSet {
        public c() {
            super(false);
            a();
        }

        public final void a() {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(300L);
            addAnimation(alphaAnimation);
        }
    }

    public q81(Context context, View view) {
        super(context);
        this.f8890a = view;
        a(context);
    }

    public final void a(Context context) {
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        c cVar = new c();
        this.f8890a.startAnimation(cVar);
        cVar.setAnimationListener(new a());
    }
}
