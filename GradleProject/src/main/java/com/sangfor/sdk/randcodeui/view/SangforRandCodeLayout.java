package com.sangfor.sdk.randcodeui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.sangfor.sdk.base.SFAuthType;
import com.sangfor.sdk.utils.SFLogN;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import supwisdom.m91;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"NewApi"})
public class SangforRandCodeLayout extends RelativeLayout implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public m91 f3931a;
    public int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3932e;
    public int f;
    public String g;
    public ArrayList<b> h;
    public a i;
    public boolean j;
    public boolean k;

    /* JADX INFO: compiled from: Proguard */
    public interface a {
        void a(String str, SFAuthType sFAuthType);
    }

    /* JADX INFO: compiled from: Proguard */
    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3933a;
        public int b;
        public int c;
        public m91 d;

        public b(int i, int i2, int i3) {
            this.f3933a = i;
            this.b = i2;
            this.c = i3;
        }

        public int c() {
            return this.c;
        }

        public m91 d() {
            return this.d;
        }

        public int a() {
            return this.f3933a;
        }

        public int b() {
            return this.b;
        }

        public void a(int i) {
            this.c = i;
            this.d.a(this);
        }

        public void a(m91 m91Var) {
            this.d = m91Var;
        }
    }

    public SangforRandCodeLayout(Context context) {
        super(context, null);
        this.j = false;
        this.k = false;
        b();
    }

    private String getRondCode() {
        for (int i = 0; i < this.h.size(); i++) {
            b bVar = this.h.get(i);
            if (i == 0) {
                this.g = Operators.ARRAY_START_STR + bVar.a() + "," + bVar.b() + Operators.ARRAY_END_STR;
            } else {
                this.g += ",[" + bVar.a() + "," + bVar.b() + Operators.ARRAY_END_STR;
            }
        }
        return "{\"coordinates\":[" + this.g + "],\"width\":" + this.f3932e + ",\"height\":" + this.f + Operators.BLOCK_END_STR;
    }

    public void a(int i, int i2) {
        SFLogN.c("SangforRandCodeLayout", "click x:" + i + "/y:" + i2);
        if (this.h == null) {
            SFLogN.b("SangforRandCodeLayout", "addData is fail", "points list is null");
            return;
        }
        b(i, i2);
        if (this.h.size() != 3 || this.j) {
            return;
        }
        a aVar = this.i;
        if (aVar != null) {
            aVar.a(getRondCode(), SFAuthType.AUTH_TYPE_RAND);
        }
        this.j = true;
    }

    public final void b(int i, int i2) {
        b bVar = new b(i, i2, this.h.size() + 1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        m91 m91Var = new m91(getContext(), bVar, this.k);
        m91Var.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        m91Var.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.d = m91Var.getMeasuredHeight();
        layoutParams.leftMargin = i - (m91Var.getMeasuredWidth() / 2);
        int i3 = this.d;
        int i4 = i2 - (i3 / 2);
        layoutParams.topMargin = i4;
        if (i4 <= 0) {
            layoutParams.topMargin = 0;
        } else if (i4 + i3 > getHeight()) {
            layoutParams.topMargin = getHeight() - this.d;
        }
        if (layoutParams.leftMargin <= 0) {
            layoutParams.leftMargin = 0;
        }
        if (layoutParams.rightMargin >= getWidth()) {
            layoutParams.rightMargin = getWidth();
        }
        bVar.a(m91Var);
        this.h.add(bVar);
        addView(m91Var, layoutParams);
    }

    public void c(int i, int i2) {
        SFLogN.c("SangforRandCodeLayout", "cricle num:" + this.h.size());
        if (this.h.size() < 3) {
            a(i, i2);
        }
    }

    public final boolean d(int i, int i2) {
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (new Rect((int) childAt.getX(), (int) childAt.getY(), childAt.getRight(), childAt.getBottom()).contains(i, i2)) {
                this.f3931a = (m91) childAt;
                return true;
            }
        }
        return false;
    }

    public final boolean e(int i, int i2) {
        int i3 = this.f;
        if (i3 <= 0 || this.f3932e <= 0) {
            return false;
        }
        return new Rect(0, 0, this.f3932e, (int) (i3 - (getResources().getDisplayMetrics().density * 32.0f))).contains(i, i2);
    }

    public boolean getCheckCodeing() {
        return this.j;
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        SFLogN.c("SangforRandCodeLayout", "onSizeChanged:" + i + "/" + i2);
        float f = (float) this.f3932e;
        float f2 = (float) this.f;
        this.f3932e = i;
        this.f = i2;
        ArrayList arrayList = new ArrayList();
        ArrayList<b> arrayList2 = this.h;
        if (arrayList2 == null || arrayList2.size() == 0) {
            return;
        }
        int size = this.h.size();
        int i5 = 0;
        while (i5 < size) {
            b bVar = this.h.get(i5);
            i5++;
            arrayList.add(new b((int) ((bVar.f3933a / f) * i), (int) ((bVar.b / f2) * i2), i5));
        }
        a();
        if (arrayList.size() > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                b bVar2 = (b) arrayList.get(i6);
                c(bVar2.f3933a, bVar2.b);
            }
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.j) {
                SFLogN.c("SangforRandCodeLayout", "onTouch is checking");
            } else {
                this.b = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                this.c = y;
                if (d(this.b, y) || !e(this.b, this.c)) {
                    c();
                } else {
                    c(this.b, this.c);
                }
            }
        }
        return true;
    }

    public void setCheckCodeing(boolean z) {
        this.j = z;
    }

    public void setInjectMode(boolean z) {
        this.k = z;
    }

    public void setOnFinishClickListener(a aVar) {
        this.i = aVar;
    }

    public final void c() {
        m91 m91Var = this.f3931a;
        if (m91Var != null) {
            int iA = m91Var.a();
            removeAllViews();
            this.f3931a = null;
            this.g = "";
            for (int i = 0; i < this.h.size(); i++) {
                if (this.h.get(i).c() == iA) {
                    this.h.remove(i);
                }
            }
            int i2 = 0;
            while (i2 < this.h.size()) {
                b bVar = this.h.get(i2);
                i2++;
                bVar.a(i2);
            }
            for (int i3 = 0; i3 < this.h.size(); i3++) {
                addView(this.h.get(i3).d());
            }
        }
    }

    public SangforRandCodeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = false;
        this.k = false;
        b();
    }

    public void a() {
        removeAllViews();
        ArrayList<b> arrayList = this.h;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.h.clear();
    }

    public final void b() {
        setOnTouchListener(this);
        this.h = new ArrayList<>();
    }
}
