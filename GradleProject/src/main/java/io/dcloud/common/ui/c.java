package io.dcloud.common.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.URLSpan;
import android.view.View;
import io.dcloud.WebviewActivity;
import io.dcloud.base.R;
import supwisdom.k7;

/* JADX INFO: loaded from: classes2.dex */
public class c {
    public static c b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f6460a;

    public class a extends ClickableSpan {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ URLSpan f6461a;

        public a(URLSpan uRLSpan) {
            this.f6461a = uRLSpan;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            try {
                String url = this.f6461a.getURL();
                Intent intent = new Intent();
                intent.setClass(c.this.f6460a, WebviewActivity.class);
                intent.putExtra("url", url);
                intent.setData(Uri.parse(url));
                intent.setAction("android.intent.action.VIEW");
                intent.setFlags(268435456);
                intent.putExtra("ANIM", "POP");
                c.this.f6460a.startActivity(intent, k7.a(c.this.f6460a, R.anim.dcloud_pop_in, R.anim.dcloud_pop_in_out).a());
            } catch (Exception unused) {
            }
        }
    }

    public c(Context context) {
        this.f6460a = context;
    }

    public static c a(Context context) {
        if (b == null) {
            b = new c(context);
        }
        return b;
    }

    public CharSequence a(String str) {
        Spanned spannedFromHtml = Html.fromHtml(str);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spannedFromHtml);
        for (URLSpan uRLSpan : (URLSpan[]) spannableStringBuilder.getSpans(0, spannedFromHtml.length(), URLSpan.class)) {
            a(spannableStringBuilder, uRLSpan);
        }
        return spannableStringBuilder;
    }

    private void a(SpannableStringBuilder spannableStringBuilder, URLSpan uRLSpan) {
        int spanStart = spannableStringBuilder.getSpanStart(uRLSpan);
        int spanEnd = spannableStringBuilder.getSpanEnd(uRLSpan);
        spannableStringBuilder.setSpan(new a(uRLSpan), spanStart, spanEnd, spannableStringBuilder.getSpanFlags(uRLSpan));
        spannableStringBuilder.setSpan(new TextAppearanceSpan(this.f6460a, R.style.textAppearance), spanStart, spanEnd, 33);
    }
}
