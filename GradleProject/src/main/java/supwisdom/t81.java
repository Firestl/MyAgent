package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import supwisdom.i71;
import supwisdom.s81;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class t81 extends LinearLayout {
    public static final int n = v71.a(8.0f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f9264a;
    public TextView b;
    public TextView c;
    public ScrollView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Button f9265e;
    public Button f;
    public String g;
    public String h;
    public View.OnClickListener i;
    public View.OnClickListener j;
    public String k;
    public String l;
    public Dialog m;

    /* JADX INFO: compiled from: Proguard */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (t81.this.m != null) {
                t81.this.m.dismiss();
            }
            if (t81.this.j != null) {
                t81.this.j.onClick(view);
            }
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (t81.this.m != null) {
                t81.this.m.dismiss();
            }
            if (t81.this.i != null) {
                t81.this.i.onClick(view);
            }
        }
    }

    public t81(Context context, Dialog dialog, s81.a aVar) {
        super(context);
        this.f9264a = context;
        this.m = dialog;
        this.k = aVar.f();
        this.l = aVar.a();
        this.g = aVar.e();
        this.h = aVar.b();
        this.i = aVar.d();
        this.j = aVar.c();
        d();
        c();
        b();
        a();
    }

    public final void d() {
        setOrientation(1);
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(new w71(-1, n));
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        super.onMeasure(i, i2);
        View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            int i3 = this.f9264a.getResources().getDisplayMetrics().heightPixels;
            int iA = v71.a(400.0f);
            if (i3 < v71.a(640.0f)) {
                iA = v71.a(320.0f);
            }
            size2 = Math.min(iA, i3);
            ScrollView scrollView = null;
            int measuredHeight = 0;
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                measureChild(childAt, i, i2);
                if ((childAt instanceof ScrollView) && childAt.getVisibility() == 0) {
                    scrollView = (ScrollView) childAt;
                } else {
                    measuredHeight += childAt.getMeasuredHeight();
                }
            }
            if (scrollView != null) {
                int measuredHeight2 = scrollView.getMeasuredHeight() + measuredHeight;
                if (measuredHeight2 > size2) {
                    ViewGroup.LayoutParams layoutParams = scrollView.getLayoutParams();
                    layoutParams.height = size2 - measuredHeight;
                    scrollView.setLayoutParams(layoutParams);
                } else {
                    size2 = measuredHeight2;
                }
            } else {
                size2 = measuredHeight;
            }
        }
        setMeasuredDimension(size, size2);
    }

    public final void a() {
        boolean z;
        View view = new View(this.f9264a);
        addView(view);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
        int i = i71.b.f7924e;
        view.setBackgroundColor(i);
        LinearLayout linearLayout = new LinearLayout(this.f9264a);
        addView(linearLayout);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setOrientation(0);
        if (TextUtils.isEmpty(this.h)) {
            z = false;
        } else {
            Button button = new Button(this.f9264a);
            this.f9265e = button;
            linearLayout.addView(button);
            this.f9265e.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
            this.f9265e.setGravity(17);
            this.f9265e.setAllCaps(false);
            this.f9265e.setTextColor(Color.parseColor("#666666"));
            this.f9265e.setTextSize(16.0f);
            this.f9265e.setPadding(0, v71.a(12.0f), 0, v71.a(12.0f));
            this.f9265e.setText(this.h);
            this.f9265e.setBackground(new y71(i71.b.b, 0, 0, 0, n));
            this.f9265e.setOnClickListener(new a());
            z = true;
        }
        if (TextUtils.isEmpty(this.g)) {
            return;
        }
        if (z) {
            View view2 = new View(this.f9264a);
            view2.setLayoutParams(new LinearLayout.LayoutParams(1, -1));
            view2.setBackgroundColor(i);
            linearLayout.addView(view2);
        }
        Button button2 = new Button(this.f9264a);
        this.f = button2;
        linearLayout.addView(button2);
        this.f.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        this.f.setGravity(17);
        this.f.setAllCaps(false);
        this.f.setTextColor(i71.b.f7923a);
        this.f.setTextSize(16.0f);
        this.f.setPadding(0, v71.a(12.0f), 0, v71.a(12.0f));
        if (z) {
            this.f.setBackground(new y71(i71.b.b, 0, 0, n, 0));
        } else {
            Button button3 = this.f;
            int i2 = i71.b.b;
            int i3 = n;
            button3.setBackground(new y71(i2, 0, 0, i3, i3));
        }
        this.f.setText(this.g);
        this.f.setOnClickListener(new b());
    }

    public final void b() {
        if (TextUtils.isEmpty(this.l)) {
            return;
        }
        ScrollView scrollView = new ScrollView(this.f9264a);
        this.d = scrollView;
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        this.d.setScrollBarSize(v71.a(8.0f));
        if (Build.VERSION.SDK_INT >= 30) {
            this.d.setVerticalScrollbarThumbDrawable(new w71(i71.b.t, v71.a(10.0f)));
        }
        this.d.setPadding(0, 0, v71.a(4.0f), 0);
        this.d.setBackgroundColor(-1);
        this.c = new TextView(this.f9264a);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(v71.a(24.0f), 0, v71.a(24.0f), v71.a(24.0f));
        this.c.setLayoutParams(layoutParams);
        this.c.setText(this.l);
        this.c.setGravity(8388611);
        this.c.setTextSize(2, 16.0f);
        this.c.setTextColor(i71.b.u);
        LinearLayout linearLayout = new LinearLayout(this.f9264a);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.addView(this.c);
        this.d.addView(linearLayout);
        addView(this.d);
    }

    public final void c() {
        if (TextUtils.isEmpty(this.k)) {
            return;
        }
        TextView textView = new TextView(this.f9264a);
        this.b = textView;
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.b.setPadding(v71.a(24.0f), v71.a(24.0f), v71.a(24.0f), v71.a(24.0f));
        this.b.setGravity(17);
        this.b.setTextColor(i71.b.c);
        this.b.setVerticalScrollBarEnabled(true);
        this.b.setTextSize(18.0f);
        this.b.setEllipsize(TextUtils.TruncateAt.END);
        this.b.setMaxLines(5);
        addView(this.b);
        this.b.setText(this.k);
    }
}
