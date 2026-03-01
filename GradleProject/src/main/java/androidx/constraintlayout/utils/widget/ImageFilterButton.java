package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.R;

/* JADX INFO: loaded from: classes.dex */
public class ImageFilterButton extends AppCompatImageButton {
    public ImageFilterView.c c;
    public float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f1230e;
    public float f;
    public Path g;
    public ViewOutlineProvider h;
    public RectF i;
    public Drawable[] j;
    public LayerDrawable k;
    public boolean l;

    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), (Math.min(r3, r4) * ImageFilterButton.this.f1230e) / 2.0f);
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), ImageFilterButton.this.f);
        }
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new ImageFilterView.c();
        this.d = 0.0f;
        this.f1230e = 0.0f;
        this.f = Float.NaN;
        this.l = true;
        a(context, attributeSet);
    }

    private void setOverlay(boolean z) {
        this.l = z;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 21 || this.f == 0.0f || this.g == null) {
            z = false;
        } else {
            z = true;
            canvas.save();
            canvas.clipPath(this.g);
        }
        super.draw(canvas);
        if (z) {
            canvas.restore();
        }
    }

    public float getContrast() {
        return this.c.f;
    }

    public float getCrossfade() {
        return this.d;
    }

    public float getRound() {
        return this.f;
    }

    public float getRoundPercent() {
        return this.f1230e;
    }

    public float getSaturation() {
        return this.c.f1237e;
    }

    public float getWarmth() {
        return this.c.g;
    }

    public void setBrightness(float f) {
        ImageFilterView.c cVar = this.c;
        cVar.d = f;
        cVar.a(this);
    }

    public void setContrast(float f) {
        ImageFilterView.c cVar = this.c;
        cVar.f = f;
        cVar.a(this);
    }

    public void setCrossfade(float f) {
        this.d = f;
        if (this.j != null) {
            if (!this.l) {
                this.k.getDrawable(0).setAlpha((int) ((1.0f - this.d) * 255.0f));
            }
            this.k.getDrawable(1).setAlpha((int) (this.d * 255.0f));
            super.setImageDrawable(this.k);
        }
    }

    public void setRound(float f) {
        if (Float.isNaN(f)) {
            this.f = f;
            float f2 = this.f1230e;
            this.f1230e = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.f != f;
        this.f = f;
        if (f != 0.0f) {
            if (this.g == null) {
                this.g = new Path();
            }
            if (this.i == null) {
                this.i = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.h == null) {
                    b bVar = new b();
                    this.h = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.i.set(0.0f, 0.0f, getWidth(), getHeight());
            this.g.reset();
            Path path = this.g;
            RectF rectF = this.i;
            float f3 = this.f;
            path.addRoundRect(rectF, f3, f3, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setRoundPercent(float f) {
        boolean z = this.f1230e != f;
        this.f1230e = f;
        if (f != 0.0f) {
            if (this.g == null) {
                this.g = new Path();
            }
            if (this.i == null) {
                this.i = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.h == null) {
                    a aVar = new a();
                    this.h = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.f1230e) / 2.0f;
            this.i.set(0.0f, 0.0f, width, height);
            this.g.reset();
            this.g.addRoundRect(this.i, fMin, fMin, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setSaturation(float f) {
        ImageFilterView.c cVar = this.c;
        cVar.f1237e = f;
        cVar.a(this);
    }

    public void setWarmth(float f) {
        ImageFilterView.c cVar = this.c;
        cVar.g = f;
        cVar.a(this);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ImageFilterView_altSrc);
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ImageFilterView_crossfade) {
                    this.d = typedArrayObtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == R.styleable.ImageFilterView_warmth) {
                    setWarmth(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.ImageFilterView_saturation) {
                    setSaturation(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.ImageFilterView_contrast) {
                    setContrast(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.ImageFilterView_round) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRound(typedArrayObtainStyledAttributes.getDimension(index, 0.0f));
                    }
                } else if (index == R.styleable.ImageFilterView_roundPercent) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRoundPercent(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                    }
                } else if (index == R.styleable.ImageFilterView_overlay) {
                    setOverlay(typedArrayObtainStyledAttributes.getBoolean(index, this.l));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            if (drawable != null) {
                Drawable[] drawableArr = new Drawable[2];
                this.j = drawableArr;
                drawableArr[0] = getDrawable();
                this.j[1] = drawable;
                LayerDrawable layerDrawable = new LayerDrawable(this.j);
                this.k = layerDrawable;
                layerDrawable.getDrawable(1).setAlpha((int) (this.d * 255.0f));
                super.setImageDrawable(this.k);
            }
        }
    }

    public ImageFilterButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new ImageFilterView.c();
        this.d = 0.0f;
        this.f1230e = 0.0f;
        this.f = Float.NaN;
        this.l = true;
        a(context, attributeSet);
    }
}
