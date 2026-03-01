package io.dcloud.feature.internal.splash;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
public class SplashViewDBackground extends RelativeLayout implements ISplash {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f6559a;
    public b b;
    public TextView c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6560e;

    public SplashViewDBackground(Context context, Bitmap bitmap, String str, boolean z) {
        super(context);
        this.b = null;
        this.c = null;
        this.d = false;
        this.f6560e = false;
        this.f6559a = str;
        this.d = z;
        setBackgroundColor(-1);
        a(bitmap);
    }

    private void a(Bitmap bitmap) {
        float fA;
        if (this.f6560e) {
            return;
        }
        this.b = new b(getContext(), this.d);
        int i = getResources().getDisplayMetrics().heightPixels;
        int iA = (int) this.b.a(6.0f);
        int iA2 = (int) this.b.a(65.0f);
        int iA3 = (int) this.b.a(8.0f);
        if (getResources().getDisplayMetrics().widthPixels >= 1080) {
            fA = this.b.a(6.0f);
        } else {
            if (getResources().getDisplayMetrics().widthPixels < 720) {
                if (getResources().getDisplayMetrics().widthPixels >= 540) {
                    fA = this.b.a(10.0f);
                }
                int i2 = iA3;
                b bVar = this.b;
                bVar.a(bitmap, iA2, iA2, (int) bVar.a(1.0f), 12962246, -5592406);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iA2, iA2);
                layoutParams.addRule(14);
                layoutParams.topMargin = ((i / 2) - ((iA2 + iA) + i2)) / 2;
                this.b.setId(R.id.button1);
                addView(this.b, layoutParams);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(3, this.b.getId());
                layoutParams2.addRule(13);
                layoutParams2.topMargin = iA;
                TextView textView = new TextView(getContext());
                this.c = textView;
                textView.setSingleLine();
                this.c.setTextSize(i2);
                this.c.setTextColor(-10855846);
                setNameText(this.f6559a);
                this.c.setTypeface(Typeface.create("宋体", 0));
                addView(this.c, layoutParams2);
            }
            fA = this.b.a(8.0f);
        }
        iA3 = (int) fA;
        int i22 = iA3;
        b bVar2 = this.b;
        bVar2.a(bitmap, iA2, iA2, (int) bVar2.a(1.0f), 12962246, -5592406);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(iA2, iA2);
        layoutParams3.addRule(14);
        layoutParams3.topMargin = ((i / 2) - ((iA2 + iA) + i22)) / 2;
        this.b.setId(R.id.button1);
        addView(this.b, layoutParams3);
        RelativeLayout.LayoutParams layoutParams22 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams22.addRule(3, this.b.getId());
        layoutParams22.addRule(13);
        layoutParams22.topMargin = iA;
        TextView textView2 = new TextView(getContext());
        this.c = textView2;
        textView2.setSingleLine();
        this.c.setTextSize(i22);
        this.c.setTextColor(-10855846);
        setNameText(this.f6559a);
        this.c.setTypeface(Typeface.create("宋体", 0));
        addView(this.c, layoutParams22);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof ISplash) {
            this.f6560e = true;
        }
        super.addView(view, i, layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.f6560e) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // io.dcloud.feature.internal.splash.ISplash
    public void setImageBitmap(Bitmap bitmap) {
        this.b.setBitmap(bitmap);
    }

    @Override // io.dcloud.feature.internal.splash.ISplash
    public void setNameText(String str) {
        TextView textView = this.c;
        if (textView == null || !TextUtils.isEmpty(textView.getText())) {
            return;
        }
        this.c.setText(str);
    }
}
