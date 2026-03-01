package com.supwisdom.superapp.speech;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.supwisdom.zueb.R;
import com.taobao.weex.common.Constants;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.MissingResourceException;
import java.util.Random;
import java.util.ResourceBundle;
import supwisdom.nj1;
import supwisdom.qj1;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"Registered"})
public class BaiduASRDigitalDialog extends BaiduASRDialog {
    public EditText A;
    public SDKProgressBar B;
    public Drawable G;
    public ColorStateList L;
    public ColorStateList M;
    public ResourceBundle R;
    public String T;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4009e;
    public View h;
    public View i;
    public TextView j;
    public TextView k;
    public TextView l;
    public TextView m;
    public TextView n;
    public SDKAnimationView o;
    public TextView p;
    public TextView q;
    public TextView r;
    public ImageButton s;
    public ImageButton t;
    public TextView u;
    public View v;
    public qj1 w;
    public TextView x;
    public TextView y;
    public View z;
    public CharSequence f = "";
    public View g = null;
    public int C = 0;
    public int D = 0;
    public volatile int E = 0;
    public Message F = Message.obtain();
    public StateListDrawable H = new StateListDrawable();
    public StateListDrawable I = new StateListDrawable();
    public StateListDrawable J = new StateListDrawable();
    public StateListDrawable K = new StateListDrawable();
    public int N = 0;
    public int O = 0;
    public int P = 0;
    public int Q = 0;
    public Handler S = new Handler();
    public Random U = new Random();
    public Runnable V = new a();
    public View.OnClickListener W = new b();
    public Handler X = new c();

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BaiduASRDigitalDialog.this.y();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if ("speak_complete".equals(view.getTag())) {
                String string = BaiduASRDigitalDialog.this.l.getText().toString();
                if (string.equals(BaiduASRDigitalDialog.this.e("btn.start"))) {
                    BaiduASRDigitalDialog.this.C = 0;
                    BaiduASRDigitalDialog.this.D = 0;
                    BaiduASRDigitalDialog.this.B.setVisibility(4);
                    BaiduASRDigitalDialog.this.t();
                    return;
                }
                if (string.equals(BaiduASRDigitalDialog.this.e("btn.done"))) {
                    BaiduASRDigitalDialog baiduASRDigitalDialog = BaiduASRDigitalDialog.this;
                    if (baiduASRDigitalDialog.b == 4) {
                        baiduASRDigitalDialog.s();
                        BaiduASRDigitalDialog.this.p();
                        return;
                    } else {
                        baiduASRDigitalDialog.l();
                        BaiduASRDigitalDialog.this.a(7, 0);
                        return;
                    }
                }
                return;
            }
            if ("cancel_text_btn".equals(view.getTag())) {
                if (BaiduASRDigitalDialog.this.m.getText().toString().equals(BaiduASRDigitalDialog.this.e("btn.help"))) {
                    BaiduASRDigitalDialog.this.z();
                    return;
                } else {
                    BaiduASRDigitalDialog.this.finish();
                    return;
                }
            }
            if ("retry_text_btn".equals(view.getTag())) {
                BaiduASRDigitalDialog.this.C = 0;
                BaiduASRDigitalDialog.this.D = 0;
                BaiduASRDigitalDialog.this.A.setVisibility(8);
                BaiduASRDigitalDialog.this.B.setVisibility(4);
                BaiduASRDigitalDialog.this.t();
                return;
            }
            if ("cancel_btn".equals(view.getTag())) {
                BaiduASRDigitalDialog.this.finish();
                return;
            }
            if ("help_btn".equals(view.getTag())) {
                BaiduASRDigitalDialog.this.z();
                return;
            }
            if ("logo_1".equals(view.getTag()) || "logo_2".equals(view.getTag())) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://developer.baidu.com/static/community/servers/voice/sdk.html"));
                intent.setFlags(268435456);
                try {
                    BaiduASRDigitalDialog.this.startActivity(intent);
                    BaiduASRDigitalDialog.this.finish();
                } catch (Exception unused) {
                }
            }
        }
    }

    public class c extends Handler {
        public c() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 0) {
                if (i == 1) {
                    if (BaiduASRDigitalDialog.this.C <= 80) {
                        BaiduASRDigitalDialog.this.C += 3;
                        BaiduASRDigitalDialog.this.X.sendEmptyMessageDelayed(1, 1L);
                    } else {
                        BaiduASRDigitalDialog.this.C = 0;
                        BaiduASRDigitalDialog.this.D = 0;
                        BaiduASRDigitalDialog.this.A.setVisibility(8);
                        BaiduASRDigitalDialog.this.B.setVisibility(4);
                        if (BaiduASRDigitalDialog.this.f4009e == 0) {
                            BaiduASRDigitalDialog.this.finish();
                        }
                        BaiduASRDigitalDialog.this.X.removeMessages(1);
                    }
                    BaiduASRDigitalDialog.this.B.setProgress(BaiduASRDigitalDialog.this.C);
                    return;
                }
                return;
            }
            if (BaiduASRDigitalDialog.this.D >= 3000) {
                if (BaiduASRDigitalDialog.this.A.getVisibility() == 0) {
                    BaiduASRDigitalDialog.this.A.setVisibility(4);
                }
                BaiduASRDigitalDialog.this.j.setVisibility(4);
                if (BaiduASRDigitalDialog.this.E == 0) {
                    BaiduASRDigitalDialog.this.k.setText(BaiduASRDigitalDialog.this.e("tips.wait.net"));
                    BaiduASRDigitalDialog.this.k.setVisibility(0);
                }
            } else if (BaiduASRDigitalDialog.this.A.getVisibility() == 0) {
                BaiduASRDigitalDialog.this.j.setVisibility(4);
                BaiduASRDigitalDialog.this.k.setVisibility(4);
            } else {
                BaiduASRDigitalDialog.this.j.setVisibility(0);
                BaiduASRDigitalDialog.this.k.setVisibility(4);
            }
            BaiduASRDigitalDialog baiduASRDigitalDialog = BaiduASRDigitalDialog.this;
            baiduASRDigitalDialog.F.what = 0;
            if (baiduASRDigitalDialog.C <= 30) {
                BaiduASRDigitalDialog.this.D += 10;
                BaiduASRDigitalDialog.this.C++;
                BaiduASRDigitalDialog.this.X.sendEmptyMessageDelayed(0, 10L);
            } else if (BaiduASRDigitalDialog.this.C < 60) {
                BaiduASRDigitalDialog.this.D += 100;
                BaiduASRDigitalDialog.this.C++;
                BaiduASRDigitalDialog.this.X.sendEmptyMessageDelayed(0, 100L);
            } else if (BaiduASRDigitalDialog.this.D >= 15000) {
                BaiduASRDigitalDialog.this.l();
                BaiduASRDigitalDialog.this.a(2, 589824);
                BaiduASRDigitalDialog.this.C = 0;
                BaiduASRDigitalDialog.this.D = 0;
                BaiduASRDigitalDialog.this.B.setVisibility(4);
                BaiduASRDigitalDialog.this.X.removeMessages(0);
            } else {
                BaiduASRDigitalDialog.this.C = 60;
                BaiduASRDigitalDialog.this.D += 100;
                BaiduASRDigitalDialog.this.X.sendEmptyMessageDelayed(0, 100L);
            }
            BaiduASRDigitalDialog.this.B.setProgress(BaiduASRDigitalDialog.this.C);
        }
    }

    public final void A() {
        this.o.d();
    }

    public final void B() {
        this.o.a();
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void o() {
        this.j.setText(e("tips.state.listening"));
        this.o.e();
        this.S.removeCallbacks(this.V);
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog, supwisdom.rj1.b
    public void onAsrVolume(int i, int i2) {
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog, com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.Q = intent.getIntExtra("BaiduASRDigitalDialog_theme", this.Q);
        }
        v();
        x();
        t();
        w();
        q();
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void p() {
        this.j.setText(e("tips.state.recognizing"));
        this.l.setText(e("tips.state.recognizing"));
        this.B.setVisibility(0);
        this.X.sendEmptyMessage(0);
        this.l.setEnabled(false);
        A();
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void q() {
        this.o.c();
        if (TextUtils.isEmpty(this.f4008a)) {
            this.j.setText(e("tips.state.ready"));
        } else {
            this.j.setText(this.f4008a);
        }
        this.l.setText(e("btn.done"));
        this.l.setEnabled(true);
        this.S.removeCallbacks(this.V);
        if (!n().getBoolean("BaiduASRDigitalDialog_showTip", true) || this.w.getCount() <= 0) {
            return;
        }
        this.S.postDelayed(this.V, 3000L);
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void r() {
        this.X.removeMessages(1);
        this.X.removeMessages(0);
        this.C = 0;
        this.D = 0;
        this.A.setText("");
        this.A.setVisibility(4);
        this.o.setVisibility(0);
        this.o.b();
        this.j.setText(e("tips.state.wait"));
        this.i.setVisibility(4);
        this.h.setVisibility(0);
        this.l.setText(e("tips.state.initializing"));
        this.l.setEnabled(false);
        this.j.setVisibility(0);
        this.B.setVisibility(4);
        this.k.setVisibility(4);
        this.z.setVisibility(0);
        this.v.setVisibility(4);
        if (this.w.getCount() > 0) {
            this.t.setVisibility(0);
        }
        this.x.setVisibility(8);
        this.q.setVisibility(0);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:5:0x000b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void u() {
        /*
            r3 = this;
            int r0 = r3.Q
            r1 = -1020133376(0xffffffffc3320000, float:-178.0)
            r2 = 0
            switch(r0) {
                case 16777217: goto Lb;
                case 16777218: goto L16;
                case 16777219: goto L13;
                case 16777220: goto L18;
                default: goto L8;
            }
        L8:
            switch(r0) {
                case 33554433: goto Lb;
                case 33554434: goto L10;
                case 33554435: goto Ld;
                case 33554436: goto L18;
                default: goto Lb;
            }
        Lb:
            r1 = 0
            goto L18
        Ld:
            r1 = -1025900544(0xffffffffc2da0000, float:-109.0)
            goto L18
        L10:
            r1 = 1125580800(0x43170000, float:151.0)
            goto L18
        L13:
            r1 = -1026031616(0xffffffffc2d80000, float:-108.0)
            goto L18
        L16:
            r1 = 1125384192(0x43140000, float:148.0)
        L18:
            android.graphics.ColorMatrix r0 = new android.graphics.ColorMatrix
            r0.<init>()
            supwisdom.oj1.a(r0, r2, r2, r2, r1)
            android.graphics.ColorMatrixColorFilter r1 = new android.graphics.ColorMatrixColorFilter
            r1.<init>(r0)
            android.graphics.drawable.Drawable r0 = r3.G
            r0.setColorFilter(r1)
            android.graphics.drawable.StateListDrawable r0 = r3.H
            r0.setColorFilter(r1)
            android.graphics.drawable.StateListDrawable r0 = r3.I
            r0.setColorFilter(r1)
            android.graphics.drawable.StateListDrawable r0 = r3.J
            r0.setColorFilter(r1)
            com.supwisdom.superapp.speech.SDKProgressBar r0 = r3.B
            r0.setHsvFilter(r1)
            com.supwisdom.superapp.speech.SDKAnimationView r0 = r3.o
            r0.setHsvFilter(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.supwisdom.superapp.speech.BaiduASRDigitalDialog.u():void");
    }

    public final void v() {
        c(this.Q);
        View viewInflate = View.inflate(this, getResources().getIdentifier("bdspeech_digital_layout", Constants.Name.LAYOUT, getPackageName()), null);
        this.g = viewInflate;
        if (viewInflate != null) {
            viewInflate.findViewWithTag("bg_layout").setBackgroundDrawable(this.G);
            TextView textView = (TextView) this.g.findViewWithTag("tips_text");
            this.j = textView;
            textView.setTextColor(this.O);
            TextView textView2 = (TextView) this.g.findViewWithTag("tips_wait_net");
            this.k = textView2;
            textView2.setVisibility(4);
            this.k.setTextColor(this.O);
            this.q = (TextView) this.g.findViewWithTag("logo_1");
            this.r = (TextView) this.g.findViewWithTag("logo_2");
            this.q.setOnClickListener(this.W);
            this.r.setOnClickListener(this.W);
            this.q.setTextColor(this.N);
            this.r.setTextColor(this.N);
            TextView textView3 = (TextView) this.g.findViewWithTag("suggestion_tips");
            this.x = textView3;
            textView3.setTextColor(this.N);
            TextView textView4 = (TextView) this.g.findViewWithTag("suggestion_tips_2");
            this.y = textView4;
            textView4.setTextColor(this.N);
            SDKProgressBar sDKProgressBar = (SDKProgressBar) this.g.findViewWithTag(AbsoluteConst.JSON_KEY_PROGRESS);
            this.B = sDKProgressBar;
            sDKProgressBar.setVisibility(4);
            this.B.setTheme(this.Q);
            TextView textView5 = (TextView) this.g.findViewWithTag("speak_complete");
            this.l = textView5;
            textView5.setOnClickListener(this.W);
            this.l.setBackgroundDrawable(this.H);
            this.l.setTextColor(this.M);
            TextView textView6 = (TextView) this.g.findViewWithTag("cancel_text_btn");
            this.m = textView6;
            textView6.setOnClickListener(this.W);
            this.m.setBackgroundDrawable(this.I);
            this.m.setTextColor(this.L);
            TextView textView7 = (TextView) this.g.findViewWithTag("retry_text_btn");
            this.n = textView7;
            textView7.setOnClickListener(this.W);
            this.n.setBackgroundDrawable(this.J);
            this.n.setTextColor(this.M);
            TextView textView8 = (TextView) this.g.findViewWithTag("error_tips");
            this.p = textView8;
            textView8.setTextColor(this.P);
            Drawable drawable = getResources().getDrawable(getResources().getIdentifier("bdspeech_close_v2", "drawable", getPackageName()));
            ImageButton imageButton = (ImageButton) this.g.findViewWithTag("cancel_btn");
            this.s = imageButton;
            imageButton.setOnClickListener(this.W);
            this.s.setImageDrawable(drawable);
            ImageButton imageButton2 = (ImageButton) this.g.findViewWithTag("help_btn");
            this.t = imageButton2;
            imageButton2.setOnClickListener(this.W);
            this.t.setImageDrawable(this.K);
            View viewFindViewWithTag = this.g.findViewWithTag("error_reflect");
            this.i = viewFindViewWithTag;
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewFindViewWithTag.getLayoutParams();
            layoutParams.addRule(6, R.id.dialog_linear);
            layoutParams.addRule(8, R.id.dialog_linear);
            SDKAnimationView sDKAnimationView = (SDKAnimationView) this.g.findViewWithTag("voicewave_view");
            this.o = sDKAnimationView;
            sDKAnimationView.setThemeStyle(this.Q);
            this.h = this.g.findViewWithTag("main_reflect");
            this.o.setVisibility(4);
            this.z = this.g.findViewWithTag("recognizing_reflect");
            View viewFindViewWithTag2 = this.g.findViewWithTag("help_reflect");
            this.v = viewFindViewWithTag2;
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) viewFindViewWithTag2.getLayoutParams();
            layoutParams2.addRule(6, R.id.dialog_linear);
            layoutParams2.addRule(8, R.id.dialog_linear);
            TextView textView9 = (TextView) this.g.findViewWithTag("help_title");
            this.u = textView9;
            textView9.setTextColor(this.O);
            ListView listView = (ListView) this.g.findViewWithTag("suggestions_list");
            qj1 qj1Var = new qj1(this);
            this.w = qj1Var;
            qj1Var.setNotifyOnChange(true);
            this.w.a(this.O);
            listView.setAdapter((ListAdapter) this.w);
            EditText editText = (EditText) this.g.findViewWithTag("partial_text");
            this.A = editText;
            editText.setTextColor(this.O);
            requestWindowFeature(1);
            setContentView(new View(this));
            addContentView(this.g, new FrameLayout.LayoutParams(-1, -1));
        }
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        u();
    }

    @SuppressLint({"NewApi"})
    public void w() {
        this.w.clear();
        String[] stringArray = n().getStringArray("BaiduASRDigitalDialog_tips");
        boolean z = false;
        if (stringArray != null) {
            if (Build.VERSION.SDK_INT >= 11) {
                this.w.addAll(stringArray);
            } else {
                for (String str : stringArray) {
                    this.w.add(str);
                }
            }
        }
        if (this.w.getCount() > 0) {
            this.t.setVisibility(0);
            z = n().getBoolean("BaiduASRDigitalDialog_showTips", false);
        } else {
            this.t.setVisibility(4);
        }
        if (z) {
            z();
        }
    }

    public final void x() {
        try {
            this.R = ResourceBundle.getBundle("BaiduASRDigitalDialog");
            this.q.setText(e("tips.copyright"));
            this.r.setText(e("tips.copyright"));
            this.n.setText(e("btn.retry"));
            this.u.setText(e("tips.help.title"));
            this.T = e("tips.suggestion.prefix");
        } catch (MissingResourceException e2) {
            Log.w("BSDigitalDialog", "loadI18N error", e2);
        }
    }

    public final void y() {
        qj1 qj1Var = this.w;
        String item = qj1Var.getItem(this.U.nextInt(qj1Var.getCount()));
        this.x.setText(this.T + item);
        this.x.setVisibility(0);
        this.q.setVisibility(8);
    }

    public final void z() {
        this.i.setVisibility(4);
        this.h.setVisibility(0);
        this.z.setVisibility(4);
        this.v.setVisibility(0);
        this.l.setText(e("btn.start"));
        this.l.setEnabled(true);
        this.t.setVisibility(4);
        this.S.removeCallbacks(this.V);
        l();
    }

    public final void c(int i) {
        Integer numValueOf;
        Integer numValueOf2;
        Integer num;
        Integer num2;
        Integer numValueOf3 = Integer.valueOf(getResources().getIdentifier("bdspeech_btn_normal", "drawable", getPackageName()));
        Integer numValueOf4 = Integer.valueOf(getResources().getIdentifier("bdspeech_btn_pressed", "drawable", getPackageName()));
        Integer numValueOf5 = Integer.valueOf(getResources().getIdentifier("bdspeech_right_normal", "drawable", getPackageName()));
        Integer numValueOf6 = Integer.valueOf(getResources().getIdentifier("bdspeech_right_pressed", "drawable", getPackageName()));
        int[] iArr = new int[3];
        int[] iArr2 = new int[3];
        if (nj1.a(i)) {
            Integer numValueOf7 = Integer.valueOf(getResources().getIdentifier("bdspeech_digital_deep_bg", "drawable", getPackageName()));
            Integer numValueOf8 = Integer.valueOf(getResources().getIdentifier("bdspeech_left_deep_normal", "drawable", getPackageName()));
            numValueOf = Integer.valueOf(getResources().getIdentifier("bdspeech_left_deep_pressed", "drawable", getPackageName()));
            numValueOf2 = Integer.valueOf(getResources().getIdentifier("bdspeech_btn_recognizing_deep", "drawable", getPackageName()));
            iArr[0] = -1;
            iArr[1] = -11711155;
            iArr[2] = -1;
            iArr2[0] = -1;
            iArr2[1] = -11711155;
            iArr2[2] = -1;
            this.N = -10592672;
            this.O = -3750202;
            this.P = -1579033;
            num = numValueOf7;
            num2 = numValueOf8;
            this.K.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(getResources().getIdentifier("bdspeech_help_pressed_deep", "drawable", getPackageName())));
            this.K.addState(new int[0], getResources().getDrawable(getResources().getIdentifier("bdspeech_help_deep", "drawable", getPackageName())));
        } else {
            Integer numValueOf9 = Integer.valueOf(getResources().getIdentifier("bdspeech_digital_bg", "drawable", getPackageName()));
            Integer numValueOf10 = Integer.valueOf(getResources().getIdentifier("bdspeech_left_normal", "drawable", getPackageName()));
            numValueOf = Integer.valueOf(getResources().getIdentifier("bdspeech_left_pressed", "drawable", getPackageName()));
            numValueOf2 = Integer.valueOf(getResources().getIdentifier("bdspeech_btn_recognizing", "drawable", getPackageName()));
            iArr[0] = -12105913;
            iArr[1] = -1513240;
            iArr[2] = -12105913;
            iArr2[0] = -1;
            iArr2[1] = -4276546;
            iArr2[2] = -1;
            this.N = -2631721;
            this.O = -9868951;
            this.P = -9803158;
            num = numValueOf9;
            num2 = numValueOf10;
            this.K.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(getResources().getIdentifier("bdspeech_help_pressed_light", "drawable", getPackageName())));
            this.K.addState(new int[0], getResources().getDrawable(getResources().getIdentifier("bdspeech_help_light", "drawable", getPackageName())));
        }
        this.G = getResources().getDrawable(num.intValue());
        this.H.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, getResources().getDrawable(numValueOf4.intValue()));
        this.H.addState(new int[]{-16842910}, getResources().getDrawable(numValueOf2.intValue()));
        this.H.addState(new int[0], getResources().getDrawable(numValueOf3.intValue()));
        this.I.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(numValueOf.intValue()));
        this.I.addState(new int[0], getResources().getDrawable(num2.intValue()));
        this.J.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(numValueOf6.intValue()));
        this.J.addState(new int[0], getResources().getDrawable(numValueOf5.intValue()));
        int[][] iArr3 = {new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, new int[]{-16842910}, new int[1]};
        this.L = new ColorStateList(iArr3, iArr);
        this.M = new ColorStateList(iArr3, iArr2);
    }

    public final String e(String str) {
        ResourceBundle resourceBundle = this.R;
        if (resourceBundle != null) {
            try {
                return resourceBundle.getString(str);
            } catch (Exception e2) {
                Log.w("BSDigitalDialog", "get internationalization error key:" + str, e2);
            }
        }
        return null;
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void b(String[] strArr) {
        if (strArr == null || strArr == null || strArr.length <= 0) {
            return;
        }
        if (this.A.getVisibility() != 0) {
            this.A.setVisibility(0);
            this.k.setVisibility(4);
            this.j.setVisibility(4);
        }
        this.A.setText(strArr[0]);
        EditText editText = this.A;
        editText.setSelection(editText.getText().length());
        this.D = 0;
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void a(float f) {
        this.o.setCurrentDBLevelMeter(f);
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void a(int i, int i2) {
        boolean z;
        this.f4009e = i;
        this.X.removeMessages(0);
        this.X.sendEmptyMessage(1);
        this.k.setVisibility(4);
        B();
        if (i != 0) {
            if (Log.isLoggable("BSDigitalDialog", 3)) {
                Log.d("BSDigitalDialog", String.format("onError:errorType %1$d,errorCode %2$d ", Integer.valueOf(i), Integer.valueOf(i2)));
            }
            this.X.removeMessages(1);
            this.y.setVisibility(8);
            switch (i) {
                case 1:
                    SpannableString spannableString = new SpannableString("网络超时，再试一次");
                    spannableString.setSpan(new URLSpan("#") { // from class: com.supwisdom.superapp.speech.BaiduASRDigitalDialog.3
                        @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
                        public void onClick(View view) {
                            BaiduASRDigitalDialog.this.t();
                        }
                    }, 5, 9, 33);
                    this.f = spannableString;
                    z = false;
                    break;
                case 2:
                    if (i2 == 589824) {
                        this.f = e("tips.error.network_unusable");
                    } else {
                        this.f = e("tips.error.network");
                    }
                    z = false;
                    break;
                case 3:
                    this.f = "启动录音失败";
                    if (this.w.getCount() <= 0) {
                        z = false;
                    } else {
                        z = n().getBoolean("BaiduASRDigitalDialog_showHelp", true);
                        if (n().getBoolean("BaiduASRDigitalDialog_showTip", true)) {
                            qj1 qj1Var = this.w;
                            String item = qj1Var.getItem(this.U.nextInt(qj1Var.getCount()));
                            this.y.setText(this.T + item);
                            this.y.setVisibility(0);
                        }
                    }
                    break;
                case 4:
                    this.f = e("tips.error.decoder");
                    z = false;
                    break;
                case 5:
                    this.f = "客户端错误";
                    z = false;
                    break;
                case 6:
                    this.f = e("tips.error.silent");
                    z = false;
                    break;
                case 7:
                    this.f = "没有匹配的识别结果";
                    z = false;
                    break;
                case 8:
                    this.f = "引擎忙";
                    z = false;
                    break;
                case 9:
                    this.f = "权限不足，请检查设置";
                    z = false;
                    break;
                default:
                    this.f = e("tips.error.internal");
                    z = false;
                    break;
            }
            this.m.setText(e(z ? "btn.help" : "btn.cancel"));
            this.k.setVisibility(4);
            this.p.setMovementMethod(LinkMovementMethod.getInstance());
            this.p.setText(this.f);
            this.i.setVisibility(0);
            this.h.setVisibility(4);
            this.t.setVisibility(4);
            this.S.removeCallbacks(this.V);
        }
        this.o.setVisibility(4);
    }
}
