package com.sangfor.sdk.auth.trustterminal.activity;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sangfor.sdk.base.authdevice.AuthDeviceInfo;
import com.sangfor.sdk.base.authdevice.TrustedDeviceList;
import com.sangfor.sdk.utils.SFLogN;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.JSUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import supwisdom.c81;
import supwisdom.e81;
import supwisdom.f81;
import supwisdom.g81;
import supwisdom.i71;
import supwisdom.i81;
import supwisdom.l71;
import supwisdom.l81;
import supwisdom.p71;
import supwisdom.q71;
import supwisdom.t71;
import supwisdom.v71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class OverloadTerminalActivity extends BaseTrustTerminalActivity implements f81 {
    public g81 n;
    public ListView p;
    public p71 q;
    public q71 r;
    public h s;
    public int o = 0;
    public List<e81> t = new ArrayList();
    public int u = 0;
    public int v = 1;
    public l71 w = new f();
    public l71 x = new g();

    /* JADX INFO: compiled from: Proguard */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            OverloadTerminalActivity.this.n.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            OverloadTerminalActivity overloadTerminalActivity = OverloadTerminalActivity.this;
            overloadTerminalActivity.o = overloadTerminalActivity.n.getHeight();
            SFLogN.c("OverloadTerminalActivity", "get terminalCommonViewHeight  = " + OverloadTerminalActivity.this.o);
            OverloadTerminalActivity.this.n();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            OverloadTerminalActivity.this.n();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class c implements AdapterView.OnItemClickListener {
        public c() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            e81 e81Var = (e81) OverloadTerminalActivity.this.t.get(i);
            if (e81Var != null) {
                e81Var.a(!e81Var.e());
                OverloadTerminalActivity.this.s.notifyDataSetChanged();
                if (e81Var.e()) {
                    OverloadTerminalActivity.f(OverloadTerminalActivity.this);
                } else {
                    OverloadTerminalActivity.g(OverloadTerminalActivity.this);
                }
                OverloadTerminalActivity.this.q.a(OverloadTerminalActivity.this.u >= OverloadTerminalActivity.this.v);
            }
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SFLogN.c("OverloadTerminalActivity", "mOnClickListener remove RouteCallback");
            c81.a().a((f81) null);
            OverloadTerminalActivity.this.g();
            OverloadTerminalActivity.this.finish();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class e implements DialogInterface.OnClickListener {
        public e() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (c81.b(OverloadTerminalActivity.this.m)) {
                return;
            }
            OverloadTerminalActivity.this.finish();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class f implements l71 {
        public f() {
        }

        @Override // supwisdom.l71
        public void a() {
            String strL = OverloadTerminalActivity.this.l();
            if (TextUtils.isEmpty(strL)) {
                SFLogN.b("OverloadTerminalActivity", "unbind failed", "have nothing to choose");
                return;
            }
            OverloadTerminalActivity.this.e();
            HashMap map = new HashMap();
            map.put("unbindList", strL);
            c81.a().a(OverloadTerminalActivity.this.l, map);
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class g implements l71 {
        public g() {
        }

        @Override // supwisdom.l71
        public void a() {
            OverloadTerminalActivity.this.g();
            OverloadTerminalActivity.this.finish();
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public class h extends BaseAdapter {
        public h() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return OverloadTerminalActivity.this.t.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            if (i >= 0 && i <= OverloadTerminalActivity.this.t.size()) {
                return OverloadTerminalActivity.this.t.get(i);
            }
            SFLogN.c("OverloadTerminalActivity", "position< 0 or > size");
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            e81 e81Var = (e81) getItem(i);
            if (e81Var == null) {
                SFLogN.c("OverloadTerminalActivity", "item is null");
                return null;
            }
            i81 i81Var = view != null ? (i81) view : new i81(OverloadTerminalActivity.this);
            int i2 = i71.c.z;
            i81Var.setPadding(i2, 0, i2, 0);
            i81Var.f7929a.setImageBitmap(v71.a(i81Var.getContext(), e81Var.c()));
            i81Var.b.b(e81Var.a());
            i81Var.c.b(e81Var.d());
            i81Var.d.a(e81Var.e());
            return i81Var;
        }

        public /* synthetic */ h(OverloadTerminalActivity overloadTerminalActivity, a aVar) {
            this();
        }
    }

    public static /* synthetic */ int f(OverloadTerminalActivity overloadTerminalActivity) {
        int i = overloadTerminalActivity.u;
        overloadTerminalActivity.u = i + 1;
        return i;
    }

    public static /* synthetic */ int g(OverloadTerminalActivity overloadTerminalActivity) {
        int i = overloadTerminalActivity.u;
        overloadTerminalActivity.u = i - 1;
        return i;
    }

    public final String l() {
        ArrayList arrayList = new ArrayList();
        for (e81 e81Var : this.t) {
            if (e81Var.e()) {
                SFLogN.c("OverloadTerminalActivity", "item : " + e81Var.a() + "_" + e81Var.b() + " is selected");
                arrayList.add(e81Var);
            }
        }
        if (arrayList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(Operators.ARRAY_START_STR);
        for (int i = 0; i < arrayList.size(); i++) {
            e81 e81Var2 = (e81) arrayList.get(i);
            if (i == arrayList.size() - 1) {
                sb.append(JSUtil.QUOTE + e81Var2.b() + JSUtil.QUOTE);
                sb.append(Operators.ARRAY_END_STR);
            } else {
                sb.append(JSUtil.QUOTE + e81Var2.b() + JSUtil.QUOTE);
                sb.append(",");
            }
        }
        SFLogN.c("OverloadTerminalActivity", "get select array = " + sb.toString());
        return sb.toString();
    }

    public final void m() {
        AuthDeviceInfo authDeviceInfo = this.k;
        if (authDeviceInfo != null) {
            for (TrustedDeviceList trustedDeviceList : authDeviceInfo.getTrustedDeviceList()) {
                this.t.add(new e81(trustedDeviceList.getId(), trustedDeviceList.getOs(), trustedDeviceList.getDeviceName()));
            }
            SFLogN.c("OverloadTerminalActivity", "get TrustedDeviceList is = " + this.t.size());
            int bindNumLimit = this.k.getBindNumLimit();
            int size = this.t.size();
            if (size > bindNumLimit) {
                this.v = (size - bindNumLimit) + 1;
            }
            this.n.c(String.format(i71.b.h4, Integer.valueOf(bindNumLimit)));
            this.n.b(String.format(i71.b.i4, Integer.valueOf(this.v)));
            this.s.notifyDataSetChanged();
        }
    }

    public final void n() {
        int i = i71.c.w + (i71.c.o * 2);
        int i2 = this.o;
        int i3 = i71.c.x + i2 + i71.c.y + i71.c.c;
        int i4 = i71.c.h;
        int i5 = i3 + i4;
        int i6 = (i71.c.j * 2) + i4;
        int i7 = i71.c.g;
        int i8 = i6 + i7 + i7;
        SFLogN.a("OverloadTerminalActivity", "terminalCommonViewHeight = %d, BACK_IV_HEIGHT = %d, SCREEN_HEAD_HEIGHT = %d, SCREEN_TAIL_HEIGHT = %d", Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(i5), Integer.valueOf(i8));
        int iA = v71.a() - ((i + i5) + i8);
        int iA2 = v71.a(this.t.size() * 64) + ((this.t.size() - 1) * i71.c.d);
        SFLogN.c("OverloadTerminalActivity", "Screen height = " + v71.a() + ", the maxListHeight = " + iA + ", realListHeight = " + iA2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, Math.min(iA, iA2));
        int i9 = i71.c.i;
        layoutParams.setMargins(i9, 0, i9, 0);
        this.p.setLayoutParams(layoutParams);
    }

    @Override // com.sangfor.sdk.auth.BaseAuthActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LinearLayout linearLayout = this.h;
        if (linearLayout != null) {
            linearLayout.post(new b());
        }
    }

    @Override // com.sangfor.sdk.auth.trustterminal.activity.BaseTrustTerminalActivity, com.sangfor.sdk.auth.BaseAuthActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c81.a().a(this);
        m();
        this.n.getViewTreeObserver().addOnGlobalLayoutListener(new a());
    }

    @Override // com.sangfor.sdk.auth.trustterminal.activity.BaseTrustTerminalActivity, com.sangfor.sdk.auth.BaseAuthActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        c81.a().a((f81) null);
    }

    @Override // com.sangfor.sdk.auth.trustterminal.activity.BaseTrustTerminalActivity
    public void a(l81 l81Var) {
        String strA;
        String str;
        if (c81.b(this.m)) {
            strA = l81Var.b;
            str = i71.b.z;
        } else {
            strA = t71.a(this.m);
            str = i71.b.u1;
        }
        a(i71.b.k4, strA, str, new e());
    }

    @Override // com.sangfor.sdk.auth.trustterminal.activity.BaseTrustTerminalActivity
    public void h() {
        g81 g81Var = new g81(this);
        this.n = g81Var;
        g81Var.a("overload.png");
        this.h.addView(this.n);
        View view = new View(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, i71.c.c);
        int i = i71.c.i;
        layoutParams.setMargins(i, i71.c.x, i, i71.c.y);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(i71.b.h);
        this.h.addView(view);
        ListView listView = new ListView(this);
        this.p = listView;
        listView.setDivider(new ColorDrawable(-1));
        this.p.setDividerHeight(i71.c.d);
        this.h.addView(this.p);
        h hVar = new h(this, null);
        this.s = hVar;
        this.p.setAdapter((ListAdapter) hVar);
        this.p.setSelector(new ColorDrawable(-1));
        this.p.setBackgroundDrawable(new ColorDrawable(-1));
        this.p.setOnItemClickListener(new c());
        p71 p71Var = new p71(this);
        this.q = p71Var;
        p71Var.a(false);
        this.q.a(this.w);
        this.q.setText(i71.b.j4);
        this.h.addView(this.q);
        q71 q71Var = new q71(this);
        this.r = q71Var;
        q71Var.a(this.x);
        this.r.setText(i71.b.d4);
        this.h.addView(this.r);
    }

    @Override // com.sangfor.sdk.auth.trustterminal.activity.BaseTrustTerminalActivity
    public void k() {
        this.j = new d();
    }
}
