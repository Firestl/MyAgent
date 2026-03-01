package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageButton;
import androidx.appcompat.R;
import supwisdom.jb;
import supwisdom.k3;
import supwisdom.m2;
import supwisdom.m3;
import supwisdom.q2;
import supwisdom.qc;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageButton extends ImageButton implements jb, qc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m2 f1149a;
    public final q2 b;

    public AppCompatImageButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        m2 m2Var = this.f1149a;
        if (m2Var != null) {
            m2Var.a();
        }
        q2 q2Var = this.b;
        if (q2Var != null) {
            q2Var.a();
        }
    }

    @Override // supwisdom.jb
    public ColorStateList getSupportBackgroundTintList() {
        m2 m2Var = this.f1149a;
        if (m2Var != null) {
            return m2Var.b();
        }
        return null;
    }

    @Override // supwisdom.jb
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        m2 m2Var = this.f1149a;
        if (m2Var != null) {
            return m2Var.c();
        }
        return null;
    }

    @Override // supwisdom.qc
    public ColorStateList getSupportImageTintList() {
        q2 q2Var = this.b;
        if (q2Var != null) {
            return q2Var.b();
        }
        return null;
    }

    @Override // supwisdom.qc
    public PorterDuff.Mode getSupportImageTintMode() {
        q2 q2Var = this.b;
        if (q2Var != null) {
            return q2Var.c();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.b.d() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        m2 m2Var = this.f1149a;
        if (m2Var != null) {
            m2Var.b(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        m2 m2Var = this.f1149a;
        if (m2Var != null) {
            m2Var.a(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        q2 q2Var = this.b;
        if (q2Var != null) {
            q2Var.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        q2 q2Var = this.b;
        if (q2Var != null) {
            q2Var.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        this.b.a(i);
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        q2 q2Var = this.b;
        if (q2Var != null) {
            q2Var.a();
        }
    }

    @Override // supwisdom.jb
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        m2 m2Var = this.f1149a;
        if (m2Var != null) {
            m2Var.b(colorStateList);
        }
    }

    @Override // supwisdom.jb
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        m2 m2Var = this.f1149a;
        if (m2Var != null) {
            m2Var.a(mode);
        }
    }

    @Override // supwisdom.qc
    public void setSupportImageTintList(ColorStateList colorStateList) {
        q2 q2Var = this.b;
        if (q2Var != null) {
            q2Var.a(colorStateList);
        }
    }

    @Override // supwisdom.qc
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        q2 q2Var = this.b;
        if (q2Var != null) {
            q2Var.a(mode);
        }
    }

    public AppCompatImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.imageButtonStyle);
    }

    public AppCompatImageButton(Context context, AttributeSet attributeSet, int i) {
        super(m3.b(context), attributeSet, i);
        k3.a(this, getContext());
        m2 m2Var = new m2(this);
        this.f1149a = m2Var;
        m2Var.a(attributeSet, i);
        q2 q2Var = new q2(this);
        this.b = q2Var;
        q2Var.a(attributeSet, i);
    }
}
