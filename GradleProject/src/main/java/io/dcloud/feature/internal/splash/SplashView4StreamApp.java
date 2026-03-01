package io.dcloud.feature.internal.splash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.taobao.weex.common.Constants;
import io.dcloud.PdrR;

/* JADX INFO: loaded from: classes3.dex */
public class SplashView4StreamApp extends RelativeLayout implements ISplash {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Bitmap f6558a;
    public ImageView b;
    public TextView c;

    public SplashView4StreamApp(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void a(Context context, String str, String str2) {
        View viewInflate = View.inflate(context, PdrR.getInt(context, Constants.Name.LAYOUT, "dcloud_view_splash"), this);
        ((TextView) viewInflate.findViewById(PdrR.getInt(context, "id", "tv_copyright_splash_dcloud"))).setTypeface(Typeface.create("宋体", 2));
        TextView textView = (TextView) viewInflate.findViewById(PdrR.getInt(context, "id", "tv_loading_splash_dcloud"));
        if (!TextUtils.isEmpty(str2)) {
            textView.setText(str2);
        }
        textView.setTypeface(Typeface.create("宋体", 1));
        TextView textView2 = (TextView) viewInflate.findViewById(PdrR.getInt(context, "id", "tv_name_splash_dcloud"));
        this.c = textView2;
        textView2.setTypeface(Typeface.create("宋体", 1));
        if (!TextUtils.isEmpty(str)) {
            textView2.setText(str);
        }
        ImageView imageView = (ImageView) viewInflate.findViewById(PdrR.getInt(context, "id", "iv_icon_splash_dcloud"));
        this.b = imageView;
        Bitmap bitmap = this.f6558a;
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    @Override // io.dcloud.feature.internal.splash.ISplash
    public void setImageBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            this.f6558a = bitmap;
            this.b.setImageBitmap(bitmap);
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
            alphaAnimation.setDuration(800L);
            this.b.startAnimation(alphaAnimation);
        }
    }

    @Override // io.dcloud.feature.internal.splash.ISplash
    public void setNameText(String str) {
        TextView textView = this.c;
        if (textView == null || !TextUtils.isEmpty(textView.getText())) {
            return;
        }
        this.c.setText(str);
    }

    public SplashView4StreamApp(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SplashView4StreamApp(Context context, Bitmap bitmap, String str) {
        super(context);
        this.f6558a = bitmap;
        a(context, str, null);
    }

    public SplashView4StreamApp(Context context, Bitmap bitmap, String str, String str2) {
        super(context);
        this.f6558a = bitmap;
        a(context, str, str2);
    }
}
