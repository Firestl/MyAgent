package supwisdom;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: WrappedDrawableApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class b9 extends Drawable implements Drawable.Callback, a9, z8 {
    public static final PorterDuff.Mode g = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7036a;
    public PorterDuff.Mode b;
    public boolean c;
    public d9 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f7037e;
    public Drawable f;

    public b9(d9 d9Var, Resources resources) {
        this.d = d9Var;
        a(resources);
    }

    public final void a(Resources resources) {
        Drawable.ConstantState constantState;
        d9 d9Var = this.d;
        if (d9Var == null || (constantState = d9Var.b) == null) {
            return;
        }
        a(constantState.newDrawable(resources));
    }

    public boolean b() {
        return true;
    }

    public final d9 c() {
        return new d9(this.d);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.f.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        d9 d9Var = this.d;
        return changingConfigurations | (d9Var != null ? d9Var.getChangingConfigurations() : 0) | this.f.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        d9 d9Var = this.d;
        if (d9Var == null || !d9Var.a()) {
            return null;
        }
        this.d.f7304a = getChangingConfigurations();
        return this.d;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.f.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getLayoutDirection() {
        return y8.e(this.f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.f.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.f.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        return this.f.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        return this.f.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.f.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return y8.f(this.f);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        d9 d9Var;
        ColorStateList colorStateList = (!b() || (d9Var = this.d) == null) ? null : d9Var.c;
        return (colorStateList != null && colorStateList.isStateful()) || this.f.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.f.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f7037e && super.mutate() == this) {
            this.d = c();
            Drawable drawable = this.f;
            if (drawable != null) {
                drawable.mutate();
            }
            d9 d9Var = this.d;
            if (d9Var != null) {
                Drawable drawable2 = this.f;
                d9Var.b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.f7037e = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        return y8.a(this.f, i);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        return this.f.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        y8.a(this.f, z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int i) {
        this.f.setChangingConfigurations(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.f.setDither(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.f.setFilterBitmap(z);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        return a(iArr) || this.f.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, supwisdom.z8
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable, supwisdom.z8
    public void setTintList(ColorStateList colorStateList) {
        this.d.c = colorStateList;
        a(getState());
    }

    @Override // android.graphics.drawable.Drawable, supwisdom.z8
    public void setTintMode(PorterDuff.Mode mode) {
        this.d.d = mode;
        a(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.f.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public final boolean a(int[] iArr) {
        if (!b()) {
            return false;
        }
        d9 d9Var = this.d;
        ColorStateList colorStateList = d9Var.c;
        PorterDuff.Mode mode = d9Var.d;
        if (colorStateList != null && mode != null) {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!this.c || colorForState != this.f7036a || mode != this.b) {
                setColorFilter(colorForState, mode);
                this.f7036a = colorForState;
                this.b = mode;
                this.c = true;
                return true;
            }
        } else {
            this.c = false;
            clearColorFilter();
        }
        return false;
    }

    public b9(Drawable drawable) {
        this.d = c();
        a(drawable);
    }

    @Override // supwisdom.a9
    public final Drawable a() {
        return this.f;
    }

    @Override // supwisdom.a9
    public final void a(Drawable drawable) {
        Drawable drawable2 = this.f;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            d9 d9Var = this.d;
            if (d9Var != null) {
                d9Var.b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }
}
