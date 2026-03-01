package supwisdom;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class ab1 extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f6882a;
    public final DisplayMetrics b;
    public final ImageView c;
    public final TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextView f6883e;

    public ab1(Context context) {
        super(context);
        this.f6882a = context;
        this.b = context.getResources().getDisplayMetrics();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.listPreferredItemHeightSmall, R.attr.activatedBackgroundIndicator});
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        typedArrayObtainStyledAttributes.recycle();
        setOrientation(0);
        setLayoutParams(new AbsListView.LayoutParams(-1, -2));
        setMinimumHeight(dimensionPixelSize);
        setPadding(0, a(4.0f), 0, a(4.0f));
        setBackgroundDrawable(drawable);
        ImageView imageViewB = b();
        this.c = imageViewB;
        addView(imageViewB);
        LinearLayout linearLayoutD = d();
        addView(linearLayoutD);
        TextView textViewC = c();
        this.d = textViewC;
        linearLayoutD.addView(textViewC);
        TextView textViewA = a();
        this.f6883e = textViewA;
        linearLayoutD.addView(textViewA);
    }

    public final TextView a() {
        TextView textView = new TextView(this.f6882a);
        TypedArray typedArrayObtainStyledAttributes = this.f6882a.obtainStyledAttributes(new int[]{R.attr.textAppearanceSmall});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setTextAppearance(this.f6882a, resourceId);
        textView.setMinLines(1);
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        return textView;
    }

    public final ImageView b() {
        ImageView imageView = new ImageView(this.f6882a);
        TypedArray typedArrayObtainStyledAttributes = this.f6882a.obtainStyledAttributes(new int[]{R.attr.listPreferredItemPaddingLeft});
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a(24.0f), a(24.0f));
        layoutParams.gravity = 8388627;
        layoutParams.leftMargin = dimensionPixelOffset;
        layoutParams.topMargin = a(12.0f);
        layoutParams.bottomMargin = a(12.0f);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return imageView;
    }

    public final TextView c() {
        TextView textView = new TextView(this.f6882a);
        TypedArray typedArrayObtainStyledAttributes = this.f6882a.obtainStyledAttributes(new int[]{R.attr.textAppearanceMedium, R.attr.textColorPrimary});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        int color = typedArrayObtainStyledAttributes.getColor(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setTextAppearance(this.f6882a, resourceId);
        textView.setTextColor(color);
        textView.setMinLines(1);
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        return textView;
    }

    public final LinearLayout d() {
        LinearLayout linearLayout = new LinearLayout(this.f6882a);
        TypedArray typedArrayObtainStyledAttributes = this.f6882a.obtainStyledAttributes(new int[]{R.attr.listPreferredItemPaddingLeft, R.attr.listPreferredItemPaddingRight});
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, 0);
        int dimensionPixelOffset2 = typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        linearLayout.setOrientation(1);
        linearLayout.setGravity(8388627);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388627;
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset2, 0);
        return linearLayout;
    }

    public TextView e() {
        return this.f6883e;
    }

    public ImageView f() {
        return this.c;
    }

    public TextView g() {
        return this.d;
    }

    public final int a(float f) {
        return (int) TypedValue.applyDimension(1, f, this.b);
    }
}
