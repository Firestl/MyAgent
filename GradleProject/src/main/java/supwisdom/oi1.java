package supwisdom;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.supwisdom.superapp.ui.activity.PrivacyActivity;
import com.supwisdom.zueb.R;

/* JADX INFO: compiled from: BottomPrivacyDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class oi1 extends ql0 implements View.OnClickListener {
    public Context h;
    public TextView i;
    public ImageView j;
    public TextView k;
    public c l;

    /* JADX INFO: compiled from: BottomPrivacyDialog.java */
    public class a extends ClickableSpan {
        public a() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(oi1.this.h, (Class<?>) PrivacyActivity.class);
            intent.putExtra("policyType", 0);
            oi1.this.h.startActivity(intent);
            oi1.this.dismiss();
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    /* JADX INFO: compiled from: BottomPrivacyDialog.java */
    public class b extends ClickableSpan {
        public b() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(oi1.this.h, (Class<?>) PrivacyActivity.class);
            intent.putExtra("policyType", 1);
            oi1.this.h.startActivity(intent);
            oi1.this.dismiss();
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    /* JADX INFO: compiled from: BottomPrivacyDialog.java */
    public interface c {
        void a();
    }

    public oi1(Context context) {
        super(context, R.style.dialog_transparent);
        this.h = context;
        c();
    }

    public final void c() {
        Window window = getWindow();
        setCanceledOnTouchOutside(false);
        View viewInflate = LayoutInflater.from(this.h).inflate(R.layout.dialog_bottom_privacy, (ViewGroup) null);
        this.j = (ImageView) viewInflate.findViewById(R.id.iv_close_privacy);
        this.i = (TextView) viewInflate.findViewById(R.id.privacyTv);
        this.k = (TextView) viewInflate.findViewById(R.id.tv_agree_login);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        d();
        setContentView(viewInflate);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = jn1.a(this.h, 300.0f);
        attributes.height = -2;
        attributes.gravity = 17;
        window.setAttributes(attributes);
    }

    public final void d() {
        String string = this.h.getResources().getString(R.string.privacy_bottom_login);
        String string2 = this.h.getResources().getString(R.string.privacy_tips_key1);
        String string3 = this.h.getResources().getString(R.string.privacy_tips_key2);
        int iIndexOf = string.indexOf(string2);
        int iIndexOf2 = string.indexOf(string3);
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(this.h.getResources().getColor(R.color.color_032)), iIndexOf, string2.length() + iIndexOf, 34);
        spannableString.setSpan(new ForegroundColorSpan(this.h.getResources().getColor(R.color.color_032)), iIndexOf2, string3.length() + iIndexOf2, 34);
        spannableString.setSpan(new a(), iIndexOf, string2.length() + iIndexOf, 34);
        spannableString.setSpan(new b(), iIndexOf2, string3.length() + iIndexOf2, 34);
        this.i.setMovementMethod(LinkMovementMethod.getInstance());
        this.i.setText(spannableString);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_close_privacy) {
            dismiss();
        } else {
            if (id != R.id.tv_agree_login) {
                return;
            }
            c cVar = this.l;
            if (cVar != null) {
                cVar.a();
            }
            dismiss();
        }
    }

    public void a(c cVar) {
        this.l = cVar;
    }
}
