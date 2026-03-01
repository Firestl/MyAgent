package supwisdom;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.AbsListView;
import android.widget.TextView;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"AppCompatCustomView"})
public class za1 extends TextView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DisplayMetrics f9988a;

    public za1(Context context) {
        super(context);
        this.f9988a = context.getResources().getDisplayMetrics();
        setLayoutParams(new AbsListView.LayoutParams(-1, -2));
        setText(jb1.c.a2);
        setMinHeight(a(56.0f));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.textAppearanceMedium});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        setTextAppearance(context, resourceId);
        setGravity(8388627);
        setPadding(a(16.0f), a(8.0f), a(16.0f), a(8.0f));
        setBackgroundColor(-1);
        setTextColor(-7829368);
    }

    public final int a(float f) {
        return (int) TypedValue.applyDimension(1, f, this.f9988a);
    }
}
