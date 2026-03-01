package io.dcloud.feature.gallery.imageedit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import io.dcloud.feature.gallery.imageedit.b;
import io.dcloud.feature.gallery.imageedit.c.d;

/* JADX INFO: loaded from: classes2.dex */
public class IMGStickerTextView extends IMGStickerView implements b.a {
    public static float o = -1.0f;
    public TextView l;
    public d m;
    public b n;

    public IMGStickerTextView(Context context) {
        this(context, null, 0);
    }

    private b getDialog() {
        if (this.n == null) {
            this.n = new b(getContext(), this);
        }
        return this.n;
    }

    @Override // io.dcloud.feature.gallery.imageedit.view.IMGStickerView
    public View a(Context context) {
        TextView textView = new TextView(context);
        this.l = textView;
        textView.setTextSize(o);
        this.l.setPadding(26, 26, 26, 26);
        this.l.setTextColor(-1);
        return this.l;
    }

    @Override // io.dcloud.feature.gallery.imageedit.view.IMGStickerView
    public void b(Context context) {
        if (o <= 0.0f) {
            o = TypedValue.applyDimension(2, 24.0f, context.getResources().getDisplayMetrics());
        }
        super.b(context);
    }

    @Override // io.dcloud.feature.gallery.imageedit.view.IMGStickerView
    public void c() {
        b dialog = getDialog();
        dialog.a(this.m);
        dialog.show();
    }

    public d getText() {
        return this.m;
    }

    public void setText(d dVar) {
        TextView textView;
        this.m = dVar;
        if (dVar == null || (textView = this.l) == null) {
            return;
        }
        textView.setText(dVar.c());
        this.l.setTextColor(this.m.b());
        this.l.setBackgroundColor(this.m.a());
    }

    public IMGStickerTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IMGStickerTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // io.dcloud.feature.gallery.imageedit.b.a
    public void a(d dVar) {
        TextView textView;
        this.m = dVar;
        if (dVar == null || (textView = this.l) == null) {
            return;
        }
        textView.setText(dVar.c());
        this.l.setTextColor(this.m.b());
        this.l.setBackgroundColor(this.m.a());
    }
}
