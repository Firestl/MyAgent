package supwisdom;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sangfor.sdk.utils.SFLogN;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import supwisdom.i71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class r81 extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f9022a;
    public TextView b;
    public Button c;
    public Button d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f9023e;
    public LinearLayout f;
    public ProgressBar g;
    public TextView h;

    public r81(Context context) {
        super(context);
        float f = context.getResources().getDisplayMetrics().density;
        SFLogN.c("CustomDialogView", "NoTitleDialogView density:" + f);
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        setOrientation(1);
        LinearLayout linearLayout = new LinearLayout(context);
        addView(linearLayout);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setOrientation(1);
        linearLayout.setMinimumHeight(a(f, 60));
        TextView textView = new TextView(context);
        this.f9022a = textView;
        linearLayout.addView(textView);
        this.f9022a.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.f9022a.setPadding(a(f, 24), a(f, 24), a(f, 24), a(f, 16));
        this.f9022a.setGravity(17);
        this.f9022a.setTextColor(i71.b.c);
        this.f9022a.setVerticalScrollBarEnabled(true);
        this.f9022a.setTextSize(18.0f);
        this.f9022a.setEllipsize(TextUtils.TruncateAt.END);
        this.f9022a.setMaxLines(5);
        TextView textView2 = new TextView(context);
        this.b = textView2;
        linearLayout.addView(textView2);
        this.b.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.b.setPadding(a(f, 24), a(f, 0), a(f, 24), a(f, 24));
        this.b.setGravity(17);
        this.b.setTextColor(i71.b.d);
        this.b.setVerticalScrollBarEnabled(true);
        this.b.setTextSize(16.0f);
        this.b.setEllipsize(TextUtils.TruncateAt.END);
        this.b.setMaxLines(5);
        LinearLayout linearLayout2 = new LinearLayout(context);
        this.f = linearLayout2;
        addView(linearLayout2);
        this.f.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.f.setOrientation(0);
        this.f.setGravity(17);
        ProgressBar progressBar = new ProgressBar(context, null, R.attr.progressBarStyleHorizontal);
        this.g = progressBar;
        this.f.addView(progressBar);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, a(f, 8), 1.0f);
        layoutParams.setMargins(0, 0, a(f, 10), 0);
        this.g.setLayoutParams(layoutParams);
        this.g.setProgressDrawable(a(context, "update_progress_drawable.xml"));
        this.g.setMax(100);
        this.g.setProgress(1);
        TextView textView3 = new TextView(context);
        this.h = textView3;
        this.f.addView(textView3);
        this.h.setLayoutParams(new LinearLayout.LayoutParams(a(f, 40), -2));
        this.h.setText("1%");
        this.h.setTextSize(2, 13.0f);
        this.f.setPadding(a(f, 24), 0, a(f, 24), a(f, 24));
        View view = new View(context);
        addView(view);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
        int i = i71.b.f7924e;
        view.setBackgroundColor(i);
        LinearLayout linearLayout3 = new LinearLayout(context);
        addView(linearLayout3);
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(-1, a(f, 48)));
        linearLayout3.setOrientation(0);
        Button button = new Button(context);
        this.c = button;
        linearLayout3.addView(button);
        this.c.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        this.c.setGravity(17);
        this.c.setAllCaps(false);
        Button button2 = this.c;
        int i2 = i71.b.f7923a;
        button2.setTextColor(i2);
        this.c.setTextSize(16.0f);
        View view2 = new View(context);
        this.f9023e = view2;
        linearLayout3.addView(view2);
        this.f9023e.setLayoutParams(new LinearLayout.LayoutParams(1, -1));
        this.f9023e.setBackgroundColor(i);
        Button button3 = new Button(context);
        this.d = button3;
        linearLayout3.addView(button3);
        this.d.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        this.d.setGravity(17);
        this.d.setAllCaps(false);
        this.d.setTextColor(i2);
        this.d.setTextSize(16.0f);
    }

    public int a(float f, int i) {
        return (int) ((i * f) + 0.5f);
    }

    public final Drawable a(Context context, String str) {
        try {
            return Drawable.createFromXml(context.getResources(), b(context, "assets/" + str));
        } catch (Exception e2) {
            SFLogN.a("CustomDialogView", "assets2Drawable failed!", e2);
            return null;
        }
    }

    public final XmlPullParser b(Context context, String str) {
        try {
            return context.getAssets().openXmlResourceParser(str);
        } catch (IOException e2) {
            SFLogN.a("CustomDialogView", "getDrawableXmlPullParser failed!", e2);
            return null;
        }
    }
}
