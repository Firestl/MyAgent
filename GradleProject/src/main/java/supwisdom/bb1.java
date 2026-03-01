package supwisdom;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import supwisdom.ya1;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class bb1 extends ViewGroup implements ya1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f7048a;
    public final DisplayMetrics b;
    public final TextView c;
    public final ImageView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ListView f7049e;
    public final TextView f;
    public final LinearLayout g;
    public final Button h;
    public final Button i;
    public final int j;
    public int k;
    public float l;
    public float m;
    public ya1.a n;

    /* JADX INFO: compiled from: Proguard */
    public static class a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7050a;

        public a(int i, int i2) {
            super(i, i2);
        }
    }

    public bb1(Context context, boolean z) {
        super(context);
        this.f7048a = context;
        this.b = getResources().getDisplayMetrics();
        setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        setBackgroundColor(0);
        this.j = a(480);
        LinearLayout linearLayoutB = b(z);
        addView(linearLayoutB);
        if (!z) {
            TextView textViewP = p();
            this.c = textViewP;
            linearLayoutB.addView(textViewP);
            this.d = null;
            ListView listViewN = n();
            this.f7049e = listViewN;
            addView(listViewN);
            TextView textViewJ = j();
            this.f = textViewJ;
            addView(textViewJ);
            LinearLayout linearLayoutA = a(z);
            this.g = linearLayoutA;
            addView(linearLayoutA);
            Button buttonO = o();
            this.h = buttonO;
            linearLayoutA.addView(buttonO);
            Button buttonH = h();
            this.i = buttonH;
            linearLayoutA.addView(buttonH);
            return;
        }
        LinearLayout linearLayoutL = l();
        linearLayoutB.addView(linearLayoutL);
        ImageView imageViewK = k();
        this.d = imageViewK;
        linearLayoutL.addView(imageViewK);
        TextView textViewM = m();
        this.c = textViewM;
        linearLayoutL.addView(textViewM);
        LinearLayout linearLayoutA2 = a(z);
        this.g = linearLayoutA2;
        linearLayoutB.addView(linearLayoutA2);
        Button buttonO2 = o();
        this.h = buttonO2;
        linearLayoutA2.addView(buttonO2);
        Button buttonH2 = h();
        this.i = buttonH2;
        linearLayoutA2.addView(buttonH2);
        linearLayoutB.addView(i());
        ListView listViewN2 = n();
        this.f7049e = listViewN2;
        addView(listViewN2);
        this.f = null;
    }

    @Override // supwisdom.ya1
    public void a(ya1.a aVar) {
        this.n = aVar;
    }

    @Override // supwisdom.ya1
    public ImageView b() {
        return this.d;
    }

    @Override // supwisdom.ya1
    public Button c() {
        return this.h;
    }

    @Override // supwisdom.ya1
    public TextView d() {
        return this.c;
    }

    @Override // supwisdom.ya1
    public Button e() {
        return this.i;
    }

    @Override // supwisdom.ya1
    public LinearLayout f() {
        return this.g;
    }

    @Override // supwisdom.ya1
    public TextView g() {
        return this.f;
    }

    public final Button h() {
        Button button = new Button(this.f7048a, null, R.attr.buttonBarButtonStyle);
        button.setContentDescription("AlwaysButton");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388611;
        button.setLayoutParams(layoutParams);
        button.setMaxLines(2);
        button.setMinHeight(a(48));
        button.setEnabled(false);
        button.setText(jb1.c.Z1);
        return button;
    }

    public final View i() {
        View view = new View(this.f7048a);
        TypedArray typedArrayObtainStyledAttributes = this.f7048a.obtainStyledAttributes(new int[]{R.attr.dividerVertical});
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, a(1)));
        view.setBackgroundDrawable(drawable);
        return view;
    }

    public final TextView j() {
        TextView textView = new TextView(this.f7048a);
        textView.setContentDescription("EmptyView");
        a aVar = new a(-1, -1);
        aVar.f7050a = true;
        textView.setLayoutParams(aVar);
        textView.setPadding(a(32), a(32), a(32), a(32));
        textView.setGravity(17);
        textView.setVisibility(8);
        textView.setTextColor(-1);
        textView.setText(jb1.c.W1);
        return textView;
    }

    public final ImageView k() {
        ImageView imageView = new ImageView(this.f7048a);
        imageView.setContentDescription("IconView");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(a(24), a(24));
        layoutParams.gravity = 8388659;
        layoutParams.setMargins(a(16), a(20), a(16), 0);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return imageView;
    }

    public final LinearLayout l() {
        LinearLayout linearLayout = new LinearLayout(this.f7048a);
        linearLayout.setContentDescription("LastSelectionHolder");
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, a(64)));
        linearLayout.setOrientation(0);
        return linearLayout;
    }

    public final TextView m() {
        TextView textView = new TextView(this.f7048a);
        textView.setContentDescription("LastTitleView");
        TypedArray typedArrayObtainStyledAttributes = this.f7048a.obtainStyledAttributes(new int[]{R.attr.textAppearanceMedium, R.attr.listPreferredItemHeight});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, dimensionPixelSize, 1.0f);
        layoutParams.leftMargin = a(16);
        textView.setLayoutParams(layoutParams);
        textView.setTextAppearance(this.f7048a, resourceId);
        textView.setGravity(8388627);
        textView.setPadding(0, 0, a(16), 0);
        return textView;
    }

    public final ListView n() {
        ListView listView = new ListView(this.f7048a);
        listView.setContentDescription("ListView");
        listView.setLayoutParams(new a(-1, -2));
        listView.setClipToPadding(false);
        listView.setScrollBarStyle(33554432);
        listView.setBackgroundColor(-1);
        listView.setDivider(null);
        return listView;
    }

    public final Button o() {
        Button button = new Button(this.f7048a, null, R.attr.buttonBarButtonStyle);
        button.setContentDescription("OnceButton");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388611;
        button.setLayoutParams(layoutParams);
        button.setMaxLines(2);
        button.setMinHeight(a(48));
        button.setEnabled(false);
        button.setText(jb1.c.Y1);
        return button;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width = getWidth();
        int i5 = this.k;
        int paddingLeft = getPaddingLeft();
        int paddingRight = width - getPaddingRight();
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            a aVar = (a) childAt.getLayoutParams();
            if (childAt.getVisibility() != 8) {
                int i7 = i5 + ((ViewGroup.MarginLayoutParams) aVar).topMargin;
                int measuredHeight = childAt.getMeasuredHeight() + i7;
                int measuredWidth = childAt.getMeasuredWidth();
                int i8 = (((paddingRight - paddingLeft) - measuredWidth) / 2) + paddingLeft;
                childAt.layout(i8, i7, measuredWidth + i8, measuredHeight);
                i5 = measuredHeight + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i5 = this.j;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i5 >= 0 ? Math.min(size, i5) : size, 1073741824);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(size2, 1073741824);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childCount = getChildCount();
        int measuredHeight = paddingTop;
        int i6 = 0;
        while (true) {
            i3 = 8;
            if (i6 >= childCount) {
                break;
            }
            View childAt = getChildAt(i6);
            a aVar = (a) childAt.getLayoutParams();
            if (aVar.f7050a && childAt.getVisibility() != 8) {
                measureChildWithMargins(childAt, iMakeMeasureSpec, paddingLeft, iMakeMeasureSpec2, measuredHeight);
                measuredHeight += ((ViewGroup.MarginLayoutParams) aVar).topMargin + childAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
            }
            i6++;
        }
        int i7 = 0;
        while (i7 < childCount) {
            View childAt2 = getChildAt(i7);
            a aVar2 = (a) childAt2.getLayoutParams();
            if (aVar2.f7050a || childAt2.getVisibility() == i3) {
                i4 = iMakeMeasureSpec;
            } else {
                i4 = iMakeMeasureSpec;
                measureChildWithMargins(childAt2, iMakeMeasureSpec, paddingLeft, iMakeMeasureSpec2, measuredHeight);
                measuredHeight += ((ViewGroup.MarginLayoutParams) aVar2).topMargin + childAt2.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) aVar2).bottomMargin;
            }
            i7++;
            iMakeMeasureSpec = i4;
            i3 = 8;
        }
        this.k = Math.max(0, size2 - measuredHeight);
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.l = motionEvent.getX();
            float y = motionEvent.getY();
            this.m = y;
            if (a(this.l, y) == null) {
                return true;
            }
        } else if (actionMasked == 1 && a(this.l, this.m) == null && a(motionEvent.getX(), motionEvent.getY()) == null) {
            q();
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final TextView p() {
        TextView textView = new TextView(this.f7048a);
        textView.setContentDescription("TitleView");
        TypedArray typedArrayObtainStyledAttributes = this.f7048a.obtainStyledAttributes(new int[]{R.attr.textAppearanceMedium});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        textView.setTextAppearance(this.f7048a, resourceId);
        textView.setGravity(8388627);
        textView.setPadding(a(16), a(8), a(16), a(8));
        textView.setMinHeight(a(56));
        return textView;
    }

    public final void q() {
        ya1.a aVar = this.n;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // supwisdom.ya1
    public ListView a() {
        return this.f7049e;
    }

    public final LinearLayout b(boolean z) {
        LinearLayout linearLayout = new LinearLayout(this.f7048a);
        linearLayout.setContentDescription("TitleBarHolder");
        a aVar = new a(-1, -2);
        aVar.f7050a = true;
        if (z) {
            linearLayout.setOrientation(1);
        } else {
            linearLayout.setOrientation(0);
        }
        linearLayout.setLayoutParams(aVar);
        linearLayout.setBackgroundColor(-1);
        return linearLayout;
    }

    public final LinearLayout a(boolean z) {
        ViewGroup.LayoutParams layoutParams;
        LinearLayout linearLayout = new LinearLayout(this.f7048a, null, R.attr.buttonBarStyle);
        linearLayout.setContentDescription("ButtonBar");
        linearLayout.setOrientation(0);
        if (z) {
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
        } else {
            a aVar = new a(-1, -2);
            aVar.f7050a = true;
            layoutParams = aVar;
        }
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(8388629);
        linearLayout.setMeasureWithLargestChildEnabled(true);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setPadding(a(12), a(8), a(12), a(8));
        linearLayout.setVisibility(8);
        return linearLayout;
    }

    public final View a(float f, float f2) {
        return a((ViewGroup) this, f, f2);
    }

    public static View a(ViewGroup viewGroup, float f, float f2) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (a(childAt, f, f2)) {
                return childAt;
            }
        }
        return null;
    }

    public static boolean a(View view, float f, float f2) {
        float x = view.getX();
        float y = view.getY();
        return f >= x && f2 >= y && f < ((float) view.getWidth()) + x && f2 < ((float) view.getHeight()) + y;
    }

    public final int a(int i) {
        return (int) TypedValue.applyDimension(1, i, this.b);
    }
}
