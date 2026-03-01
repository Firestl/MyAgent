package io.dcloud.common.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.dcloud.PdrR;
import io.dcloud.WebviewActivity;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.ui.Info.AndroidPrivacyResponse;
import io.dcloud.common.ui.b;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import java.io.File;
import supwisdom.k7;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f6445a;
    public c b;
    public b.InterfaceC0157b d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6446e;
    public d h;
    public AndroidPrivacyResponse c = null;
    public int f = PdrR.UNI_CUSTOM_PRIVACY_DIALOG_LAYOUT;
    public boolean g = true;

    /* JADX INFO: renamed from: io.dcloud.common.ui.a$a, reason: collision with other inner class name */
    public class C0154a extends b {
        public final /* synthetic */ URLSpan b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0154a(e eVar, URLSpan uRLSpan) {
            super(a.this, eVar);
            this.b = uRLSpan;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            try {
                Intent intent = new Intent();
                intent.setClass(a.this.f6445a, WebviewActivity.class);
                String url = this.b.getURL();
                if (!TextUtils.isEmpty(url) && !url.startsWith(DeviceInfo.HTTP_PROTOCOL) && !url.startsWith(DeviceInfo.HTTPS_PROTOCOL) && !url.startsWith("HTTP://") && !url.startsWith("HTTPS://")) {
                    if (url.startsWith("./")) {
                        url = url.substring(2);
                    }
                    String str = BaseInfo.sDefaultBootApp + "/www/" + url;
                    if (io.dcloud.common.ui.b.a().b()) {
                        url = "file:///android_asset/apps/" + str;
                    } else {
                        url = "file://" + new File(BaseInfo.sCacheFsAppsPath + str).getPath();
                    }
                    intent.putExtra(WebviewActivity.isLocalHtmlParam, true);
                }
                intent.putExtra("url", url);
                intent.setData(Uri.parse(url));
                intent.setAction("android.intent.action.VIEW");
                intent.setFlags(268435456);
                intent.putExtra("ANIM", "POP");
                a.this.f6445a.startActivity(intent, k7.a(a.this.f6445a, R.anim.dcloud_pop_in, R.anim.dcloud_pop_in_out).a());
            } catch (Exception unused) {
            }
        }
    }

    public abstract class b extends ClickableSpan {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e f6447a;

        public b(a aVar, e eVar) {
            this.f6447a = eVar;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            e eVar = this.f6447a;
            if (eVar != null) {
                textPaint.setUnderlineText(eVar.b);
                textPaint.setColor(this.f6447a.f6455a);
            } else {
                textPaint.setUnderlineText(false);
                textPaint.setColor(-16776961);
            }
        }
    }

    public class c extends Dialog {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f6448a;
        public TextView b;
        public TextView c;
        public Button d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Button f6449e;
        public LinearLayout f;

        /* JADX INFO: renamed from: io.dcloud.common.ui.a$c$a, reason: collision with other inner class name */
        public class ViewOnClickListenerC0155a implements View.OnClickListener {
            public ViewOnClickListenerC0155a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                c.this.dismiss();
                a.this.h.a(a.this.c.version);
            }
        }

        public class b implements View.OnClickListener {
            public b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                c.this.dismiss();
                a.this.h.onCancel();
            }
        }

        /* JADX INFO: renamed from: io.dcloud.common.ui.a$c$c, reason: collision with other inner class name */
        public class ViewOnClickListenerC0156c implements View.OnClickListener {
            public ViewOnClickListenerC0156c() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                c.this.dismiss();
                a.this.h.onCancel();
            }
        }

        public class d implements View.OnClickListener {
            public d() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                c.this.cancel();
            }
        }

        public class e implements View.OnClickListener {
            public e() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                c.this.cancel();
            }
        }

        public c(Context context) {
            super(context);
            requestWindowFeature(1);
            this.f6448a = context;
            c();
            a();
            if (f.a(this.f.getTag() != null ? this.f.getTag().toString() : "").f6456a) {
                getWindow().setLayout(-1, -1);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            int color;
            int color2;
            this.d.setOnClickListener(new ViewOnClickListenerC0155a());
            if (a.this.g) {
                if (!TextUtils.isEmpty(a.this.c.second.title)) {
                    this.b.setText(a.this.c.second.title);
                }
                if (!TextUtils.isEmpty(a.this.c.second.message)) {
                    e eVarA = e.a(this.c.getTag() != null ? this.c.getTag().toString() : "");
                    this.c.setMovementMethod(LinkMovementMethod.getInstance());
                    this.c.setAutoLinkMask(15);
                    TextView textView = this.c;
                    a aVar = a.this;
                    textView.setText(aVar.a(aVar.c.second.message, eVarA));
                    this.c.setGravity(a.this.a("left"));
                }
                if (!TextUtils.isEmpty(a.this.c.second.buttonAccept)) {
                    this.d.setVisibility(0);
                    this.d.setText(a.this.c.second.buttonAccept);
                }
                if (TextUtils.isEmpty(a.this.c.second.buttonRefuse)) {
                    this.f6449e.setVisibility(8);
                } else {
                    this.f6449e.setText(a.this.c.second.buttonRefuse);
                    this.f6449e.setVisibility(0);
                    this.f6449e.setOnClickListener(new b());
                }
            } else {
                if (!TextUtils.isEmpty(a.this.c.title)) {
                    this.b.setText(a.this.c.title);
                }
                if (!TextUtils.isEmpty(a.this.c.message)) {
                    e eVarA2 = e.a(this.c.getTag() != null ? this.c.getTag().toString() : "");
                    this.c.setMovementMethod(LinkMovementMethod.getInstance());
                    this.c.setAutoLinkMask(15);
                    TextView textView2 = this.c;
                    a aVar2 = a.this;
                    textView2.setText(aVar2.a(aVar2.c.message, eVarA2));
                    this.c.setGravity(a.this.a("left"));
                }
                if (!TextUtils.isEmpty(a.this.c.buttonAccept)) {
                    this.d.setVisibility(0);
                    this.d.setText(a.this.c.buttonAccept);
                }
                if (TextUtils.isEmpty(a.this.c.buttonRefuse)) {
                    this.f6449e.setVisibility(8);
                } else {
                    this.f6449e.setText(a.this.c.buttonRefuse);
                    this.f6449e.setVisibility(0);
                    this.f6449e.setOnClickListener(new ViewOnClickListenerC0156c());
                }
            }
            if (a.this.c.styles != null) {
                if (!TextUtils.isEmpty(a.this.c.styles.backgroundColor)) {
                    String str = !TextUtils.isEmpty(a.this.c.styles.borderRadius) ? a.this.c.styles.borderRadius : "10px";
                    int color3 = -1;
                    try {
                        color3 = Color.parseColor(a.this.c.styles.backgroundColor);
                    } catch (Exception unused) {
                    }
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setColor(color3);
                    gradientDrawable.setCornerRadius(a.this.a(this.f6448a, PdrUtil.parseInt(str, 1, 10)));
                    this.f.setBackground(gradientDrawable);
                }
                int color4 = -16777216;
                if (a.this.c.styles.title != null) {
                    try {
                        color = Color.parseColor(a.this.c.styles.title.color);
                    } catch (Exception unused2) {
                        color = -16777216;
                    }
                    this.b.setTextColor(color);
                }
                if (a.this.c.styles.buttonAccept != null && !TextUtils.isEmpty(a.this.c.styles.buttonAccept.color)) {
                    try {
                        color2 = Color.parseColor(a.this.c.styles.buttonAccept.color);
                    } catch (Exception unused3) {
                        color2 = -16777216;
                    }
                    this.d.setTextColor(color2);
                }
                if (a.this.c.styles.buttonRefuse == null || TextUtils.isEmpty(a.this.c.styles.buttonRefuse.color)) {
                    return;
                }
                try {
                    color4 = Color.parseColor(a.this.c.styles.buttonRefuse.color);
                } catch (Exception unused4) {
                }
                this.f6449e.setTextColor(color4);
            }
        }

        private void c() {
            View viewInflate = LayoutInflater.from(this.f6448a).inflate(a.this.f, (ViewGroup) null);
            setContentView(viewInflate);
            this.f6449e = (Button) viewInflate.findViewById(R.id.btn_custom_privacy_cancel);
            this.d = (Button) viewInflate.findViewById(R.id.btn_custom_privacy_sure);
            this.c = (TextView) viewInflate.findViewById(R.id.tv_privacy_content);
            this.b = (TextView) viewInflate.findViewById(R.id.tv_custom_privacy_title);
            this.f = (LinearLayout) viewInflate.findViewById(R.id.ll_content_layout);
            getWindow().setBackgroundDrawable(new ColorDrawable(0));
            d();
        }

        public void d() {
            Display defaultDisplay = ((Activity) this.f6448a).getWindowManager().getDefaultDisplay();
            if (this.f6448a.getResources().getConfiguration().orientation == 1) {
                this.c.setMaxHeight((int) (((double) defaultDisplay.getHeight()) * 0.6d));
            } else {
                this.c.setMaxHeight((int) (((double) defaultDisplay.getHeight()) * 0.5d));
            }
        }

        private void a() {
            this.f6449e.setOnClickListener(new d());
            this.d.setOnClickListener(new e());
        }
    }

    public interface d {
        void a(String str);

        void onCancel();
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6455a;
        public boolean b;

        public static e a(String str) {
            e eVar = new e();
            if (TextUtils.isEmpty(str)) {
                return eVar;
            }
            JSONObject object = JSON.parseObject(str);
            eVar.b = object.getBoolean("linkLine").booleanValue();
            eVar.f6455a = Color.parseColor(object.getString("linkColor"));
            return eVar;
        }
    }

    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f6456a = false;

        public static f a(String str) {
            f fVar = new f();
            if (TextUtils.isEmpty(str)) {
                return fVar;
            }
            JSONObject object = JSON.parseObject(str);
            if (object.containsKey(IApp.ConfigProperty.CONFIG_FULLSCREEN)) {
                fVar.f6456a = object.getBoolean(IApp.ConfigProperty.CONFIG_FULLSCREEN).booleanValue();
            }
            return fVar;
        }
    }

    public a(Context context) {
        this.f6445a = context;
    }

    public b.InterfaceC0157b b() {
        return this.d;
    }

    public boolean c() {
        return this.f6446e;
    }

    public void d() {
        c cVar = new c(this.f6445a);
        this.b = cVar;
        cVar.setCanceledOnTouchOutside(false);
        this.b.setCancelable(false);
        this.b.b();
        this.b.show();
    }

    public void a(b.InterfaceC0157b interfaceC0157b) {
        this.d = interfaceC0157b;
    }

    public void a(boolean z) {
        this.f6446e = z;
    }

    public void a(int i) {
        if (i != 0) {
            this.f = i;
        }
    }

    public void a(AndroidPrivacyResponse androidPrivacyResponse, boolean z, d dVar) {
        this.c = androidPrivacyResponse;
        this.g = z;
        this.h = dVar;
    }

    public CharSequence a(String str, e eVar) {
        Spanned spannedFromHtml = Html.fromHtml(str);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spannedFromHtml);
        for (URLSpan uRLSpan : (URLSpan[]) spannableStringBuilder.getSpans(0, spannedFromHtml.length(), URLSpan.class)) {
            a(spannableStringBuilder, uRLSpan, eVar);
        }
        return spannableStringBuilder;
    }

    private void a(SpannableStringBuilder spannableStringBuilder, URLSpan uRLSpan, e eVar) {
        spannableStringBuilder.setSpan(new C0154a(eVar, uRLSpan), spannableStringBuilder.getSpanStart(uRLSpan), spannableStringBuilder.getSpanEnd(uRLSpan), spannableStringBuilder.getSpanFlags(uRLSpan));
    }

    public void a() {
        c cVar = this.b;
        if (cVar != null) {
            cVar.dismiss();
            this.b = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(String str) {
        if (!TextUtils.isEmpty(str)) {
            str.hashCode();
            byte b2 = -1;
            int iHashCode = str.hashCode();
            if (iHashCode != -1383228885) {
                if (iHashCode != 3317767) {
                    if (iHashCode == 108511772 && str.equals("right")) {
                        b2 = 2;
                    }
                } else if (str.equals("left")) {
                    b2 = 1;
                }
            } else if (str.equals("bottom")) {
                b2 = 0;
            }
            if (b2 == 0) {
                return 80;
            }
            if (b2 == 1) {
                return 3;
            }
            if (b2 == 2) {
                return 5;
            }
        }
        return 17;
    }
}
