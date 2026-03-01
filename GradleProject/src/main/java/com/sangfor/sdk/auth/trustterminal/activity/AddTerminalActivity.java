package com.sangfor.sdk.auth.trustterminal.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.sangfor.sdk.base.SFErrorCode;
import com.sangfor.sdk.utils.SFLogN;
import java.util.HashMap;
import supwisdom.c81;
import supwisdom.f81;
import supwisdom.g81;
import supwisdom.i71;
import supwisdom.l71;
import supwisdom.l81;
import supwisdom.m71;
import supwisdom.o71;
import supwisdom.p71;
import supwisdom.r71;
import supwisdom.s81;
import supwisdom.t71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class AddTerminalActivity extends BaseTrustTerminalActivity implements f81 {
    public g81 n;
    public p71 o;
    public m71 p;
    public TextView q;
    public ScrollView r;
    public int s = 0;
    public o71 t = new e();
    public l71 u = new f();

    /* JADX INFO: compiled from: Proguard */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            AddTerminalActivity.this.r.fullScroll(130);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AddTerminalActivity.this.m();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            r71.b(AddTerminalActivity.this.p);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SFLogN.c("AddTerminalActivity", "mOnClickListener remove RouteCallback");
            AddTerminalActivity.this.g();
            c81.a().a((f81) null);
            AddTerminalActivity.this.finish();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class e extends o71 {
        public e() {
        }

        @Override // supwisdom.o71, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            super.afterTextChanged(editable);
            SFLogN.c("AddTerminalActivity", "SimpleTextWatcher afterTextChanged text count = " + editable.toString().length());
            AddTerminalActivity.this.s = editable.length();
            AddTerminalActivity.this.l();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class f implements l71 {
        public f() {
        }

        @Override // supwisdom.l71
        public void a() {
            String string = AddTerminalActivity.this.p.getText().toString();
            if (TextUtils.isEmpty(string) || string.length() < 5) {
                SFLogN.b("AddTerminalActivity", "reason commit failed", "reason length should >= 5");
                return;
            }
            AddTerminalActivity.this.e();
            HashMap map = new HashMap();
            map.put("reason", string);
            c81.a().a(AddTerminalActivity.this.l, map);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class g implements DialogInterface.OnClickListener {
        public g() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            SFLogN.c("AddTerminalActivity", "mSFSfBaseMessage:" + AddTerminalActivity.this.m);
            if (c81.b(AddTerminalActivity.this.m)) {
                return;
            }
            AddTerminalActivity.this.finish();
        }
    }

    @Override // com.sangfor.sdk.auth.trustterminal.activity.BaseTrustTerminalActivity
    public void h() {
        ScrollView scrollView = new ScrollView(this);
        this.r = scrollView;
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.h.addView(this.r);
        this.r.getViewTreeObserver().addOnGlobalLayoutListener(new a());
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        linearLayout.setOrientation(1);
        this.r.addView(linearLayout);
        g81 g81Var = new g81(this);
        this.n = g81Var;
        g81Var.a("add_terminal.png");
        this.n.c(i71.b.T3);
        this.n.b(i71.b.U3);
        this.n.a(12.0f);
        this.n.b(8388611);
        this.n.a(new b());
        linearLayout.addView(this.n);
        String str = i71.b.V3;
        int i = i71.c.f;
        this.p = new m71(this, str, i, false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, i71.c.t);
        int i2 = i71.c.i;
        layoutParams.setMargins(i2, i, i2, 0);
        this.p.setLayoutParams(layoutParams);
        this.p.setHintTextColor(i71.b.r);
        this.p.setGravity(48);
        m71 m71Var = this.p;
        int i3 = i71.c.v;
        int i4 = i71.c.u;
        m71Var.setPadding(i3, i4, i3, i4);
        this.p.setSingleLine(false);
        this.p.setOnClickListener(new c());
        this.p.addTextChangedListener(this.t);
        this.p.setFilters(new InputFilter[]{new InputFilter.LengthFilter(512)});
        linearLayout.addView(this.p);
        this.q = new TextView(this);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(0, i71.c.f7925a, i2, 0);
        this.q.setLayoutParams(layoutParams2);
        this.q.setGravity(5);
        this.q.setTextSize(12.0f);
        this.q.setTextColor(i71.b.q);
        linearLayout.addView(this.q);
        p71 p71Var = new p71(this);
        this.o = p71Var;
        p71Var.setText(i71.b.W3);
        this.o.a(this.u);
        linearLayout.addView(this.o);
        l();
        r71.a(this.p);
    }

    @Override // com.sangfor.sdk.auth.trustterminal.activity.BaseTrustTerminalActivity
    public void k() {
        this.j = new d();
    }

    public final void l() {
        this.q.setText(this.s + "/512");
        this.o.a(this.s >= 5);
    }

    public final void m() {
        s81.a aVar = new s81.a();
        aVar.b(i71.b.z4);
        aVar.a(this.k.getTips());
        aVar.a(i71.b.z, null);
        aVar.a(this).show();
    }

    @Override // com.sangfor.sdk.auth.trustterminal.activity.BaseTrustTerminalActivity, com.sangfor.sdk.auth.BaseAuthActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        getWindow().setSoftInputMode(19);
        super.onCreate(bundle);
        this.n.a(this.k.getTips(), 6);
        c81.a().a(this);
    }

    @Override // com.sangfor.sdk.auth.trustterminal.activity.BaseTrustTerminalActivity, com.sangfor.sdk.auth.BaseAuthActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        SFLogN.c("AddTerminalActivity", "onDestroy remove RouteCallback");
        c81.a().a((f81) null);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        r71.a(this.p);
    }

    @Override // com.sangfor.sdk.auth.trustterminal.activity.BaseTrustTerminalActivity
    public void a(l81 l81Var) {
        String strA;
        String str;
        if (l81Var.f8262a == SFErrorCode.SF_ERROR_BIND_DEVICE_APPLIED.value()) {
            SFLogN.c("AddTerminalActivity", "commit about apply authdevice  is succesful");
            return;
        }
        if (!c81.b(this.m)) {
            strA = t71.a(this.m);
            str = i71.b.u1;
        } else {
            strA = l81Var.b;
            str = i71.b.z;
        }
        a(i71.b.X3, strA, str, new g());
    }
}
