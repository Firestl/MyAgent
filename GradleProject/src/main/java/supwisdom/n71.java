package supwisdom;

import android.content.Context;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sangfor.sdk.utils.SFLogN;
import supwisdom.i71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class n71 extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f8482a;
    public TextView b;

    public n71(Context context) {
        super(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int i = i71.c.k;
        layoutParams.setMargins(i, 0, i, 0);
        setLayoutParams(layoutParams);
        setOrientation(0);
        TextView textView = new TextView(context);
        this.f8482a = textView;
        textView.setLayoutParams(new LinearLayout.LayoutParams(i71.c.l, -2));
        this.f8482a.setTextColor(i71.b.f);
        this.f8482a.setTextSize(14.0f);
        this.f8482a.setSingleLine(true);
        addView(this.f8482a);
        this.b = new TextView(context);
        new LinearLayout.LayoutParams(-1, -2).setMargins(i71.c.g, 0, 0, 0);
        this.b.setTextColor(-16777216);
        this.b.setTextSize(14.0f);
        this.b.setSingleLine(true);
        this.b.setEllipsize(TextUtils.TruncateAt.END);
        addView(this.b);
    }

    public void a(String str) {
        TextView textView = this.f8482a;
        if (textView != null) {
            textView.setText(str);
        } else {
            SFLogN.d("LRTextLayout", "updateLeftText is fail, leftTextView is null");
        }
    }

    public void b(String str) {
        TextView textView = this.b;
        if (textView != null) {
            textView.setText(str);
        } else {
            SFLogN.d("LRTextLayout", "updateRightText is fail, rightTextView is null");
        }
    }
}
