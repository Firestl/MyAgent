package com.sangfor.sdk.auth.trustterminal.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.sangfor.sdk.base.SFAuthType;
import com.sangfor.sdk.base.authdevice.SFApplyStatus;
import com.sangfor.sdk.utils.SFLogN;
import com.taobao.weex.el.parse.Operators;
import supwisdom.g81;
import supwisdom.h81;
import supwisdom.i71;
import supwisdom.l71;
import supwisdom.p71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class TerminalInfoActivity extends BaseTrustTerminalActivity {
    public g81 o;
    public h81 p;
    public View q;
    public p71 r;
    public TextView s;
    public ScrollView t;
    public c n = c.AddSuccess;
    public l71 u = new a();

    /* JADX INFO: compiled from: Proguard */
    public class a implements l71 {
        public a() {
        }

        @Override // supwisdom.l71
        public void a() {
            SFLogN.c("TerminalInfoActivity", "infoState = " + TerminalInfoActivity.this.n);
            int i = b.b[TerminalInfoActivity.this.n.ordinal()];
            if (i == 2 || i == 3) {
                TerminalInfoActivity terminalInfoActivity = TerminalInfoActivity.this;
                BaseTrustTerminalActivity.Sangfor_a(terminalInfoActivity, AddTerminalActivity.class, SFAuthType.AUTH_TYPE_APPLY_BIND_AUTH_DEVICE, terminalInfoActivity.k);
            } else {
                TerminalInfoActivity.this.g();
            }
            TerminalInfoActivity.this.finish();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3914a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[c.values().length];
            b = iArr;
            try {
                iArr[c.AddSuccess.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[c.Reject.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[c.UnResolve.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[SFApplyStatus.values().length];
            f3914a = iArr2;
            try {
                iArr2[SFApplyStatus.Approvaling.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3914a[SFApplyStatus.ApprovalReject.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public enum c {
        AddSuccess,
        UnResolve,
        Reject
    }

    @Override // com.sangfor.sdk.auth.trustterminal.activity.BaseTrustTerminalActivity
    public void h() {
        ScrollView scrollView = new ScrollView(this);
        this.t = scrollView;
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.h.addView(this.t);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        linearLayout.setOrientation(1);
        this.t.addView(linearLayout);
        g81 g81Var = new g81(this);
        this.o = g81Var;
        g81Var.c(i71.b.Y3);
        linearLayout.addView(this.o);
        this.q = new View(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, i71.c.c);
        int i = i71.c.i;
        layoutParams.setMargins(i, i71.c.g, i, i71.c.s);
        this.q.setLayoutParams(layoutParams);
        this.q.setBackgroundColor(i71.b.h);
        linearLayout.addView(this.q);
        this.s = new TextView(this);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.setMargins(i, 0, 0, 0);
        this.s.setLayoutParams(layoutParams2);
        this.s.setTextColor(-16777216);
        this.s.setTextSize(14.0f);
        this.s.setText(i71.b.f4);
        linearLayout.addView(this.s);
        h81 h81Var = new h81(this);
        this.p = h81Var;
        linearLayout.addView(h81Var);
        p71 p71Var = new p71(this);
        this.r = p71Var;
        p71Var.a(this.u);
        linearLayout.addView(this.r);
    }

    @Override // com.sangfor.sdk.auth.trustterminal.activity.BaseTrustTerminalActivity, com.sangfor.sdk.auth.BaseAuthActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String time = this.k.getLastApplyInfo().getTime();
        String[] strArrSplit = time.split(Operators.SPACE_STR);
        if (strArrSplit.length >= 2) {
            a(strArrSplit[0]);
        } else {
            a(time);
        }
    }

    public final void a(String str) {
        SFLogN.c("TerminalInfoActivity", "get date = " + str);
        int i = b.f3914a[SFApplyStatus.valueOf(this.k.getLastApplyInfo().getStatus()).ordinal()];
        if (i == 1) {
            this.n = c.UnResolve;
        } else if (i != 2) {
            this.n = c.AddSuccess;
        } else {
            this.n = c.Reject;
        }
        int i2 = b.b[this.n.ordinal()];
        if (i2 == 1) {
            this.o.a("add_success.png");
            this.o.b(i71.b.Z3);
            this.p.b(this.k.getLastApplyInfo().getUserName());
            this.p.a(this.k.getLastApplyInfo().getDeviceName());
            this.r.setText(i71.b.d4);
            this.q.setVisibility(8);
            this.s.setVisibility(8);
            return;
        }
        if (i2 == 2 || i2 == 3) {
            if (this.n == c.Reject) {
                this.o.a("reject.png");
                this.o.b(String.format(i71.b.g4, str));
            } else {
                this.o.a("add_terminal.png");
                this.o.b(String.format(i71.b.a4, str));
            }
            this.r.setText(i71.b.e4);
            this.p.b(this.k.getLastApplyInfo().getUserName());
            this.p.a(this.k.getLastApplyInfo().getDeviceName());
            this.q.setVisibility(0);
            this.s.setVisibility(0);
        }
    }
}
