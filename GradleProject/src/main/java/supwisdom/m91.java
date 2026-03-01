package supwisdom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.sangfor.sdk.R;
import com.sangfor.sdk.randcodeui.view.SangforRandCodeLayout;
import com.sangfor.sdk.randcodeui.view.SangforSdkTextView;
import com.sangfor.sdk.utils.SFLogN;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class m91 extends RelativeLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f8367a;
    public View b;
    public SangforSdkTextView c;
    public SangforRandCodeLayout.b d;

    /* JADX INFO: compiled from: Proguard */
    public static class a extends Drawable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Paint f8368a;
        public final Paint b;

        public a() {
            Paint paint = new Paint();
            this.f8368a = paint;
            paint.setAntiAlias(true);
            paint.setColor(-16776961);
            Paint paint2 = new Paint();
            this.b = paint2;
            paint2.setAntiAlias(true);
            paint2.setColor(-1);
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(2.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            int iWidth = getBounds().width();
            int iHeight = getBounds().height();
            int iMin = Math.min(iWidth, iHeight) / 2;
            float f = iWidth / 2;
            float f2 = iHeight / 2;
            float f3 = iMin;
            canvas.drawCircle(f, f2, f3 - this.b.getStrokeWidth(), this.f8368a);
            canvas.drawCircle(f, f2, f3 - this.b.getStrokeWidth(), this.b);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return this.f8368a.getAlpha();
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.f8368a.setAlpha(i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.f8368a.setColorFilter(colorFilter);
        }
    }

    public m91(Context context, SangforRandCodeLayout.b bVar, boolean z) {
        super(context);
        this.f8367a = context;
        this.d = bVar;
        if (z) {
            b();
        } else {
            c();
        }
    }

    public int a() {
        return this.d.c();
    }

    public final void b() {
        LinearLayout linearLayout = new LinearLayout(this.f8367a);
        linearLayout.setGravity(16);
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        LinearLayout linearLayout2 = new LinearLayout(this.f8367a);
        linearLayout2.setLayoutParams(new RelativeLayout.LayoutParams(wb1.a(this.f8367a, 21.0f), wb1.a(this.f8367a, 21.0f)));
        linearLayout2.setBackground(new a());
        linearLayout.addView(linearLayout2);
        SangforSdkTextView sangforSdkTextView = new SangforSdkTextView(this.f8367a);
        this.c = sangforSdkTextView;
        sangforSdkTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.c.a(this.d.c() + "");
        linearLayout2.addView(this.c);
        addView(linearLayout);
    }

    public void c() {
        View viewInflate = LayoutInflater.from(this.f8367a).inflate(R.layout.randcode_sdk_cricle_view, (ViewGroup) this, true);
        this.b = viewInflate;
        SangforSdkTextView sangforSdkTextView = (SangforSdkTextView) viewInflate.findViewById(R.id.tvNum);
        this.c = sangforSdkTextView;
        sangforSdkTextView.a(this.d.c() + "");
        SFLogN.c("SangforRandCodeCircleView", this.d.c() + "");
    }

    public void a(SangforRandCodeLayout.b bVar) {
        this.d = bVar;
        this.c.a(this.d.c() + "");
    }
}
