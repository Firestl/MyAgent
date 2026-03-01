package io.dcloud.feature.ui.nativeui;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.js.map.amap.util.AMapUtil;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.pqc.crypto.qtesla.HashUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class a extends AlertDialog implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f6655a;
    public b b;
    public View c;
    public ViewGroup d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f6656e;
    public List<c> f;
    public String g;
    public String h;
    public int i;
    public boolean j;
    public boolean k;
    public boolean l;
    public float m;
    public int n;
    public boolean o;
    public boolean p;

    /* JADX INFO: renamed from: io.dcloud.feature.ui.nativeui.a$a, reason: collision with other inner class name */
    public class DialogInterfaceOnCancelListenerC0179a implements DialogInterface.OnCancelListener {
        public DialogInterfaceOnCancelListenerC0179a() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            a.this.a(-1);
            b bVar = a.this.b;
            if (bVar != null) {
                bVar.onItemClick(-1);
            }
            a.this.l = false;
        }
    }

    public interface b {
        void initCancelText(TextView textView);

        void initTextItem(int i, TextView textView, String str);

        boolean onDismiss(int i);

        void onItemClick(int i);
    }

    public class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f6658a;
        public String b;
        public String c;

        public c(a aVar, String str, String str2, String str3) {
            if (str2 == null) {
                this.b = "normal";
            } else {
                this.b = str2;
            }
            this.f6658a = str;
            this.c = str3;
        }
    }

    public a(Context context) {
        super(context);
        this.g = "";
        this.h = "";
        this.i = -16777216;
        this.k = true;
        this.m = 16.0f;
        this.n = 0;
        this.o = true;
        this.p = false;
        a(context);
    }

    private Animation b() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300L);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }

    private void d() {
        int intrinsicHeight = h().getIntrinsicHeight();
        boolean z = !TextUtils.isEmpty(this.g);
        boolean z2 = !TextUtils.isEmpty(this.h);
        String str = this.h;
        if (str != null && TextUtils.isEmpty(str)) {
            this.h = getContext().getResources().getString(R.string.cancel);
            z2 = true;
        }
        if (z2) {
            TextView textView = new TextView(this.f6655a);
            textView.setGravity(17);
            textView.getPaint().setFakeBoldText(true);
            textView.setTextSize(2, this.m);
            textView.setId(100);
            if (this.o) {
                textView.setBackgroundDrawable(this.f6655a.getResources().getDrawable(NativeUIR.SLT_AS_IOS7_CANCEL_BT));
            } else {
                textView.setBackgroundDrawable(this.f6655a.getResources().getDrawable(NativeUIR.SLT_AS_IOS7_OTHER_BT_MIDDLE));
            }
            textView.setText(this.h);
            textView.setTextColor(this.i);
            textView.setOnClickListener(this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(12);
            layoutParams.topMargin = b(this.n);
            this.d.addView(textView, layoutParams);
            b bVar = this.b;
            if (bVar != null) {
                bVar.initCancelText(textView);
            }
        }
        LinearLayout linearLayout = new LinearLayout(this.f6655a);
        ScrollView scrollView = new ScrollView(this.f6655a);
        linearLayout.setOrientation(1);
        scrollView.setId(300);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
        if (!z) {
            intrinsicHeight = 0;
        }
        linearLayout.setPadding(0, intrinsicHeight, 0, 0);
        scrollView.addView(linearLayout, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        if (z2) {
            layoutParams3.addRule(2, 100);
        } else {
            layoutParams3.addRule(12);
        }
        this.d.addView(scrollView, layoutParams3);
        if (z) {
            TextView textView2 = new TextView(this.f6655a);
            textView2.setGravity(17);
            textView2.setId(200);
            textView2.setOnClickListener(this);
            textView2.setBackgroundDrawable(h());
            textView2.setText(this.g);
            textView2.setTextColor(Color.parseColor("#8C8C8C"));
            textView2.setTextSize(2, this.m);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams4.topMargin = 0;
            layoutParams4.addRule(6, 300);
            this.d.addView(textView2, layoutParams4);
        }
        List<c> list = this.f;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < this.f.size(); i++) {
                TextView textView3 = new TextView(this.f6655a);
                textView3.setGravity(17);
                textView3.setId(i + 100 + 1);
                textView3.setOnClickListener(this);
                textView3.setBackgroundDrawable(a(this.f.size(), i, z));
                textView3.setText(this.f.get(i).f6658a);
                if (this.f.get(i).b.equals("destructive")) {
                    textView3.setTextColor(Color.parseColor("#E8484A"));
                } else {
                    int iStringToColor = PdrUtil.stringToColor(this.f.get(i).c);
                    if (-1 != iStringToColor) {
                        textView3.setTextColor(iStringToColor);
                    } else {
                        textView3.setTextColor(Color.parseColor(AMapUtil.HtmlBlack));
                    }
                }
                textView3.setTextSize(2, this.m);
                if (i > 0) {
                    LinearLayout.LayoutParams layoutParamsC = c();
                    layoutParamsC.topMargin = 0;
                    linearLayout.addView(textView3, layoutParamsC);
                } else {
                    linearLayout.addView(textView3);
                }
                b bVar2 = this.b;
                if (bVar2 != null) {
                    bVar2.initTextItem(i, textView3, this.f.get(i).f6658a);
                }
            }
        }
        int iB = b(this.n);
        this.d.setBackgroundDrawable(new ColorDrawable(0));
        if (this.p) {
            this.d.setPadding(iB, iB, iB, iB);
        } else {
            this.d.setPadding(0, iB, 0, iB);
        }
    }

    private Animation e() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setDuration(300L);
        return translateAnimation;
    }

    private Animation f() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

    private View g() {
        FrameLayout frameLayout = new FrameLayout(this.f6655a);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 80;
        frameLayout.setLayoutParams(layoutParams);
        View view = new View(this.f6655a);
        this.f6656e = view;
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        if (this.p) {
            this.f6656e.setBackgroundColor(Color.argb(HashUtils.SECURE_HASH_ALGORITHM_KECCAK_256_RATE, 0, 0, 0));
        }
        this.f6656e.setId(10);
        this.f6656e.setOnClickListener(this);
        this.d = new RelativeLayout(this.f6655a);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams2.gravity = 80;
        this.d.setLayoutParams(layoutParams2);
        frameLayout.addView(this.f6656e);
        frameLayout.addView(this.d);
        return frameLayout;
    }

    private Drawable h() {
        int i = NativeUIR.SLT_AS_IOS7_TITLE;
        if (!this.o) {
            i = NativeUIR.SLT_AS_IOS7_OTHER_BT_MIDDLE;
        }
        return this.f6655a.getResources().getDrawable(i);
    }

    public LinearLayout.LayoutParams c() {
        return new LinearLayout.LayoutParams(-1, -2);
    }

    public void i() {
        View currentFocus;
        InputMethodManager inputMethodManager = (InputMethodManager) this.f6655a.getSystemService("input_method");
        if (inputMethodManager.isActive() && (currentFocus = ((Activity) this.f6655a).getCurrentFocus()) != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
        this.c = g();
        this.f6656e.startAnimation(a());
        this.d.startAnimation(e());
    }

    public void j() {
        if (this.k) {
            d();
            show();
            getWindow().setContentView(this.c);
            this.k = false;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if ((view.getId() != 10 || this.j) && view.getId() != 200) {
            if (view.getId() == 10) {
                a(-1);
                b bVar = this.b;
                if (bVar != null) {
                    bVar.onItemClick(-1);
                    return;
                }
                return;
            }
            int id = view.getId() - 100;
            a(id);
            b bVar2 = this.b;
            if (bVar2 != null) {
                bVar2.onItemClick(id);
            }
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        attributes.height = -2;
        View decorView = getWindow().getDecorView();
        int i = this.n;
        decorView.setPadding(i, 0, i, 0);
        getWindow().setAttributes(attributes);
    }

    private boolean c(int i) {
        b bVar = this.b;
        if (bVar == null) {
            return false;
        }
        boolean zOnDismiss = bVar.onDismiss(i);
        if (zOnDismiss) {
            return zOnDismiss;
        }
        this.d.startAnimation(f());
        this.f6656e.startAnimation(b());
        return zOnDismiss;
    }

    public void a(Context context) {
        this.f6655a = context;
        this.n = 10;
        i();
        getWindow().setGravity(80);
        ColorDrawable colorDrawable = new ColorDrawable(0);
        colorDrawable.setAlpha(0);
        getWindow().setBackgroundDrawable(colorDrawable);
        setOnCancelListener(new DialogInterfaceOnCancelListenerC0179a());
    }

    public a e(int i) {
        this.n = b(i);
        return this;
    }

    private int b(int i) {
        return (int) TypedValue.applyDimension(1, i, this.f6655a.getResources().getDisplayMetrics());
    }

    public a b(String str) {
        this.h = str;
        return this;
    }

    public a b(boolean z) {
        this.o = z;
        return this;
    }

    private Animation a() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300L);
        return alphaAnimation;
    }

    public a(Context context, int i) {
        super(context, i);
        this.g = "";
        this.h = "";
        this.i = -16777216;
        this.k = true;
        this.m = 16.0f;
        this.n = 0;
        this.o = true;
        this.p = false;
        this.p = true;
        a(context);
    }

    private Drawable a(int i, int i2, boolean z) {
        if (!this.o) {
            return this.f6655a.getResources().getDrawable(NativeUIR.SLT_AS_IOS7_OTHER_BT_MIDDLE);
        }
        if (i == 1) {
            if (z) {
                return this.f6655a.getResources().getDrawable(NativeUIR.SLT_AS_IOS7_OTHER_BT_BOTTOM);
            }
            return this.f6655a.getResources().getDrawable(NativeUIR.SLT_AS_IOS7_OTHER_BT_SINGLE);
        }
        if (i == 2) {
            if (i2 != 0) {
                if (i2 != 1) {
                    return null;
                }
                return this.f6655a.getResources().getDrawable(NativeUIR.SLT_AS_IOS7_OTHER_BT_BOTTOM);
            }
            if (z) {
                return this.f6655a.getResources().getDrawable(NativeUIR.SLT_AS_IOS7_OTHER_BT_MIDDLE);
            }
            return this.f6655a.getResources().getDrawable(NativeUIR.SLT_AS_IOS7_OTHER_BT_TOP);
        }
        if (i <= 2) {
            return null;
        }
        if (i2 == 0) {
            if (z) {
                return this.f6655a.getResources().getDrawable(NativeUIR.SLT_AS_IOS7_OTHER_BT_MIDDLE);
            }
            return this.f6655a.getResources().getDrawable(NativeUIR.SLT_AS_IOS7_OTHER_BT_TOP);
        }
        if (i2 == i - 1) {
            return this.f6655a.getResources().getDrawable(NativeUIR.SLT_AS_IOS7_OTHER_BT_BOTTOM);
        }
        return this.f6655a.getResources().getDrawable(NativeUIR.SLT_AS_IOS7_OTHER_BT_MIDDLE);
    }

    public void a(int i) {
        if (this.k || c(i)) {
            return;
        }
        dismiss();
        this.k = true;
        List<c> list = this.f;
        if (list != null) {
            list.clear();
        }
    }

    public a a(float f) {
        this.m = f;
        return this;
    }

    public a a(String str) {
        this.g = str;
        return this;
    }

    public a a(boolean z) {
        this.j = z;
        return this;
    }

    public a a(JSONArray jSONArray) {
        this.f = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    this.f.add(new c(this, jSONObject.optString("title"), jSONObject.optString("style"), jSONObject.optString("color")));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return this;
    }

    public a a(b bVar) {
        this.b = bVar;
        return this;
    }

    public a d(int i) {
        this.i = i;
        return this;
    }
}
