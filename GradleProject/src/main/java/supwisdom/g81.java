package supwisdom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sangfor.sdk.utils.SFLogN;
import supwisdom.i71;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class g81 extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ImageView f7704a;
    public TextView b;
    public TextView c;
    public TextView d;

    /* JADX INFO: compiled from: Proguard */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f7705a;

        public a(int i) {
            this.f7705a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int lineCount = g81.this.c.getLineCount();
            boolean z = lineCount > this.f7705a;
            TextView textView = g81.this.c;
            if (z) {
                lineCount = this.f7705a;
            }
            textView.setMaxLines(lineCount);
            if (g81.this.d != null) {
                g81.this.d.setVisibility(z ? 0 : 8);
            }
            if (z) {
                g81.this.c.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
            }
        }
    }

    public g81(Context context) {
        super(context);
        this.d = null;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int i = i71.c.i;
        layoutParams.setMargins(i, 0, i, 0);
        setLayoutParams(layoutParams);
        setOrientation(1);
        this.f7704a = new ImageView(context);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(i, i71.c.h, i, 0);
        this.f7704a.setMinimumHeight(i71.c.p);
        this.f7704a.setLayoutParams(layoutParams2);
        this.f7704a.setAdjustViewBounds(false);
        this.f7704a.setScaleType(ImageView.ScaleType.FIT_CENTER);
        addView(this.f7704a);
        this.b = new TextView(context);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.gravity = 17;
        int i2 = i71.c.g;
        layoutParams3.setMargins(0, i2, 0, 0);
        this.b.setLayoutParams(layoutParams3);
        this.b.setTextColor(-16777216);
        this.b.setTextSize(20.0f);
        this.b.setTypeface(Typeface.defaultFromStyle(1));
        addView(this.b);
        this.c = new TextView(context);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams4.setMargins(0, i2, 0, 0);
        this.c.setLayoutParams(layoutParams4);
        this.c.setTextColor(i71.b.f);
        this.c.setTextSize(14.0f);
        this.c.setGravity(17);
        this.c.setLineSpacing(14.0f, 1.0f);
        addView(this.c);
        TextView textView = new TextView(context);
        this.d = textView;
        textView.setText(i71.b.A4);
        this.d.setTextSize(14.0f);
        int[][] iArr = {Button.PRESSED_ENABLED_STATE_SET, Button.EMPTY_STATE_SET};
        int[] iArr2 = {i71.b.j, i71.b.f7923a};
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams5.gravity = 5;
        this.d.setLayoutParams(layoutParams5);
        this.d.setTextColor(new ColorStateList(iArr, iArr2));
        this.d.setVisibility(8);
        addView(this.d);
    }

    public void c(String str) {
        TextView textView = this.b;
        if (textView != null) {
            textView.setText(str);
        } else {
            SFLogN.d("TerminalCommonView", "updateTitle is fail, mTittle is null");
        }
    }

    public void a(String str) {
        ImageView imageView = this.f7704a;
        if (imageView != null) {
            imageView.setImageBitmap(v71.a(getContext(), str));
        } else {
            SFLogN.d("TerminalCommonView", "updateImage is fail, mImageView is null");
        }
    }

    public void b(String str) {
        a(str, -1);
    }

    public void b(int i) {
        TextView textView = this.c;
        if (textView != null) {
            textView.setGravity(i);
        } else {
            SFLogN.d("TerminalCommonView", "updateMessageTextGravity is fail, mMessage is null");
        }
    }

    public void a(String str, int i) {
        TextView textView = this.c;
        if (textView != null) {
            textView.setText(str);
        } else {
            SFLogN.d("TerminalCommonView", "updateMessage is fail, mMessage is null");
        }
        a(i);
    }

    public final void a(int i) {
        if (i <= 0) {
            TextView textView = this.d;
            if (textView != null) {
                textView.setVisibility(8);
                return;
            }
            return;
        }
        TextView textView2 = this.c;
        if (textView2 != null) {
            textView2.post(new a(i));
        }
    }

    public void a(View.OnClickListener onClickListener) {
        TextView textView = this.d;
        if (textView != null) {
            textView.setOnClickListener(onClickListener);
        } else {
            SFLogN.d("TerminalCommonView", "setDetailsViewOnClickListener is fail, mTxtDetails is null");
        }
    }

    public void a(float f) {
        TextView textView = this.c;
        if (textView != null) {
            textView.setTextSize(f);
        } else {
            SFLogN.d("TerminalCommonView", "updateMessageTextSize is fail, mMessage is null");
        }
    }
}
