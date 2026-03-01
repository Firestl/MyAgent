package io.dcloud.js.map.amap.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
public class PopViewLayout extends LinearLayout {
    public ImageView mImageView;
    public TextView mTextView;

    public PopViewLayout(Context context, String str, Drawable drawable) {
        super(context);
        this.mTextView = new TextView(context);
        if (!TextUtils.isEmpty(str)) {
            this.mTextView.setText(str);
        }
        ImageView imageView = new ImageView(context);
        this.mImageView = imageView;
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        }
        addView(this.mImageView);
        addView(this.mTextView);
    }

    public void setBubbleIcon(Drawable drawable) {
        if (drawable != null) {
            this.mImageView.setImageDrawable(drawable);
        }
    }

    public void setBubbleLabel(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mTextView.setText(str);
    }
}
