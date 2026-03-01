package supwisdom;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.igexin.sdk.PushManager;
import com.supwisdom.superapp.WXApplication;
import com.supwisdom.superapp.ui.activity.MainActivity;
import com.supwisdom.superapp.ui.activity.PrivacyActivity;
import com.supwisdom.zueb.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/* JADX INFO: compiled from: PrivacyDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class cj1 extends Dialog implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f7219a;
    public TextView b;
    public TextView c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f7220e;
    public TextView f;
    public Boolean g;

    /* JADX INFO: compiled from: PrivacyDialog.java */
    public class a extends ClickableSpan {
        public a() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(cj1.this.f7219a, (Class<?>) PrivacyActivity.class);
            intent.putExtra("policyType", 0);
            cj1.this.f7219a.startActivity(intent);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    /* JADX INFO: compiled from: PrivacyDialog.java */
    public class b extends ClickableSpan {
        public b() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(cj1.this.f7219a, (Class<?>) PrivacyActivity.class);
            intent.putExtra("policyType", 1);
            cj1.this.f7219a.startActivity(intent);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    /* JADX INFO: compiled from: PrivacyDialog.java */
    public interface c {
        void a();

        void b();
    }

    public cj1(Context context, Boolean bool) {
        super(context, R.style.Dialog);
        this.f7219a = context;
        this.g = bool;
        a();
    }

    public final void a() {
        Window window = getWindow();
        window.requestFeature(1);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_privacy);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 17;
        window.setAttributes(attributes);
        this.d = (TextView) findViewById(R.id.cancelTv);
        this.c = (TextView) findViewById(R.id.agreeTv);
        this.b = (TextView) findViewById(R.id.contentTv);
        this.f = (TextView) findViewById(R.id.tv_new_privacy);
        this.d.setOnClickListener(this);
        this.c.setOnClickListener(this);
        b();
        if (this.g.booleanValue()) {
            this.f.setVisibility(0);
        }
    }

    public final void b() {
        String string = this.f7219a.getResources().getString(R.string.privacy_tips_dialog);
        String string2 = this.f7219a.getResources().getString(R.string.privacy_tips_key1);
        String string3 = this.f7219a.getResources().getString(R.string.privacy_tips_key2);
        int iIndexOf = string.indexOf(string2);
        int iIndexOf2 = string.indexOf(string3);
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(this.f7219a.getResources().getColor(R.color.color_032)), iIndexOf, string2.length() + iIndexOf, 34);
        spannableString.setSpan(new ForegroundColorSpan(this.f7219a.getResources().getColor(R.color.color_032)), iIndexOf2, string3.length() + iIndexOf2, 34);
        spannableString.setSpan(new a(), iIndexOf, string2.length() + iIndexOf, 34);
        spannableString.setSpan(new b(), iIndexOf2, string3.length() + iIndexOf2, 34);
        this.b.setMovementMethod(LinkMovementMethod.getInstance());
        this.b.setText(spannableString);
    }

    public void c() {
        if (isShowing()) {
            return;
        }
        show();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.agreeTv) {
            if (id != R.id.cancelTv) {
                return;
            }
            sh1.c.a("isAgreePrivacy", (Boolean) false);
            ((MainActivity) this.f7219a).finish();
            dismiss();
            c cVar = this.f7220e;
            if (cVar != null) {
                cVar.b();
                return;
            }
            return;
        }
        sh1.c.a("isAgreePrivacy", (Boolean) true);
        PushManager.getInstance().initialize(this.f7219a);
        UMConfigure.init(this.f7219a, fn1.K, "umeng", 1, "");
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL);
        WXApplication.instance.initMainProcess();
        WXApplication.instance.initDCUniMPSDK();
        WXApplication.instance.regToWx();
        WXApplication.instance.initU_App();
        WXApplication.instance.initGetuiSDK();
        WXApplication.instance.registerNetwork();
        WXApplication.instance.initVirtualCard();
        gn1.a(sh1.c.c(fn1.s));
        dismiss();
        c cVar2 = this.f7220e;
        if (cVar2 != null) {
            cVar2.a();
        }
    }

    public void a(c cVar) {
        this.f7220e = cVar;
    }
}
